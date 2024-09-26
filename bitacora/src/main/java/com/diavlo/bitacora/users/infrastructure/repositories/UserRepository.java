package com.diavlo.bitacora.users.infrastructure.repositories;

import java.util.*;


import org.springframework.data.jpa.repository.JpaRepository;

import com.diavlo.bitacora.users.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username); 
    List<User> findByDepartment_DepartmentId(Long departmentId); 
}
