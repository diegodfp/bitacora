package com.diavlo.bitacora.deparments.Application;
import java.util.List;
import java.util.Optional;


import com.diavlo.bitacora.deparments.domain.entity.Department;


public interface DeparmentService {
    
    List<Department> findAll();
    Optional<Department> findByid(Long Id);
    Optional<Department> update(Long id, Department department);
    void deleteById(Long id);
    Optional<Department> create(Department department);


}
