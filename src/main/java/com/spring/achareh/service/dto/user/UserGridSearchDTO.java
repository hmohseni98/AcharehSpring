package com.spring.achareh.service.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserGridSearchDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String userType;
    private Integer pageNo;
    private Integer pageSize;
    private String sortingField;
    private String sortingDirection;

}
