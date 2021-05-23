package com.test.dto.request;

import lombok.Data;

import javax.validation.constraints.*;

/**
 * @author rajeshramawat
 * @version 1.0.0
 */
@Data
public class UserRequestDto {

    @NotNull(message = "{user.account.id.not.null}")
    private Long id;

    @NotNull(message = "{user.account.name.not.null}")
    @Size(min = 3, max = 150, message = "{user.account.name.length.invalid}")
    private String name;

    @NotNull(message = "{user.account.phone.not.null}")
    @DecimalMin(value = "100000000", message = "{user.account.phone.length.invalid}")
    @DecimalMax(value = "999999999999", message = "{user.account.phone.length.invalid}")
    private Long phone;


    @NotNull(message = "{user.account.email.not.null}")
    @Email(message = "{user.account.email.invalid}")
    private String email;

    @Size(max = 200, message = "{user.account.address.length.invalid}")
    private String address;

    @NotNull(message = "{user.account.country.not.null}")
    @Size(max = 56, message = "{user.account.country.length.invalid}")
    private String country;


    @NotNull(message = "{user.account.department.not.null}")
    @Size(max = 50, message = "{user.account.department.length.invalid}")
    private String department;
}
