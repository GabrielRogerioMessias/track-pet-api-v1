package com.unifio.tcc.track_pet.domain.usecases.usuario;

import com.unifio.tcc.track_pet.domain.usuario.Usuario;

public interface LoginUsuarioUseCase {
    Usuario execute(LoginUsuarioCommand command);

    record LoginUsuarioCommand(String email, String senha) {
    }
}
