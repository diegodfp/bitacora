
package com.diavlo.bitacora.activitychangestatus.domain.entity;

import com.diavlo.bitacora.common.domain.entities.TimeCreateUpdate;

import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityChangeStatusDTO {
    private Long activity_id;
    @Embedded
    TimeCreateUpdate timeCreateUpdate = new TimeCreateUpdate();
    private Long changed_by_user_id;
    private Long status_id;
    private String change_comment;
}
