package com.syc.carpentry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login() {
        return "login"; // templates/auth/login.html
    }

    @GetMapping("/register")
    public String register() {
        return "auth/register"; // templates/auth/register.html
    }

    @GetMapping("/password")
    public String password() {
        return "auth/password"; // templates/auth/password.html
    }
}
