package com.spring.achareh.model;

import com.spring.achareh.model.enumration.AccountStatus;
import com.spring.achareh.model.enumration.Role;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@Entity
public class Expert extends User {
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private AccountStatus status = AccountStatus.waiting;
    private byte[] image;
    @ManyToMany
    @JoinTable(
            name = "expert_speciality",
            joinColumns = {@JoinColumn(name = "expert_id")},
            inverseJoinColumns = {@JoinColumn(name = "speciality_id")}
    )
    private Set<Speciality> specialities;
    @Column(name = "balance", columnDefinition = "int default 0")
    private Integer balance;
    @Column(name = "average_score", columnDefinition = "int default 0")
    private Integer averageScore;

    public Expert(){}

    @Builder
    public Expert(String firstName, String lastName, String email, String password, LocalDateTime registerDataTime, Role role, AccountStatus status, byte[] image, Set<Speciality> specialities, Integer balance, Integer averageScore) {
        super(firstName, lastName, email, password, registerDataTime, role);
        this.status = status;
        this.image = image;
        this.specialities = specialities;
        this.balance = balance;
        this.averageScore = averageScore;
    }

    @Override
    public String toString() {
        return "Expert{" +
                "firstName='" + super.getFirstName() + '\'' +
                ", lastName='" + super.getLastName() + '\'' +
                ", email='" + super.getEmail() + '\'' +
                ", password='" + super.getPassword() + '\'' +
                ", registerDataTime=" + super.getRegisterDataTime() +
                ", status=" + status +
                ", image=" + Arrays.toString(image) +
                ", specialities=" + specialities +
                ", balance=" + balance +
                ", averageScore=" + averageScore +
                '}';
    }

}

