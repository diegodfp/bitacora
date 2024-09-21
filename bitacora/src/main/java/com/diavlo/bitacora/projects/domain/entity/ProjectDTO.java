package com.diavlo.bitacora.projects.domain.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProjectDTO {
    private String project_name;
    private String description;
    private Long department_id;
    private Date end_date;
    private Date start_date;
}
