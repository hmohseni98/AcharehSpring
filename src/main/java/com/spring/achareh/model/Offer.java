package com.spring.achareh.model;

import com.spring.achareh.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@Table( name = "offer",
        uniqueConstraints = { @UniqueConstraint( columnNames = { "expert_id", "order_id" } ) } )
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
    @Column(name = "duration_of_work") // hour
    private Integer durationOfWork;
    @Column(name = "start_work_time")
    private LocalTime startWorkTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Offer offer = (Offer) o;
        return Objects.equals(expert, offer.expert) && Objects.equals(order, offer.order) && Objects.equals(submitDateTime, offer.submitDateTime) && Objects.equals(suggestionPrice, offer.suggestionPrice) && Objects.equals(durationOfWork, offer.durationOfWork) && Objects.equals(startWorkTime, offer.startWorkTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), expert, order, submitDateTime, suggestionPrice, durationOfWork, startWorkTime);
    }

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
