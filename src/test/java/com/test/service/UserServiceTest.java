package com.test.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.test.ApplicationTest;
import com.test.dto.request.UserRequestDto;
import com.test.entities.UserAccounts;
import com.test.exception.ApiException;
import com.test.mapper.UserMapper;
import com.test.repository.UserRepository;
import com.test.service.impl.UserServiceImpl;
import com.test.util.MessageUtil;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

/**
 * @author rajeshramawat
 * @version 1.0.0
 */
@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
public class UserServiceTest extends ApplicationTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private MessageUtil messageUtil;

    @Before
    public void setup() throws IOException {
        Mockito.when(userRepository.findById(this.getId())).thenReturn(Optional.empty());
        Mockito.when(userMapper.dtoToEntity(this.getUserRequestDto())).thenReturn(this.getUserAccount());
        Mockito.when(userRepository.save(this.getUserAccount())).thenReturn(this.getUserAccount());
    }

    @Test
    public void testCreateUser() throws IOException {
        userService.createUser(getUserRequestDto());
        assert true;
    }

    @Test(expected = ApiException.class)
    public void testCreateUserAlreadyExistsException() throws IOException {
        Mockito.when(userRepository.findById(this.getId())).thenReturn(getUserAccountOptional());
        userService.createUser(getUserRequestDto());
        assert true;
    }

    @Test(expected = ApiException.class)
    public void testUpdateUserUserNotFoundException() throws IOException {
        Mockito.when(userRepository.findById(this.getId())).thenReturn(Optional.empty());
        userService.updateUser(getUserRequestDto());
        assert true;
    }

    @Test
    public void testUpdateUser() throws IOException {
        Mockito.when(userRepository.findById(this.getId())).thenReturn(this.getUserAccountOptional());
        userService.updateUser(getUserRequestDto());
        assert true;
    }

    @Test
    public void testGetUserById() throws IOException {

        Mockito.when(userRepository.findById(this.getId())).thenReturn(getUserAccountOptional());
        Mockito.when(userMapper.entityToDto(this.getUserAccount())).thenReturn(this.getUserRequestDto());

        UserRequestDto userById = userService.getUserById(getId());
        Assertions.assertThat(userById).isEqualTo(this.getUserRequestDto());
    }

    @Test(expected = ApiException.class)
    public void testGetUserByIdUserNotFoundException() throws IOException {

        Mockito.when(userRepository.findById(this.getId())).thenReturn(Optional.empty());

        UserRequestDto userById = userService.getUserById(getId());
        Assertions.assertThat(userById).isEqualTo(this.getUserRequestDto());
    }

    @Test
    public void deleteUser() {

        Mockito.doNothing().when(userRepository).deleteById(getId());
        userService.deleteUser(getId());
        assert true;
    }

    private Optional<UserAccounts> getUserAccountOptional() throws IOException {
        return Optional.of(getUserAccount());
    }

    private UserAccounts getUserAccount() throws IOException {
        return this.getUserResponse();
    }

    private UserAccounts getUserResponse() throws IOException {
        return getObjectFromJson("CREATE_USER_ACCOUNT_ENTITY_1.json",
                new TypeReference<UserAccounts>() {
                });
    }

    private Long getId() {
        return 1l;
    }

    private UserRequestDto getUserRequestDto() throws IOException {
        return getObjectFromJson("CREATE_USER_ACCOUNT_DTO_WITH_ID_1.json",
                new TypeReference<UserRequestDto>() {
                });
    }

    public static <T> T getObjectFromJson(final String fileName, final TypeReference<T> typeReference)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, Boolean.FALSE);
        return mapper.readValue(new FileReader(Test.class.getClassLoader().getResource(fileName).getFile()),
                typeReference);
    }
}
