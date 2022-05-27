package com.spring.achareh.service.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentRegisterDTO {
    private Integer customerId;

    private Integer offerId;

    private Integer score;

    private String description;
}
