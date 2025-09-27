package com.unifio.tcc.track_pet.domain.repositories;


import com.unifio.tcc.track_pet.domain.usuario.Usuario;

import java.util.Optional;

public interface UsuarioDomainRepository {
    Usuario save(Usuario u);

    Optional<Usuario> buscarUsuarioEmail(String email);
}
