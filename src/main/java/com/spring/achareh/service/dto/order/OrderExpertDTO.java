package com.spring.achareh.service.dto.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderExpertDTO {
    private Integer id;

    private String serviceName;

    private String customerFullName;

    private String description;

    private Integer suggestionPrice;

    private LocalDate date;
}
