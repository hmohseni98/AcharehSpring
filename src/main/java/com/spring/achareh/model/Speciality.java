package com.spring.achareh.model;

import com.spring.achareh.model.base.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@ToString
@Entity
public class Speciality extends BaseEntity<Integer> {
    @Column(unique = true, columnDefinition = "varchar(100)")
    private String name;
    @Column(columnDefinition = "varchar(1000)")
    private String description;
    @Column(name = "base_price", columnDefinition = "int")
    private Integer basePrice;
    @ManyToOne
    @JoinColumn(name = "category_id", columnDefinition = "int")
    private Category category;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Speciality speciality = (Speciality) o;
        return Objects.equals(name, speciality.name) && Objects.equals(description, speciality.description) && Objects.equals(basePrice, speciality.basePrice) && Objects.equals(category, speciality.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, basePrice, category);
    }
}
