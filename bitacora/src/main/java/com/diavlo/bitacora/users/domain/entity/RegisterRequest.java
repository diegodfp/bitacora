package com.diavlo.bitacora.users.domain.entity;


import com.diavlo.bitacora.roles.domain.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    String username;
    String password;
    private Role role;  // Aquí está el rol como una entidad completa
}
