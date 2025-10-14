package com.unifio.tcc.track_pet.application.services.animal;

import com.unifio.tcc.track_pet.application.services.exceptions.EntidadeNaoEncontradaException;
import com.unifio.tcc.track_pet.domain.animal.Animal;
import com.unifio.tcc.track_pet.domain.repositories.AnimalDomainRepository;
import com.unifio.tcc.track_pet.domain.repositories.UsuarioDomainRepository;
import com.unifio.tcc.track_pet.domain.sk.AnimalId;
import com.unifio.tcc.track_pet.domain.usecases.animal.MarcarAnimalComoPerdidoUseCase;
import com.unifio.tcc.track_pet.domain.usuario.Usuario;
import com.unifio.tcc.track_pet.infra.security.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MarcarAnimalComoPerdidoService implements MarcarAnimalComoPerdidoUseCase {
    private final AnimalDomainRepository animalDomainRepository;
    private final UsuarioDomainRepository usuarioDomainRepository;
    private final SecurityUtils securityUtils;

    public MarcarAnimalComoPerdidoService(AnimalDomainRepository animalDomainRepository,
                                          UsuarioDomainRepository usuarioDomainRepository,
                                          SecurityUtils securityUtils) {
        this.animalDomainRepository = animalDomainRepository;
        this.usuarioDomainRepository = usuarioDomainRepository;
        this.securityUtils = securityUtils;
    }

    @Override
    public Animal marcarComoPerdido(UUID animalId) {
        Usuario autenticado = securityUtils.usuarioAutenticado();
        Animal animalPerdido = animalDomainRepository.findById(AnimalId.of(animalId), autenticado)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Animal não encontrado com o ID: " + animalId));
        animalPerdido.marcarComoPerdido();
        return animalDomainRepository.salvar(animalPerdido);
    }
}
