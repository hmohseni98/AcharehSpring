package com.spring.achareh.service.dto.offer;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OfferDTO {
    private Integer id;

    private String expertFullName;

    private String serviceName;

    private Integer durationOfWork;

    private String orderDescription;

    private LocalDate workDate;

    private Integer expertSuggestionPrice;

}
