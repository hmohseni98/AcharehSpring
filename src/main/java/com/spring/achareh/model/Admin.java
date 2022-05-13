package com.spring.achareh.model;

import com.spring.achareh.model.enumration.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@Entity
public class Admin extends User {

    @Builder()
    public Admin(String firstName, String lastName, String email, String password, LocalDateTime registerDataTime, Role role) {
        super(firstName, lastName, email, password, registerDataTime, role);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id='" + super.getId() + '\'' +
                ", firstName='" + super.getFirstName() + '\'' +
                ", lastName='" + super.getLastName() + '\'' +
                ", email='" + super.getEmail() + '\'' +
                ", password='" + super.getPassword() + '\'' +
                ", registerData=" + super.getRegisterDataTime() +
                '}';
    }

}
