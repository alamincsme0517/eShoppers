package com.alamin.eshoppers.service;

import com.alamin.eshoppers.domain.User;
import com.alamin.eshoppers.dto.UserDto;
import com.alamin.eshoppers.repository.UserRepository;

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
    public boolean isNotUniqueUserName(UserDto userDto) {
        return userRepository.findByUsername(userDto.getUsername()).isPresent();
    }

    private String encryptedPassword(String password) {
        return password ;
    }
}
