package org.example.controllers

import org.springframework.web.bind.annotation.GetMapping

class AccountController {
    @GetMapping("/login")
    public String login() {
        return "account/login";
    }

    @GetMapping("/register")
    public String register() {
        return "account/login";
    }
}
