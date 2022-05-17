package com.spring.achareh.service.dto.expert;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExpertRegisterDTO {
    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private MultipartFile image;
}
