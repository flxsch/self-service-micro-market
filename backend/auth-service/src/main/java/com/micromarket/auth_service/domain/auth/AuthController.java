package com.micromarket.auth_service.domain.auth;

import com.micromarket.auth_service.domain.auth.dto.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationDTO request) {
        String roleName = "USER";

        KeycloakUserDTO keycloakUser = new KeycloakUserDTO(request.getEmail(), request.getPassword());

        String userId = "";
        // create user in keycloak
        try {
            ResponseEntity<String> response = authService.createUserInKeycloak(keycloakUser, roleName);
            URI location = response.getHeaders().getLocation();
            userId = location.getPath().substring(location.getPath().lastIndexOf("/") + 1);

        } catch (Exception e) {
            // TODO global exception handling to display error message
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }

        // create user in db
        try {
            request.setKeycloakId(userId);
            authService.createUser(request);
        } catch (Exception e) {
            // TODO use kafka to outsource the synchronization between keycloak and db?
            authService.deleteUser(userId);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Registration successful");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO request) {
        try {
            boolean userExists = authService.userExists(request.getEmail());
            if (!userExists) throw new RuntimeException("user doesn't exist");

            TokenDTO token = authService.login(request);
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            // TODO global exception handling to display error message
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
}

