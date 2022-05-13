package com.spring.achareh.model;

import com.spring.achareh.model.base.BaseEntity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "offer",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"expert_id", "order_id"})})
@Entity
public class Offer extends BaseEntity<Integer> {
    @ManyToOne
    @JoinColumn(name = "expert_id")
    private Expert expert;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @CreationTimestamp
    @Column(name = "submit_date_time")
    private LocalDateTime submitDateTime;
    @Column(name = "suggestion_price")
    private Integer suggestionPrice;
    @Column(name = "duration_of_work")
    private Integer durationOfWork;
    @Column(name = "start_work_time")
    private LocalTime startWorkTime;


    @Override
    public String toString() {
        return "Offer{" +
                "expert=" + expert +
                ", expert=" + expert +
                ", order=" + order +
                ", submitDateTime=" + submitDateTime +
                ", suggestionPrice=" + suggestionPrice +
                ", durationOfWork=" + durationOfWork +
                ", startWorkTime=" + startWorkTime +
                '}';
    }
}
