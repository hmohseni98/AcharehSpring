package com.spring.achareh.service.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserChangePasswordDTO {
    private Integer id;
    private String oldPassword;
    private String newPassword;
}
