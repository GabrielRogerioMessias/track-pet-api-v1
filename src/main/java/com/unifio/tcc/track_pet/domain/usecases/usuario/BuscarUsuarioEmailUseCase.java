package com.unifio.tcc.track_pet.domain.usecases.usuario;

import com.unifio.tcc.track_pet.domain.usuario.Usuario;

import java.util.Optional;

public interface BuscarUsuarioEmailUseCase {
    Optional<Usuario> buscarUsuarioEmail(String email);
}
