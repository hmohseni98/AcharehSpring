package com.spring.achareh.service.dto.customer;

import com.spring.achareh.util.customValidation.password.ValidPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CustomerRegisterDTO {
    @NotBlank
    @Size(min = 3, max = 30)
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 50)
    private String lastName;

    @Email
    private String email;

    @ValidPassword
    private String password;
}
