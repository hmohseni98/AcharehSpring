package com.spring.achareh.model;

import com.spring.achareh.model.base.BaseEntity;
import com.spring.achareh.model.enumration.OrderStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "orders")
@Entity
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

}
