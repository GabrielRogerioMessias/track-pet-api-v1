package com.unifio.tcc.track_pet.adapters.out.persistence.repositories;

import com.unifio.tcc.track_pet.adapters.out.persistence.entities.UsuarioEntityJpa;
import com.unifio.tcc.track_pet.adapters.out.persistence.mappers.UsuarioMapper;
import com.unifio.tcc.track_pet.domain.repositories.UsuarioDomainRepository;
import com.unifio.tcc.track_pet.domain.usuario.Usuario;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UsuarioDomainRepositoryImpl implements UsuarioDomainRepository {
    private final UsuarioJpaRepository usuarioJpaRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioDomainRepositoryImpl(UsuarioJpaRepository usuarioJpaRepository, UsuarioMapper usuarioMapper) {
        this.usuarioJpaRepository = usuarioJpaRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public Usuario save(Usuario u) {
        UsuarioEntityJpa usuarioEntityJpa = usuarioMapper.toJpa(u);
        return usuarioMapper.toDomain(usuarioJpaRepository.save(usuarioEntityJpa));
    }

    @Override
    public Optional<Usuario> buscarUsuarioEmail(String email) {
        Optional<UsuarioEntityJpa> resultado = usuarioJpaRepository.findByEmail(email);
        if (resultado.isEmpty()) return Optional.empty();
        else {
            UsuarioEntityJpa usuarioEntityJpa = resultado.get();
            return Optional.of(usuarioMapper.toDomain(usuarioEntityJpa));
        }
    }
}
