package ru.mylife54.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mylife54.models.User;

@Controller
public class GuestController {

    @GetMapping("/index")
    public String getIndexPage(@AuthenticationPrincipal User user, ModelMap modelMap) {
        modelMap.addAttribute("auth", user);
        return "index";
    }

    @GetMapping("/login")
    public String login(@AuthenticationPrincipal User auth, ModelMap modelMap){
        modelMap.addAttribute("auth",auth);
        return "login";
    }
    @GetMapping("/logout")
    public String logout(@AuthenticationPrincipal User auth, ModelMap modelMap){
        modelMap.addAttribute("auth",auth);
        return "logout";
    }

}
