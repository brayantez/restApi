package com.rest.api.controllers;

import com.rest.api.dtos.CreateUserDTO;
import com.rest.api.models.User;
import com.rest.api.services.UserService;
import com.rest.api.utils.ApiResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create-user")
    public ResponseEntity<ApiResponse<User>> registerUser(@RequestBody @Valid CreateUserDTO createUserDTO){
        LOGGER.info("Request for user creation : {}", createUserDTO);
        ApiResponse<User> apiResponse = new ApiResponse<>();
        User user = userService.createUser(createUserDTO);
        if(user == null){
            apiResponse.setStatus(0);
            apiResponse.setMessage("User creation Failed");
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
        apiResponse.setData(user);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }
}
