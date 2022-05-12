package com.spring.achareh.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import javax.persistence.Entity;

@AllArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@Entity
public class Admin extends User {


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
