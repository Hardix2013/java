package ru.mylife54.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.mylife54.models.Role;
import ru.mylife54.models.User;
import ru.mylife54.services.RoleService;
import ru.mylife54.services.StorageService;
import ru.mylife54.services.UserService;
import ru.mylife54.utils.CopyNotNullProperties;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private RoleService roleService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${photo.default}")
    private String defaultPhoto;

    @PreAuthorize("hasAnyAuthority('ADMIN','MODERATOR','CREATOR')")
    @GetMapping
    public String getUsers(@AuthenticationPrincipal User auth, @RequestParam(defaultValue = "", required = false)String filter, ModelMap modelMap) {
        modelMap.addAttribute("auth", auth);
        if (!filter.isEmpty()) {
            modelMap.addAttribute("user",service.getUser(filter));
            modelMap.addAttribute("filter",filter);
        }else {
            modelMap.addAttribute("users", service.getAllUser());
        }
        return "users";
    }

    @GetMapping("/{id}")
    public String showUser(@AuthenticationPrincipal User auth, @PathVariable("id") User user, ModelMap modelMap) {
        modelMap.addAttribute("allRoles", roleService.getRoles());
        modelMap.addAttribute("users", user);
        modelMap.addAttribute("auth", auth);
        return "pk";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','MODERATOR','CREATOR') or #user.username == #auth.username")
    @GetMapping("/edit/{id}")
    public String getUserEditForm(@AuthenticationPrincipal User auth, @PathVariable("id") User user, ModelMap modelMap) {
        modelMap.addAttribute("users", user);
        modelMap.addAttribute("roles", roleService.getRoles());
        modelMap.addAttribute("auth", auth);
        return "editUser";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable("id") User target, @AuthenticationPrincipal User auth, @ModelAttribute User sourse, @RequestParam("imageProfile") MultipartFile imageProfile, ModelMap modelMap) {
        String tPassword = target.getPassword();
        String sPassword = sourse.getPassword();
        String photo = target.getPhoto();
        CopyNotNullProperties.myCopyProperties(sourse, target);
        if (sPassword.equals("")) {
            target.setPassword(tPassword);
        } else {
            target.setPassword(bCryptPasswordEncoder.encode(sPassword));
        }
        if (!imageProfile.isEmpty()){
            if (!photo.equals(defaultPhoto)) {
                storageService.delete(photo);
            }
            target.setPhoto(storageService.upload(imageProfile));
        }
        if (target.getPhoto().isEmpty() || target.getPhoto()==null) {
            target.setPhoto(defaultPhoto);
        }
        modelMap.addAttribute("users", target);
        modelMap.addAttribute("auth", auth);
        auth.setBalance(target.getBalance());
        modelMap.addAttribute("roles", roleService.getRoles());
        try {
            service.saveUser(target);
        } catch (Exception e) {
            modelMap.addAttribute("errors", "Такой почтовый ящик уже занят");
            return "editUser";
        }
        return "redirect:/users/" + target.getId();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','MODERATOR','CREATOR') or #user.username == #auth.username")
    @PostMapping("/remove/{id}")
    public String removeUser(@AuthenticationPrincipal User auth, @PathVariable("id") User user, ModelMap modelMap) {
        user.setAuthorities(null);
        service.deleteUser(user);
        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        return "redirect:/users";

    }

    @GetMapping("/registration")
    public String createUser(@AuthenticationPrincipal User auth, ModelMap modelMap) {
        modelMap.addAttribute("users", new User());
        modelMap.addAttribute("auth", auth);
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@AuthenticationPrincipal User auth, @ModelAttribute User user, ModelMap modelMap) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRole("USER"));
        user.setAuthorities(roles);
        modelMap.addAttribute("users", user);
        user.setEnabled(true);
        user.setCredentialsNonExpired(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setPhoto(defaultPhoto);
        user.setBalance(0L);
        if (!user.getEmail().equals(user.getEmailConfirm())){
            modelMap.addAttribute("errors", "Почтовые ящики не совпадают");
            return "registration";
        }
        if (!user.getPassword().equals(user.getPasswordConfirm())){
            modelMap.addAttribute("errors", "Пароли не совпадают");
            return "registration";
        }else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        try {
            service.saveUser(user);
        } catch (Exception e) {
            modelMap.addAttribute("errors", e.getMessage().contains("(USERNAME)") ? "Такой никнейм уже занят" : "Такой почтовый ящик уже занят");
            return "registration";
        }
        modelMap.addAttribute("error", "Регистрация завершена успешно. Авторизуйтесь");
        if (auth!=null){
            return "redirect:/index";
        }else {
            return "redirect:/users/"+user.getId();
        }
    }


}
