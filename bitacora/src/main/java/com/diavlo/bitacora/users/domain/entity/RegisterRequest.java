package com.diavlo.bitacora.users.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private String fullName;
    private Long departmentId; // ID del departamento
    private Role role; // Si tienes un objeto Role, mantén esto
}
