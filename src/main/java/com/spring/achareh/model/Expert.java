package com.spring.achareh.model;

import com.spring.achareh.model.enumration.AccountStatus;
import com.spring.achareh.model.enumration.Role;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@Entity
public class Expert extends User {
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private AccountStatus status = AccountStatus.waiting;
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] image;
    @ManyToMany
    @JoinTable(
            name = "expert_speciality",
            joinColumns = {@JoinColumn(name = "expert_id")},
            inverseJoinColumns = {@JoinColumn(name = "speciality_id")}
    )
    private Set<Speciality> specialities;
    @Column(name = "balance", columnDefinition = "int")
    private Integer balance;
    @Column(name = "average_score", columnDefinition = "int")
    private Integer averageScore;

    public Expert(){}

    @Builder
    public Expert(String firstName, String lastName, String email, String password, LocalDateTime registerDataTime, Role role, String oneTimePassword, Date otpRequestedTime, Boolean expired, Boolean locked, Boolean credentialsExpired, Boolean enabled, AccountStatus status, byte[] image, Set<Speciality> specialities, Integer balance, Integer averageScore) {
        super(firstName, lastName, email, password, registerDataTime, role, oneTimePassword, otpRequestedTime, expired, locked, credentialsExpired, enabled);
        this.status = status;
        this.image = image;
        this.specialities = specialities;
        this.balance = balance;
        this.averageScore = averageScore;
    }
}

