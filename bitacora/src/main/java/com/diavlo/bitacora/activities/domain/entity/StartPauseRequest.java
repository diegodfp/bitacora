package com.diavlo.bitacora.activities.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StartPauseRequest {

    private Long userId;
    private Long projectId;

}
