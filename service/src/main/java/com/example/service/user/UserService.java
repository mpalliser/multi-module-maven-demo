package com.example.service.user;

import com.example.model.api.UserDto;
import com.example.model.filter.UserFilter;

import java.util.List;

public interface UserService {

    List<UserDto> findAll();

    List<UserDto> findAll(UserFilter userFilter);
}
