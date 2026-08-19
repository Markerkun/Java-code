package org.example.controllers;

import org.example.entities.UserEntity;
import org.example.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountController(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login() {
        return "account/login";
    }

    @GetMapping("/register")
    public String register() {
        return "account/register";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            Model model
    ) {

        // Перевірка username
        if (userRepository.existsByUsername(username)) {
            model.addAttribute(
                    "error",
                    "Користувач з таким ім'ям вже існує"
            );

            return "account/register";
        }

        // Перевірка email
        if (userRepository.existsByEmail(email)) {
            model.addAttribute(
                    "error",
                    "Користувач з таким Email вже існує"
            );

            return "account/register";
        }

        // Перевірка паролів
        if (!password.equals(confirmPassword)) {
            model.addAttribute(
                    "error",
                    "Паролі не співпадають"
            );

            return "account/register";
        }

        // Створення користувача
        UserEntity user = new UserEntity();

        user.setUsername(username);
        user.setEmail(email);

        // Пароль зберігаємо тільки у вигляді BCrypt hash
        user.setPassword(passwordEncoder.encode(password));

        userRepository.save(user);

        return "redirect:/login";
    }
}