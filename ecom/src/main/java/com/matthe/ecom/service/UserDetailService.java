package com.matthe.ecom.service;

import com.matthe.ecom.model.User;
import com.matthe.ecom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDetailService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Value("${admin.registration.code}") // Load the admin registration code from properties
    private String adminCode;

    public User registerUser(User user,String role, String code) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser == null) {
            if ("ROLE_ADMIN".equals(role) && !adminCode.equals(code)) {
                throw new RuntimeException("Invalid admin registration code");
            }
            user.setRole(role);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        } else {
            throw new RuntimeException("User already exists");
        }
    }

    public boolean authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return true; // Authentication successful
        }
        return false; // Authentication failed
    }

    public String setuserStatus(String status, String username) {
        userRepository.updateStatusByUsername(status, username);
        return username;
    }

    public String checkSession(String username) {
        User user= userRepository.findByUsername(username);
        return user.getStatus();
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean isAdmin(String username) {
        User user = userRepository.findByUsername(username);
        String role = user.getRole();
        if("ROLE_ADMIN".equals(role)){
            return true;
        }
        else {
            return false;
        }
    }
}
