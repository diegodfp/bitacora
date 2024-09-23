package com.diavlo.bitacora.departments.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diavlo.bitacora.departments.domain.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department,Long>{

}
