package com.test.dto.request;

import lombok.Data;

import javax.validation.constraints.*;

/**
 * @author rajeshramawat
 * @version 1.0.0
 */
@Data
public class UserRequestDto {

    @NotNull(message = "Id should not be null.")
    private Long id;

    @NotNull(message = "Name should not be null or empty.")
    @Size(min = 3, max = 150, message = "Name should contains minimum 3 and maximum 150 characters.")
    private String name;

    @NotNull(message = "Phone number should not be null or empty.")
    @DecimalMin(value = "100000000", message = "Phone number should be between 9 to 12 digits length.")
    @DecimalMax(value = "999999999999", message = "Phone number should be between 9 t0 12 digits length.")
    private Long phone;


    @NotNull(message = "Email address should not be null or empty.")
    @Email(message = "Email is invalid.")
    private String email;

    @Size(max = 200, message = "Address should not be more than 200 characters.")
    private String address;

    @NotNull(message = "Country should not be null or empty.")
    @Size(max = 56, message = "Country should not be more than 56 characters.")
    private String country;


    @NotNull(message = "Department should not be null or empty.")
    @Size(max = 50, message = "Department should not be more than 50 characters.")
    private String department;
}
