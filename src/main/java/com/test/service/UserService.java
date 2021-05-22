package com.test.service;

import com.test.dto.request.UserRequestDto;
import org.springframework.stereotype.Service;

/**
 * @author rajeshramawat
 * @version 1.0.0
 */
@Service
public interface UserService {

    void createUser(UserRequestDto userRequestDto);

    void updateUser(UserRequestDto userRequestDto);

    UserRequestDto getUserById(Long userId);

    void deleteUser(Long userId);

}
