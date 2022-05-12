package com.spring.achareh.model;

import com.spring.achareh.model.enumration.AccountStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
public class Expert extends User {
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private AccountStatus status = AccountStatus.waiting;
    private byte[] image;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "expert_speciality",
            joinColumns = { @JoinColumn(name = "expert_id") },
            inverseJoinColumns = { @JoinColumn(name = "speciality_id") }
    )
    private Set<Speciality> specialities;
    @Column(name = "balance" , columnDefinition = "int default 0")
    private Integer balance;
    @Column(name = "average_score" , columnDefinition = "int default 0")
    private Integer averageScore;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Expert expert = (Expert) o;
        return status == expert.status && Arrays.equals(image, expert.image) && Objects.equals(specialities, expert.specialities) && Objects.equals(balance, expert.balance) && Objects.equals(averageScore, expert.averageScore);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), status, specialities, balance, averageScore);
        result = 31 * result + Arrays.hashCode(image);
        return result;
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

