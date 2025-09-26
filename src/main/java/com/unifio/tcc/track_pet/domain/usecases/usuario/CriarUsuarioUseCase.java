package com.unifio.tcc.track_pet.domain.usecases.usuario;


import com.unifio.tcc.track_pet.domain.usuario.Usuario;

public interface CriarUsuarioUseCase {
    Usuario criar(criarUsuarioCommand usuario);

    record criarUsuarioCommand(
            String nome,
            String sobrenome,
            String email,
            String senha,
            String cidade,
            String bairro,
            String numero,
            String telefone
    ) {
    }
}
