package com.alamin.eshoppers.service;

import com.alamin.eshoppers.domain.User;
import com.alamin.eshoppers.dto.LoginDto;
import com.alamin.eshoppers.dto.UserDto;
import com.alamin.eshoppers.exceptions.UserNotFoundException;
import com.alamin.eshoppers.repository.UserRepository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserServiceImpl implements UserService{

    private final UserRepository userRepository ;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();

        String encrypted = encryptedPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(encrypted);
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());

        userRepository.save(user);
    }

    @Override
    public boolean isNotUniqueUserName(UserDto user) {
        return userRepository.findByUsername(user.getUsername()).isPresent();
    }

    @Override
    public boolean isNotUniqueEmail(UserDto userDto) {
        return userRepository.findByEmail(userDto.getEmail()).isPresent();
    }

    @Override
    public User varifyUser(LoginDto loginDto) {
        var user = userRepository.findByUsername(loginDto.getUsername())
                .orElseThrow(() ->new UserNotFoundException("User not found by " + loginDto.getUsername()));
        var encrypted = encryptedPassword(loginDto.getPassword());

        if (user.getPassword().equals(encrypted)) {
            return  user ;
        } else {
            throw new UserNotFoundException("Incorrect username or password");
        }
    }

    private String encryptedPassword(String password) {
        try {
            var digest = MessageDigest.getInstance("SHA-256");
            var bytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            return bytesToHex(bytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Unable to encrypt passward", e);
        }
    }

    private String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();

        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);

            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
