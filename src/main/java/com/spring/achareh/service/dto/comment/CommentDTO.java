package com.spring.achareh.service.dto.comment;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//comment dto for expert
public class CommentDTO {
    private String serviceName;

    private String description;

    private Integer score;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private LocalDateTime submitDate;
}
