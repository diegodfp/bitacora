package com.diavlo.bitacora.users.domain.services;

import com.diavlo.bitacora.users.domain.entity.User;
import com.diavlo.bitacora.users.infrastructure.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> findUsersByDepartmentId(Long departmentId) {
        return userRepository.findByDepartment_DepartmentId(departmentId);
    }
}
