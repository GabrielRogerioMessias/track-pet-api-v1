package com.unifio.tcc.track_pet.application.services.leituraqr;

import com.unifio.tcc.track_pet.application.services.exceptions.EntidadeNaoEncontradaException;
import com.unifio.tcc.track_pet.domain.qr.LeituraQr;
import com.unifio.tcc.track_pet.domain.repositories.AnimalDomainRepository;
import com.unifio.tcc.track_pet.domain.repositories.LeituraDomainRepository;
import com.unifio.tcc.track_pet.domain.sk.AnimalId;
import com.unifio.tcc.track_pet.domain.usecases.leituraqr.ListarLeiturasQrUseCase;
import com.unifio.tcc.track_pet.domain.usuario.Usuario;
import com.unifio.tcc.track_pet.infra.security.SecurityUtils;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarLeituraQrService implements ListarLeiturasQrUseCase {
    private final LeituraDomainRepository leituraDomainRepository;
    private final AnimalDomainRepository animalDomainRepository;
    private final SecurityUtils securityUtils;

    public ListarLeituraQrService(LeituraDomainRepository leituraDomainRepository,
                                  AnimalDomainRepository animalDomainRepository,
                                  SecurityUtils securityUtils) {
        this.leituraDomainRepository = leituraDomainRepository;
        this.animalDomainRepository = animalDomainRepository;
        this.securityUtils = securityUtils;
    }

    @Override
    public List<LeituraQr> listar(AnimalId animalId) {
        Usuario autenticado = securityUtils.usuarioAutenticado();
        animalDomainRepository.findById(animalId, autenticado)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Animal não encontrado com o ID: " + animalId.getValue()));
        return leituraDomainRepository.findAll(animalId, autenticado);
    }
}
