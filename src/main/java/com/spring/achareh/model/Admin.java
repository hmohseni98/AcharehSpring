package com.spring.achareh.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
import javax.persistence.Entity;

@AllArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
public class Admin extends Person {


    @Override
    public String toString() {
        return "Person{" +
                "id='" + super.getId() + '\'' +
                ", firstName='" + super.getFirstName() + '\'' +
                ", lastName='" + super.getLastName() + '\'' +
                ", email='" + super.getEmail() + '\'' +
                ", password='" + super.getPassword() + '\'' +
                ", registerData=" + super.getRegisterDataTime() +
                '}';
    }
}
