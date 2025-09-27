package com.unifio.tcc.track_pet.infra.security;

import com.unifio.tcc.track_pet.adapters.out.persistence.entities.UsuarioEntityJpa;
import com.unifio.tcc.track_pet.adapters.out.persistence.repositories.UsuarioJpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UsuarioJpaRepository usuarioJpaRepository;

    public CustomUserDetailsService(UsuarioJpaRepository usuarioJpaRepository) {
        this.usuarioJpaRepository = usuarioJpaRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioEntityJpa usuarioEntityJpa = usuarioJpaRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return new org.springframework.security.core.userdetails.User(usuarioEntityJpa.getEmail(), usuarioEntityJpa.getSenha(), new ArrayList<>());
    }
}
