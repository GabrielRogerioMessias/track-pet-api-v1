package com.unifio.tcc.track_pet.application.services.leituraqr;

import com.unifio.tcc.track_pet.application.services.exceptions.EntidadeNaoEncontradaException;
import com.unifio.tcc.track_pet.domain.qr.LeituraQr;
import com.unifio.tcc.track_pet.domain.repositories.LeituraDomainRepository;
import com.unifio.tcc.track_pet.domain.sk.LeituraId;
import com.unifio.tcc.track_pet.domain.usecases.leituraqr.ExcluirLeituraQrUseCase;
import com.unifio.tcc.track_pet.domain.usuario.Usuario;
import com.unifio.tcc.track_pet.infra.security.SecurityUtils;
import org.springframework.stereotype.Service;

@Service
public class ExcluirLeituraQrService implements ExcluirLeituraQrUseCase {
    private final LeituraDomainRepository leituraDomainRepository;
    private final SecurityUtils securityUtils;

    public ExcluirLeituraQrService(LeituraDomainRepository leituraDomainRepository, SecurityUtils securityUtils) {
        this.leituraDomainRepository = leituraDomainRepository;
        this.securityUtils = securityUtils;
    }

    @Override
    public void excluirLeitura(LeituraId leituraId) {
        Usuario autenticado = securityUtils.usuarioAutenticado();
        LeituraQr leituraQr = leituraDomainRepository.findById(leituraId.getValue(), autenticado)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Leitura não encontrado com o id: " + leituraId.getValue()));
        leituraDomainRepository.delete(leituraQr);
    }
}
