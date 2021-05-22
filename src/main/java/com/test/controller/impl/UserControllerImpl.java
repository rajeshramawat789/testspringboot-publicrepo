package com.test.controller.impl;

import com.test.constants.UrlMappingConstant;
import com.test.controller.UserController;
import com.test.dto.request.UserRequestDto;
import com.test.dto.response.SuccessResponse;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author rajeshramawat
 * @version 1.0.0
 */
@RestController
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = UrlMappingConstant.USER)
    @ResponseStatus(HttpStatus.CREATED)
    public SuccessResponse createUser(UserRequestDto userRequestDto) {

        userService.createUser(userRequestDto);
        return new SuccessResponse("User created successfully");
    }

    @PutMapping(value = UrlMappingConstant.UPDATE_USER)
    @ResponseStatus(HttpStatus.OK)
    public SuccessResponse updateUser(@Valid UserRequestDto userRequestDto) {

        userService.updateUser(userRequestDto);
        return new SuccessResponse("User created successfully");
    }

    @DeleteMapping(value = UrlMappingConstant.DELETE_USER)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public SuccessResponse deleteUser(Long id) {

        userService.deleteUser(id);
        return new SuccessResponse("User deleted Successfully");
    }

    @GetMapping(value = UrlMappingConstant.GET_USER)
    public UserRequestDto getUser(Long id) {

        return userService.getUserById(id);
    }

}
