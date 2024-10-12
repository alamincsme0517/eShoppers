package com.alamin.eshoppers.service;

import com.alamin.eshoppers.dto.UserDto;

public interface UserService {
    void saveUser(UserDto userDto);
    boolean isNotUniqueUserName(UserDto userDto) ;
}
