package com.security.example.demo.web;

import com.security.example.demo.domain.entity.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/dashboard")
    public String retrieveDashboard(@AuthenticationPrincipal User user, ModelMap modelMap) {

        modelMap.put("user", user);

        return "dashboard";
    }
}
