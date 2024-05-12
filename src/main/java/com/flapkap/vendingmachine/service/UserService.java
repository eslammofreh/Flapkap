package com.flapkap.vendingmachine.service;

import com.flapkap.vendingmachine.model.User;
import com.flapkap.vendingmachine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() throws Exception {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Error occurred while fetching users: " + e.getMessage());
        }
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User updatedUser) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setUsername(updatedUser.getUsername());
            user.setPassword(updatedUser.getPassword());
            user.setDeposit(updatedUser.getDeposit());
            user.setRole(updatedUser.getRole());
            return userRepository.save(user);
        } else {
            System.out.println("UserNotFoundException");
            return null;
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User depositCoins(Long userId, double amount) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setDeposit(user.getDeposit() + amount);
            return userRepository.save(user);
        } else {
            System.out.println("UserNotFoundException");
            return null;
        }
    }

    public User resetDeposit(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setDeposit(0);
            return userRepository.save(user);
        } else {
            System.out.println("UserNotFoundException");
            return null;
        }
    }
}
