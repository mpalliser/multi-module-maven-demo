package com.example.api.controller;

import com.example.model.api.UserDto;
import com.example.model.filter.UserFilter;
import com.example.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = UserController.API_USERS_URL)
public class UserController {

    static final String API_USERS_URL = "users";

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<List<UserDto>> findAll(@RequestBody @Valid UserFilter userFilter) {
        return ResponseEntity.ok(userService.findAll(userFilter));
    }
}
