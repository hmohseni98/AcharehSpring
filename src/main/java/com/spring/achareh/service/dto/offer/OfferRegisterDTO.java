package com.spring.achareh.service.dto.offer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OfferRegisterDTO {
    @Digits(integer = 6, fraction = 0)
    @NotEmpty
    private Integer expertId;
    @Digits(integer = 6, fraction = 0)
    @NotEmpty
    private Integer orderId;
    @Digits(integer = 10, fraction = 0)
    @NotEmpty
    private Integer suggestionPrice;
    @Digits(integer = 2, fraction = 0)
    @NotEmpty
    private Integer durationOfWork;
    @NotEmpty
    private String startWorkTime;
}

