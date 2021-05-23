package com.test.service;

import com.test.dto.request.UserRequestDto;

/**
 * @author rajeshramawat
 * @version 1.0.0
 */
public interface UserService {

    void createUser(UserRequestDto userRequestDto);

    void updateUser(UserRequestDto userRequestDto);

    UserRequestDto getUserById(Long userId);

    void deleteUser(Long userId);

}
