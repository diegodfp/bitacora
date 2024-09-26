package com.diavlo.bitacora.priorities.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriorityDTO {
    private Long priorityId;
    private String priorityLevel;
    private String description;
}