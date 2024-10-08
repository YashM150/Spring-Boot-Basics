package com.matthe.ecom.service;


import com.matthe.ecom.model.User;
import com.matthe.ecom.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService  {

    private final UserRepository userRepository;

    public UserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User registerUser(User user) {
        // Check if the user already exists by username
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser == null) {
            // Save and return the newly registered user
            return userRepository.save(user);
        } else {

            throw new RuntimeException("User already exists");
        }
    }
}
