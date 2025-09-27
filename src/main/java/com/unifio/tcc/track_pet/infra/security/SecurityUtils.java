package com.unifio.tcc.track_pet.infra.security;

import com.unifio.tcc.track_pet.domain.repositories.UsuarioRepository;
import com.unifio.tcc.track_pet.domain.usuario.Usuario;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@Component
public class SecurityUtils {
    private final UsuarioRepository repository;

    public SecurityUtils(UsuarioRepository usuarioRepository) {
        this.repository = usuarioRepository;
    }

    public Usuario usuarioAutenticado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return repository.buscarUsuarioEmail(authentication.getName()).get();
    }
}
