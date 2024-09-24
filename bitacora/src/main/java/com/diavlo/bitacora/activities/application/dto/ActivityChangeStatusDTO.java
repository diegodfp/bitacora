
package com.diavlo.bitacora.activities.application.dto;




import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;

import lombok.Data;

import lombok.Builder;



@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityChangeStatusDTO {
    private Long activity_id;
    private Long changed_by_user_id;
    private Long status_id;
    private String change_comment;
}
