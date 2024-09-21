package com.diavlo.bitacora.deparments.Infraestructure.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diavlo.bitacora.deparments.domain.entity.Department;

public interface DeparmentsRepository extends JpaRepository<Department,Long>{

}
