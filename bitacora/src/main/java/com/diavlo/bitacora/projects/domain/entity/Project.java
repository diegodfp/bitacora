package com.diavlo.bitacora.projects.domain.entity;



import java.sql.Date;

import com.diavlo.bitacora.common.domain.entities.TimeCreateUpdate;
import com.diavlo.bitacora.deparments.domain.entity.Department;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.ForeignKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "projects", uniqueConstraints = @UniqueConstraint(columnNames = "project_name"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    @Column(name = "project_name", nullable = false, length = 100)
    private String projectName;

    @Column(columnDefinition = "text")
    private String description;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false, foreignKey = @ForeignKey(name = "fk_project_department"))
    private Department department;

    @Embedded
    private TimeCreateUpdate timeCreateUpdate;


}
