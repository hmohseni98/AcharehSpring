package com.spring.achareh.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@Entity
public class Customer extends User {
    @Column(name = "balance", columnDefinition = "int default 0")
    private Integer balance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(balance, customer.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), balance);
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
