package com.spring.achareh.service.dto.speciality;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SpecialityDTO {
    private Integer id;

    private String name;

    private Integer basePrice;

    private String description;

    private String categoryName;
}
