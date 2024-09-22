package com.diavlo.bitacora.deparments.Infraestructure.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diavlo.bitacora.deparments.Application.DeparmentService;
import com.diavlo.bitacora.deparments.domain.entity.Department;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DeparmentAdapter implements DeparmentService {
    @Autowired
    private DeparmentsRepository deparmentsRepository;

    @Override
    public List<Department> findAll() {
        return deparmentsRepository.findAll();
    }

    @Override
    public Optional<Department> findByid(Long Id) {
        return deparmentsRepository.findById(Id);
    }

    @Override
    public Optional<Department> update(Long id, Department department) {
        Optional<Department> id_find_departmen = deparmentsRepository.findById(id);
        if (id_find_departmen.isPresent()) {

            // departamento existente keep mind
            Department Updatedepartment = id_find_departmen.get();

            Updatedepartment.setDepartmentName(department.getDepartmentName());
            Updatedepartment.setDescription(department.getDescription());
            Updatedepartment.setTimeCreateUpdate(department.getTimeCreateUpdate());

            return Optional.of(deparmentsRepository.save(Updatedepartment));
        } else {
            // Si no se encuentra el departamento, retornamos un Optional vacío
            return Optional.empty();
        }
    }

    @Override
    public void deleteById(Long id) {
       Optional<Department> id_find_departmen = deparmentsRepository.findById(id);
       if (id_find_departmen.isPresent()) {
            deparmentsRepository.deleteById(id);
       }else{
            throw new EntityNotFoundException("Department with id " + id + " not found.");
       }
    }

    @Override
    public Optional<Department> create(Department department) {
        try {
           
            Department savedDepartment = deparmentsRepository.save(department);
           
            return Optional.of(savedDepartment);
        } catch (Exception e) {
            // En caso de cualquier error, devolver Optional vacío
            return Optional.empty();
        }
    }
}
