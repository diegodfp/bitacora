package com.diavlo.bitacora.projects.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {

    private Long projectId;
    private String projectName;
    private String description;
    private Date startDate;
    private Date endDate;
    private Long departmentId;  
}
