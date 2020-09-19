package com.security.example.demo.web;

import com.security.example.demo.domain.entity.User;
import com.security.example.demo.domain.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final AdminService adminService;

    public UserController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/dashboard")
    @Secured("ROLE_USER") //Method level security provided
    public String retrieveDashboard(@AuthenticationPrincipal User user, ModelMap modelMap) {

        modelMap.put("user", user);
        List<User> allUsers = adminService.retrieveAllUsers();
        logger.info("All user retrieved with size: {}", allUsers.size());
        return "dashboard";
    }
}