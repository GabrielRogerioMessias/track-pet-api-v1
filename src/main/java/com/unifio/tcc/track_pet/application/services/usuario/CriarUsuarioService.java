package com.unifio.tcc.track_pet.application.services.usuario;

import com.unifio.tcc.track_pet.domain.repositories.UsuarioRepository;
import com.unifio.tcc.track_pet.domain.usecases.usuario.CriarUsuarioUseCase;
import com.unifio.tcc.track_pet.domain.usuario.Usuario;
import org.springframework.stereotype.Service;

@Service
public class CriarUsuarioService implements CriarUsuarioUseCase {
    private final UsuarioRepository usuarioRepository;

    public CriarUsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario criar(criarUsuarioCommand usuario) {
        Usuario novoUsuario = Usuario.builder()
                .nome(usuario.nome())
                .sobrenome(usuario.sobrenome())
                .email(usuario.email())
                .senha(usuario.senha())
                .cidade(usuario.cidade())
                .bairro(usuario.bairro())
                .numero(usuario.numero())
                .telefone(usuario.telefone())
                .build();
        return usuarioRepository.save(novoUsuario);
    }
}
