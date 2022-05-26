package com.spring.achareh.service.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderRegisterDTO {
    @NotEmpty
    @Digits(integer = 6, fraction = 0)
    private Integer customerId;

    @NotEmpty
    @Digits(integer = 6, fraction = 0)
    private Integer specialityId;

    @NotEmpty
    @Size(min = 10, max = 250)
    private String description;

    @NotEmpty
    @Digits(integer = 6, fraction = 0)
    private Integer suggestionPrice;

    @NotEmpty
    @Future
    private LocalDate workDate;

    @Size(min = 10, max = 250)
    private String address;
}
