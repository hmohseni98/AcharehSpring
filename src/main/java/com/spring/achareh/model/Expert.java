package com.spring.achareh.model;

import com.spring.achareh.model.enumration.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    private byte[] image;
    @ManyToMany
    @JoinTable(
            name = "expert_speciality",
            joinColumns = { @JoinColumn(name = "expert_id") },
            inverseJoinColumns = { @JoinColumn(name = "speciality_id") }
    )
    private Set<Speciality> specialities;
    @Column(name = "balance" , columnDefinition = "int default 0")
    private Integer balance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Expert expert = (Expert) o;
        return status == expert.status && Arrays.equals(image, expert.image) && Objects.equals(specialities, expert.specialities) && Objects.equals(balance, expert.balance);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), status, specialities, balance);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }
}
