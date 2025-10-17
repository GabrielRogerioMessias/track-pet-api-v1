package com.unifio.tcc.track_pet.application.services.leituraqr;

import com.unifio.tcc.track_pet.application.services.exceptions.EntidadeNaoEncontradaException;
import com.unifio.tcc.track_pet.domain.qr.LeituraQr;
import com.unifio.tcc.track_pet.domain.repositories.LeituraDomainRepository;
import com.unifio.tcc.track_pet.domain.sk.LeituraId;
import com.unifio.tcc.track_pet.domain.usecases.leituraqr.BuscarLeituraQrPorIdUseCase;
import com.unifio.tcc.track_pet.domain.usuario.Usuario;
import com.unifio.tcc.track_pet.infra.security.SecurityUtils;
import org.springframework.stereotype.Service;

@Service
public class BuscarLeituraQrPorIdService implements BuscarLeituraQrPorIdUseCase {
    private final LeituraDomainRepository leituraDomainRepository;
    private final SecurityUtils securityUtils;

    public BuscarLeituraQrPorIdService(LeituraDomainRepository leituraDomainRepository, SecurityUtils securityUtils) {
        this.leituraDomainRepository = leituraDomainRepository;
        this.securityUtils = securityUtils;
    }

    @Override
    public LeituraQr buscarLeituraQrPorId(LeituraId leituraId) {
        Usuario autenticado = securityUtils.usuarioAutenticado();
        return leituraDomainRepository.findById(leituraId.getValue(), autenticado)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Leitura não encontrada com o id: " + leituraId.getValue()));
    }
}
