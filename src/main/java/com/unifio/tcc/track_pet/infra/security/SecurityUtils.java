package com.unifio.tcc.track_pet.infra.security;

import com.unifio.tcc.track_pet.domain.repositories.UsuarioDomainRepository;
import com.unifio.tcc.track_pet.domain.usuario.Usuario;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@Component
public class SecurityUtils {
    private final UsuarioDomainRepository repository;

    public SecurityUtils(UsuarioDomainRepository usuarioRepository) {
        this.repository = usuarioRepository;
    }

    public Usuario usuarioAutenticado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return repository.buscarUsuarioEmail(authentication.getName()).get();
    }
}
