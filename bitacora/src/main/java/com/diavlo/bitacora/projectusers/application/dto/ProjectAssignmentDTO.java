package com.diavlo.bitacora.projectusers.application.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectAssignmentDTO {
    private Long userId;
    private Long projectId;
    private int isLeader;
}
