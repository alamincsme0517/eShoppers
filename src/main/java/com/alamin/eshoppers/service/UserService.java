package com.alamin.eshoppers.service;

import com.alamin.eshoppers.domain.User;
import com.alamin.eshoppers.dto.LoginDto;
import com.alamin.eshoppers.dto.UserDto;

public interface UserService {
    void saveUser(UserDto userDto);
    boolean isNotUniqueUserName(UserDto userDto) ;

    boolean isNotUniqueEmail(UserDto userDto);

    User varifyUser(LoginDto loginDto);
}
