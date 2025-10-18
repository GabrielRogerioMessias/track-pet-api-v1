package com.unifio.tcc.track_pet.application.services.usuario;

import com.unifio.tcc.track_pet.domain.usecases.usuario.BuscarDadosUsuarioAutenticadoUseCase;
import com.unifio.tcc.track_pet.domain.usuario.Usuario;
import com.unifio.tcc.track_pet.infra.security.SecurityUtils;
import org.springframework.stereotype.Service;

@Service
public class BuscarDadosUsuarioAutenticadoService implements BuscarDadosUsuarioAutenticadoUseCase {
    private final SecurityUtils securityUtils;

    public BuscarDadosUsuarioAutenticadoService(SecurityUtils securityUtils) {
        this.securityUtils = securityUtils;
    }

    @Override
    public Usuario buscarDadosUsuarioLogado() {
        return securityUtils.usuarioAutenticado();
    }
}
