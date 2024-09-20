package com.diavlo.bitacora.roles.domain.entity;

import com.diavlo.bitacora.common.domain.entities.TimeCreateUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(name = "role_name", columnDefinition = "varchar(50)", nullable = false)
    private String roleName;

    @Column(columnDefinition = "text")
    private String description;

    @Embedded
    private TimeCreateUpdate timeCreateUpdate;


}
