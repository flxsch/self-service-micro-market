package com.micromarket.core_service.core.security;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.jwk.source.RemoteJWKSet;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Configuration
@EnableMethodSecurity
public class WebConfig {

    private static final String JWK_URL = "http://localhost:8080/realms/self-service-micro-market/protocol/openid-connect/certs";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )
                .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    public static class JwtAuthenticationFilter extends OncePerRequestFilter {

        private final DefaultJWTProcessor<SecurityContext> jwtProcessor;

        public JwtAuthenticationFilter() throws Exception {
            URL jwkSetURL = new URL(JWK_URL);
            JWKSource<SecurityContext> keySource = new RemoteJWKSet<>(jwkSetURL);
            jwtProcessor = new DefaultJWTProcessor<>();
            JWSAlgorithm expectedAlg = JWSAlgorithm.RS256;
            JWSKeySelector<SecurityContext> keySelector = new JWSVerificationKeySelector<>(expectedAlg, keySource);
            jwtProcessor.setJWSKeySelector(keySelector);
        }

        @Override
        protected void doFilterInternal(HttpServletRequest request,
                                        HttpServletResponse response,
                                        FilterChain filterChain) throws ServletException, IOException, java.io.IOException {

            String authHeader = request.getHeader("Authorization");

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                try {
                    SecurityContext ctx = null;
                    JWTClaimsSet claims = jwtProcessor.process(token, ctx);

                    String username = claims.getSubject();

                    // Получение ролей из realm_access.roles
                    Map<String, Object> resourceAccess = (Map<String, Object>) claims.getClaim("resource_access");
                    Map<String, Object> clientAccess = (Map<String, Object>) resourceAccess.get("postman-client");

                    List<String> roles = clientAccess != null
                            ? (List<String>) clientAccess.get("roles")
                            : Collections.emptyList();

                    List<SimpleGrantedAuthority> authorities = roles.stream()
                            .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
                            .toList();

                    Authentication auth = new UsernamePasswordAuthenticationToken(username, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(auth);

                } catch (Exception e) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token");
                    return;
                }
            }

            filterChain.doFilter(request, response);
        }
    }
}
