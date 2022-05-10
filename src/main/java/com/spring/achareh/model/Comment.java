package com.spring.achareh.model;

import com.spring.achareh.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "comment",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"customer_id", "order_id"})})
@Entity
public class Comment extends BaseEntity<Integer> {
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "expert_id")
    private Expert expert;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @Column(name = "score", columnDefinition = "int")
    private Integer score;
    @Column(name = "description", columnDefinition = "varchar(255)")
    private String description;
    @Column(name = "date_time")
    private LocalDateTime submitDateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Comment comment = (Comment) o;
        return Objects.equals(customer, comment.customer) && Objects.equals(expert, comment.expert) && Objects.equals(order, comment.order) && Objects.equals(score, comment.score) && Objects.equals(description, comment.description) && Objects.equals(submitDateTime, comment.submitDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), customer, expert, order, score, description, submitDateTime);
    }
}
