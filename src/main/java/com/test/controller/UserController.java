package com.test.controller;

import com.test.dto.request.UserRequestDto;
import com.test.dto.response.SuccessResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author rajeshramawat
 * @version 1.0.0
 */
@Validated
public interface UserController {

    SuccessResponse createUser(@RequestBody @Valid UserRequestDto userRequestDto);

    SuccessResponse updateUser(@RequestBody @Valid UserRequestDto userRequestDto);

    SuccessResponse deleteUser(@NotNull @PathVariable("userId") Long id);

    UserRequestDto getUser(@NotNull @PathVariable("userId") Long id);
}
