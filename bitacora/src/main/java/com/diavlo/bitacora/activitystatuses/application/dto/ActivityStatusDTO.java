package com.diavlo.bitacora.activitystatuses.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActivityStatusDTO {
    private Long statusId;
    private String statusName;
}
