package com.spring.achareh.model;

import com.spring.achareh.model.base.BaseEntity;
import com.spring.achareh.model.enumration.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "orders")
public class Order extends BaseEntity<Integer> {
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "speciality_id")
    private Speciality speciality;
    @ManyToOne
    @JoinColumn(name = "accept_offer_id")
    private Offer acceptOffer;
    @Column(columnDefinition = "varchar(250)")
    private String description;
    @Column(name = "suggestion_price", columnDefinition = "int")
    private Integer suggestionPrice;
    @CreationTimestamp
    @Column(name = "submit_date_time")
    private LocalDateTime submitDateTime;
    @Column(name = "work_date")
    private LocalDate workDate;
    @Column(name = "address", columnDefinition = "varchar(250)")
    private String address;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Order order = (Order) o;
        return Objects.equals(customer, order.customer) && Objects.equals(speciality, order.speciality) && Objects.equals(acceptOffer, order.acceptOffer) && Objects.equals(description, order.description) && Objects.equals(suggestionPrice, order.suggestionPrice) && Objects.equals(submitDateTime, order.submitDateTime) && Objects.equals(workDate, order.workDate) && Objects.equals(address, order.address) && status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), customer, speciality, acceptOffer, description, suggestionPrice, submitDateTime, workDate, address, status);
    }
}
