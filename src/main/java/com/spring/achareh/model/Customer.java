package com.spring.achareh.model;

import com.spring.achareh.model.enumration.Role;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Customer extends User {
    @Column(name = "balance", columnDefinition = "int")
    private Integer balance;

    @Builder
    public Customer(String firstName, String lastName, String email, String password, LocalDateTime registerDataTime, Role role, Boolean expired, Boolean locked, Boolean credentialsExpired, Boolean enabled, Integer balance) {
        super(firstName, lastName, email, password, registerDataTime, role, expired, locked, credentialsExpired, enabled);
        this.balance = balance;
    }
}
