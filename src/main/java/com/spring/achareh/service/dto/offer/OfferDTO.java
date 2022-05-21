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

    private Integer durationOfWork;

    private LocalDate workDate;

    private LocalTime startWorkTime;

    private Integer expertSuggestionPrice;

    private Integer expertScore;
}
