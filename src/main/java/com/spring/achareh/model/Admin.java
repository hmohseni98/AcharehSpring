package com.spring.achareh.model;

import com.spring.achareh.model.enumration.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@Entity
public class Admin extends User {


    @Builder

    public Admin(String firstName, String lastName, String email, String password, LocalDateTime registerDataTime, Role role, String oneTimePassword, Date otpRequestedTime, Boolean expired, Boolean locked, Boolean credentialsExpired, Boolean enabled) {
        super(firstName, lastName, email, password, registerDataTime, role, oneTimePassword, otpRequestedTime, expired, locked, credentialsExpired, enabled);
    }
}
