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
    public Customer(String firstName, String lastName, String email, String password, LocalDateTime registerDataTime, Role role, Integer balance) {
        super(firstName, lastName, email, password, registerDataTime, role);
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + super.getFirstName() + '\'' +
                ", lastName='" + super.getLastName() + '\'' +
                ", email='" + super.getEmail() + '\'' +
                ", password='" + super.getPassword() + '\'' +
                ", registerDataTime=" + super.getRegisterDataTime() +
                ", balance=" + balance +
                '}';
    }
}
