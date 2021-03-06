package com.spring.achareh.service.dto.user;

import com.spring.achareh.model.enumration.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private Role role;

}
