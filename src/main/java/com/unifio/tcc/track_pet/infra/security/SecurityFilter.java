package com.unifio.tcc.track_pet.infra.security;

import com.unifio.tcc.track_pet.adapters.out.persistence.entities.UsuarioEntityJpa;
import com.unifio.tcc.track_pet.adapters.out.persistence.repositories.UsuarioJpaRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final UsuarioJpaRepository usuarioJpaRepository;

    public SecurityFilter(TokenService tokenService, UsuarioJpaRepository usuarioJpaRepository) {
        this.tokenService = tokenService;
        this.usuarioJpaRepository = usuarioJpaRepository;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.tokenService.recoveryToken(request);
        // retorna o e-mail do usuário
        var login = this.tokenService.validadeToken(token);
        if (login != null) {
            UsuarioEntityJpa jpaUsuarioEntity = usuarioJpaRepository.findByEmail(login).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
            var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
            var authentication = new UsernamePasswordAuthenticationToken(login, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
