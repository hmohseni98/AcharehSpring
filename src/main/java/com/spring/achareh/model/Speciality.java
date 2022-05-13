package com.spring.achareh.model;

import com.spring.achareh.model.base.BaseEntity;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
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
}
