package com.diavlo.bitacora.departments.domain.services;

import com.diavlo.bitacora.departments.application.dto.DepartmentDTO;
import com.diavlo.bitacora.departments.application.mapper.DepartmentMapper;
import com.diavlo.bitacora.departments.domain.entity.Department;
import com.diavlo.bitacora.departments.infrastructure.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Transactional(readOnly = true)
    public List<DepartmentDTO> findAll() {
        return departmentRepository.findAll()
                .stream()
                .map(DepartmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<DepartmentDTO> findById(Long id) {
        return departmentRepository.findById(id)
                .map(DepartmentMapper::toDto);
    }

    @Transactional
    public DepartmentDTO create(DepartmentDTO departmentDTO) {
        Department department = DepartmentMapper.toEntity(departmentDTO);
        Department savedDepartment = departmentRepository.save(department);
        return DepartmentMapper.toDto(savedDepartment);
    }

    @Transactional
    public Optional<DepartmentDTO> update(Long id, DepartmentDTO departmentDTO) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);

        if (departmentOptional.isPresent()) {
            Department existingDepartment = departmentOptional.get();
            existingDepartment.setDepartmentName(departmentDTO.getDepartmentName());
            existingDepartment.setDescription(departmentDTO.getDescription());
            Department updatedDepartment = departmentRepository.save(existingDepartment);
            return Optional.of(DepartmentMapper.toDto(updatedDepartment));
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    public boolean delete(Long id) {
        if (departmentRepository.existsById(id)) {
            departmentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
