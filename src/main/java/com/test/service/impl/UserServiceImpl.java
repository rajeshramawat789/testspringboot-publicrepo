package com.test.service.impl;

import com.test.dto.request.UserRequestDto;
import com.test.entities.UserAccounts;
import com.test.exception.ApiException;
import com.test.mapper.UserMapper;
import com.test.repository.UserRepository;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author rajeshramawat
 * @version 1.0.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public void createUser(UserRequestDto userRequestDto) {

        Optional<UserAccounts> userOptional = userRepository.findById(userRequestDto.getId());
        if(userOptional.isPresent()){
            throw new ApiException("User Already present.");
        }

        userRepository.save(userMapper.dtoToEntity(userRequestDto));
    }

    public void updateUser(UserRequestDto userRequestDto) {

        Optional<UserAccounts> userOptional = userRepository.findById(userRequestDto.getId());
        if(!userOptional.isPresent()){
            throw new ApiException("User not found.");
        }
        userRepository.save(userMapper.dtoToEntity(userRequestDto));

    }

    @Override
    @Transactional(readOnly = true)
    public UserRequestDto getUserById(Long userId) {

        Optional<UserAccounts> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            return userMapper.entityToDto(userOptional.get());
        }

        throw new ApiException("User not found.");
    }

    @Override
    public void deleteUser(Long userId) {

        try {
            userRepository.deleteById(userId);
        }catch (EmptyResultDataAccessException e){
            throw new ApiException("User not found.");
        }
    }
}
