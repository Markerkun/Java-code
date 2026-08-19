package org.example.seeders;

import lombok.RequiredArgsConstructor;
import org.example.entities.UserEntity;
import org.example.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        if (userRepository.count() > 0) {
            System.out.println("Users already exist. Seeder skipped.");
            return;
        }

        createUser(
                "admin",
                "admin@gmail.com",
                "admin123",
                null
        );

        createUser(
                "mark",
                "mark@gmail.com",
                "12345678",
                null
        );

        createUser(
                "user",
                "user@gmail.com",
                "user12345",
                null
        );

        System.out.println("Users seeded successfully!");
    }

    private void createUser(
            String username,
            String email,
            String password,
            String image
    ) {

        UserEntity user = new UserEntity();

        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setImage(image);
        user.setResetPasswordToken(null);

        userRepository.save(user);

        System.out.println(
                "Created user: " + username + " / " + email
        );
    }
}