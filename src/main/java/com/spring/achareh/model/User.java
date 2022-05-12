package com.spring.achareh.model;

import com.spring.achareh.model.base.BaseEntity;
import com.spring.achareh.model.enumration.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type" , discriminatorType = DiscriminatorType.STRING)
@Table(name = "users")
@Entity
public abstract class User extends BaseEntity<Integer> {
    @Column(name = "first_name", columnDefinition = "varchar(30)")
    private String firstName;
    @Column(name = "last_name", columnDefinition = "varchar(50)")
    private String lastName;
    @Column(name = "email", unique = true, columnDefinition = "varchar(50)")
    private String email;
    @Column(name = "password", columnDefinition = "varchar(12)")
    private String password;
    @CreationTimestamp
    @Column(name = "register_date_time")
    private LocalDateTime registerDataTime;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(registerDataTime, user.registerDataTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName, email, password, registerDataTime);
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", registerDataTime=" + registerDataTime +
                '}';
    }
}
