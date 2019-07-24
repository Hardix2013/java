package ru.mylife54.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mylife54.models.Product;
import ru.mylife54.models.User;
import ru.mylife54.services.UserService;

import java.util.Map;

@Controller
@RequestMapping("/bucket")
public class BucketController {

    @Autowired
    private UserService userService;


    @GetMapping()
    public String getBucket(@AuthenticationPrincipal User auth, ModelMap modelMap) {
        modelMap.addAttribute("auth", auth);
        return "bucket";
    }

    @PostMapping("/deleteProduct/{id}")
    public String deleteProduct(@AuthenticationPrincipal User auth, @PathVariable("id") Product product, ModelMap modelMap) {
        Map<Product, Integer> bucket = auth.getBucket();
        bucket.remove(product);
        auth.setBucket(bucket);
        userService.updateUser(auth);
        modelMap.addAttribute("auth", auth);
        return "redirect:/bucket";
    }

//    @PostMapping("/by")
//    public String by(@AuthenticationPrincipal User auth,  @ModelAttribute Purchases purchases, ModelMap modelMap){
//     return null;
//    }


}
