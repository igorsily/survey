package com.br.igorsily.api.controller;

import com.br.igorsily.api.model.User;
import com.br.igorsily.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(UserController.REQUEST_MAPPING)
public class UserController {

    public static final String REQUEST_MAPPING = "users";

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody User user) {

        var userCreated = this.userService.createUser(user);

        return new ResponseEntity<User>(userCreated, HttpStatus.CREATED);

    }

}
