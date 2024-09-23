package com.diavlo.bitacora.users.domain.services;



import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.diavlo.bitacora.departments.domain.entity.Department;
import com.diavlo.bitacora.jwt.JwtService;
import com.diavlo.bitacora.users.domain.entity.AuthResponse;
import com.diavlo.bitacora.users.domain.entity.LoginRequest;
import com.diavlo.bitacora.users.domain.entity.RegisterRequest;
import com.diavlo.bitacora.users.domain.entity.User;
import com.diavlo.bitacora.users.infrastructure.repositories.UserRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public AuthResponse login(LoginRequest request) {
        // Autenticar al usuario
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        // Obtener los detalles del usuario
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();

        // Generar el token JWT
        String token = jwtService.getToken(user);

        // Devolver el token junto con el rol del usuario
        return AuthResponse.builder()
            .token(token)
            .role(user.getRole().name()) 
            .build();
    }

    // Método para registrar al usuario
    public AuthResponse register(RegisterRequest request) {
    // Verificar que el rol esté presente en la solicitud
    if (request.getRole() == null) {
        throw new IllegalArgumentException("El rol es requerido");
    }
    
    if (request.getDepartmentId() == null) {
        throw new IllegalArgumentException("El ID del departamento es requerido");
    }

    // Crear el usuario con los campos proporcionados en la solicitud
    User user = User.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .email(request.getEmail())
            .fullName(request.getFullName())
            .department(new Department(request.getDepartmentId(), null, null, null))  // Crear un nuevo objeto de departamento con el ID
            .role(request.getRole())
            .build();

    // Guardar el usuario en la base de datos
    userRepository.save(user);

    // Devolver el token junto con el rol del usuario
    return AuthResponse.builder()
            .token(jwtService.getToken(user))
            .role(user.getRole().name())
            .build();
}

}
