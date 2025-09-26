package com.unifio.tcc.track_pet.adapters.out.persistence.repositories;

import com.unifio.tcc.track_pet.adapters.out.persistence.entities.UsuarioEntityJpa;
import com.unifio.tcc.track_pet.adapters.out.persistence.mappers.UsuarioMapper;
import com.unifio.tcc.track_pet.domain.repositories.UsuarioRepository;
import com.unifio.tcc.track_pet.domain.usuario.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {
    private final UsuarioJpaRepository usuarioJpaRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioRepositoryImpl(UsuarioJpaRepository usuarioJpaRepository, UsuarioMapper usuarioMapper) {
        this.usuarioJpaRepository = usuarioJpaRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public Usuario save(Usuario u) {
        UsuarioEntityJpa usuarioEntityJpa = usuarioMapper.toJpa(u);
        return usuarioMapper.toDomain(usuarioJpaRepository.save(usuarioEntityJpa));
    }
}
