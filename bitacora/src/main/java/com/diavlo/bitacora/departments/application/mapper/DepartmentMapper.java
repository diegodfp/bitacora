package com.diavlo.bitacora.departments.application.mapper;

import com.diavlo.bitacora.departments.domain.entity.Department;
import com.diavlo.bitacora.departments.application.dto.DepartmentDTO;

public class DepartmentMapper {

    public static DepartmentDTO toDto(Department department) {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setDepartmentId(department.getDepartmentId());
        dto.setDepartmentName(department.getDepartmentName());
        dto.setDescription(department.getDescription());
        return dto;
    }

    public static Department toEntity(DepartmentDTO dto) {
        return Department.builder()
                .departmentId(dto.getDepartmentId())
                .departmentName(dto.getDepartmentName())
                .description(dto.getDescription())
                .build();
    }
}
