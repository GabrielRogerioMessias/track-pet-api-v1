package com.unifio.tcc.track_pet.application.services.usuario;

import com.unifio.tcc.track_pet.domain.usecases.usuario.BuscarUsuarioEmailUseCase;
import com.unifio.tcc.track_pet.domain.usuario.Usuario;

import java.util.Optional;

public class BuscarUsuarioPorEmailService implements BuscarUsuarioEmailUseCase {
    @Override
    public Optional<Usuario> buscarUsuarioEmail(String email) {
        return Optional.empty();
    }
}
