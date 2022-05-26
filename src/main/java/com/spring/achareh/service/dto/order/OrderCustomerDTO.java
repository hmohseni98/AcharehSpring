package com.spring.achareh.service.dto.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spring.achareh.model.enumration.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderCustomerDTO {
    private Integer id;

    private String serviceName;

    private String expertFullName;

    private String description;

    private Integer suggestionPrice;

    private OrderStatus status;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private LocalDateTime submitDate;

    private LocalDate workDate;
}
