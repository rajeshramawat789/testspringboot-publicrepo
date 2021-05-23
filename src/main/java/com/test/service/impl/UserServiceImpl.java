package com.test.service.impl;

import com.test.dto.request.UserRequestDto;
import com.test.entities.UserAccounts;
import com.test.exception.ApiException;
import com.test.mapper.UserMapper;
import com.test.repository.UserRepository;
import com.test.service.UserService;
import com.test.util.MessageUtil;
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
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MessageUtil messageUtil;

    public void createUser(UserRequestDto userRequestDto) {

        Optional<UserAccounts> userOptional = userRepository.findById(userRequestDto.getId());
        if(userOptional.isPresent()){
            throw new ApiException(messageUtil.getMessage("user.already.exists"));
        }

        userRepository.save(userMapper.dtoToEntity(userRequestDto));
    }

    public void updateUser(UserRequestDto userRequestDto) {

        Optional<UserAccounts> userOptional = userRepository.findById(userRequestDto.getId());
        if(!userOptional.isPresent()){
            throw new ApiException(messageUtil.getMessage("user.not.exists"));
        }
        userRepository.save(userMapper.dtoToEntity(userRequestDto));

    }

    @Override
    public UserRequestDto getUserById(Long userId) {

        Optional<UserAccounts> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            return userMapper.entityToDto(userOptional.get());
        }

        throw new ApiException(messageUtil.getMessage("user.not.exists"));
    }

    @Override
    public void deleteUser(Long userId) {

        try {
            userRepository.deleteById(userId);
        }catch (EmptyResultDataAccessException e){
            throw new ApiException(messageUtil.getMessage("user.not.exists"));
        }
    }
}
