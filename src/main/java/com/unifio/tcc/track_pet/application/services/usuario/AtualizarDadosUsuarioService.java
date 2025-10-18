package com.unifio.tcc.track_pet.application.services.usuario;

import com.unifio.tcc.track_pet.domain.repositories.UsuarioDomainRepository;
import com.unifio.tcc.track_pet.domain.usecases.usuario.AtualizarDadosUsuarioUseCase;
import com.unifio.tcc.track_pet.domain.usuario.Usuario;
import com.unifio.tcc.track_pet.infra.security.SecurityUtils;
import org.springframework.stereotype.Service;

@Service
public class AtualizarDadosUsuarioService implements AtualizarDadosUsuarioUseCase {
    private final UsuarioDomainRepository usuarioDomainRepository;
    private final SecurityUtils securityUtils;

    public AtualizarDadosUsuarioService(UsuarioDomainRepository usuarioDomainRepository,
                                        SecurityUtils securityUtils) {
        this.usuarioDomainRepository = usuarioDomainRepository;
        this.securityUtils = securityUtils;
    }

    @Override
    public Usuario atualizarDadosUsuario(Usuario novosDados) {
        Usuario existente = securityUtils.usuarioAutenticado();
        existente.atualizarUsuario(novosDados);
        return usuarioDomainRepository.salvar(existente);
    }
}
