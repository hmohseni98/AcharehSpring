package com.spring.achareh.model;

import com.spring.achareh.model.base.BaseEntity;
import lombok.*;
import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Category extends BaseEntity<Integer> {
    @Column(columnDefinition = "varchar(50)")
    private String name;

}
