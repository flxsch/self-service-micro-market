package com.micromarket.auth_service.domain.auth;

import com.micromarket.auth_service.domain.auth.dto.*;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final RestTemplate restTemplate;

    @Value("${keycloak.admin.url}")
    private String keycloakUrl;
    @Value("${core-service.url}")
    private String coreServiceUrl;
    @Value("${keycloak.realm}")
    private String keycloakRealmName;
    @Value("${keycloak.client.secret}")
    private String keycloakClientSecret;
    @Value("${keycloak.client.id}")
    private String keycloakClientId;
    @Value("${keycloak.client.name}")
    private String keycloakClientName;

    public ResponseEntity<String> createUserInKeycloak(KeycloakUserDTO request, String roleName) throws JSONException {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(getAdminAccessToken());
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> user = new HashMap<>();
        user.put("enabled", true);
        user.put("email", request.getEmail());
        user.put("username", request.getEmail());
        user.put("credentials", List.of(Map.of(
                "type", "password",
                "value", request.getPassword(),
                "temporary", false
        )));

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(user, headers);

        // create user
        ResponseEntity<String> response = restTemplate.postForEntity(
                keycloakUrl + String.format("/admin/realms/%s/users", keycloakRealmName),
                entity,
                String.class
        );

        // get id of user from response
        URI locationUri = response.getHeaders().getLocation();
        String userId = locationUri.getPath().substring(locationUri.getPath().lastIndexOf('/') + 1);

        // assign role to user
        assignClientRoleToUser(userId, keycloakClientId, roleName);

        return response;
    }

    private void assignClientRoleToUser(String userId, String clientId, String roleName) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(getAdminAccessToken());
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 1. Get all client roles for the specific client
        ResponseEntity<RoleDTO[]> roleResp = restTemplate.exchange(
                keycloakUrl + String.format("/admin/realms/%s/clients/%s/roles", keycloakRealmName, clientId),
                HttpMethod.GET,
                new HttpEntity<>(headers),
                RoleDTO[].class
        );

        RoleDTO[] roles = roleResp.getBody();
        if (roles == null || roles.length == 0) {
            throw new RuntimeException("No client roles found for client with ID: " + clientId);
        }

        // 2. Find the specific role by name (e.g., "USER")
        RoleDTO roleToAssign = null;
        for (RoleDTO role : roles) {
            if (role.getName().equals(roleName)) {
                roleToAssign = role;
                break;
            }
        }

        if (roleToAssign == null) {
            throw new RuntimeException("Role " + roleName + " not found for client with ID: " + clientId);
        }

        // 3. Assign the found role to the user
        HttpEntity<List<RoleDTO>> assignRoleEntity = new HttpEntity<>(List.of(roleToAssign), headers);

        restTemplate.postForEntity(
                keycloakUrl + String.format("/admin/realms/%s/users/%s/role-mappings/clients/%s", keycloakRealmName, userId, clientId),
                assignRoleEntity,
                Void.class
        );
    }

    public String getAdminAccessToken() throws JSONException, RuntimeException {
        // Keycloak token endpoint
        String tokenUrl = String.format("%s/realms/master/protocol/openid-connect/token", keycloakUrl) ;

        // Prepare headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Prepare body
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("username", "admin");
        body.add("password", "admin");
        body.add("grant_type", "password");
        body.add("client_id", "admin-cli");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        // Send POST request
        ResponseEntity<String> response = restTemplate.postForEntity(tokenUrl, request, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            String responseBody = response.getBody();
            JSONObject json = new JSONObject(responseBody);
             return json.getString("access_token");
        } else {
            throw new RuntimeException("couldn't fetch admin keycloak access token");
        }

    }

    public TokenDTO login(LoginDTO request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "password");
        body.add("username", request.getEmail());
        body.add("password", request.getPassword());
        body.add("client_id", String.format("%s", keycloakClientName));
        body.add("client_secret", String.format("%s", keycloakClientSecret));

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<TokenDTO> response = restTemplate.exchange(
                String.format("%s/realms/%s/protocol/openid-connect/token", keycloakUrl, keycloakRealmName),
                HttpMethod.POST,
                entity,
                TokenDTO.class
        );

        return response.getBody();
    }

    public void createUser(RegistrationDTO request) {
        restTemplate.postForEntity(
                String.format("%s/users", coreServiceUrl),
                request,
                Void.class
        );
    }

    public void deleteUser(String userId) {
        String url = String.format("%s/admin/realms/%s/users/%s", keycloakUrl, keycloakRealmName, userId);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(getAdminAccessToken());

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class);
    }

    public boolean userExists(String email) {

        try {
            ResponseEntity<RegistrationDTO> response = restTemplate.getForEntity(
                    String.format("%s/users/email/%s", coreServiceUrl, email),
                    RegistrationDTO.class
            );
            return response.getStatusCode() == HttpStatus.OK;
        } catch (Exception e) {
            return false;
        }
    }
}

