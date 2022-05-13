package com.spring.achareh.model;

import com.spring.achareh.model.base.BaseEntity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "comment",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"customer_id", "offer_id"})})
@Entity
public class Comment extends BaseEntity<Integer> {
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;
    @Column(name = "score", columnDefinition = "int")
    private Integer score;
    @Column(name = "description", columnDefinition = "varchar(255)")
    private String description;
    @CreationTimestamp
    @Column(name = "date_time")
    private LocalDateTime submitDateTime;

}
