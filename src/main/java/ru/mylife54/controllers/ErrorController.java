package ru.mylife54.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.mylife54.models.User;

@Controller
public class ErrorController {

    @GetMapping("/403")
    public String get403(@AuthenticationPrincipal User auth, ModelMap modelMap){
        modelMap.addAttribute("auth",auth);
        return "403";
    }
    @GetMapping("/404")
    public String get404(@AuthenticationPrincipal User auth, ModelMap modelMap){
        modelMap.addAttribute("auth",auth);
        return "404";
    }
}
