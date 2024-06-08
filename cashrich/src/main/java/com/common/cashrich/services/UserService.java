package com.common.cashrich.services;

import com.common.cashrich.dao.Response;
import com.common.cashrich.entity.User;
import com.common.cashrich.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Response<String> signUpUser(User user) {
        System.out.println(user);
//        return userRepository.save(user);
        if (user == null || user.getEmail() == null || user.getEmail().isEmpty()) {
            return new Response<>(400, "Bad Request", "Invalid user or email.");
        }

        Optional<User> existingUser = Optional.ofNullable(userRepository.findByEmail(user.getEmail()));
        if (existingUser.isPresent()) {
            return new Response<>(409, "Conflict", "Email is already in use. Try with another email.");
        }

        userRepository.save(user);
        return new Response<>(201, "Created", "User signed up successfully.");
    }

    public User loginUser(String username, String password) {
        // Logic for user login
        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));
        if(user.isPresent() && user.get().getPassword().equals(password)) {
            return user.get();
        }
        return null;
    }

    public User updateUser(Long userId, User userDetails) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) {
            User existingUser = user.get();
            existingUser.setFirstName(userDetails.getFirstName());
            existingUser.setLastName(userDetails.getLastName());
            existingUser.setMobile(userDetails.getMobile());
            existingUser.setPassword(userDetails.getPassword());
            return userRepository.save(existingUser);
        }
        return null;
    }
}
