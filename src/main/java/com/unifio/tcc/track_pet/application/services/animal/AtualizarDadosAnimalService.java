package com.unifio.tcc.track_pet.application.services.animal;

import com.unifio.tcc.track_pet.application.services.exceptions.EntidadeNaoEncontradaException;
import com.unifio.tcc.track_pet.domain.animal.Animal;
import com.unifio.tcc.track_pet.domain.repositories.AnimalDomainRepository;
import com.unifio.tcc.track_pet.domain.sk.AnimalId;
import com.unifio.tcc.track_pet.domain.usecases.animal.AtualizarDadosAnimalUseCase;
import com.unifio.tcc.track_pet.domain.usuario.Usuario;
import com.unifio.tcc.track_pet.infra.security.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AtualizarDadosAnimalService implements AtualizarDadosAnimalUseCase {
    private AnimalDomainRepository animalDomainRepository;
    private SecurityUtils securityUtils;

    public AtualizarDadosAnimalService(AnimalDomainRepository animalDomainRepository, SecurityUtils securityUtils) {
        this.animalDomainRepository = animalDomainRepository;
        this.securityUtils = securityUtils;
    }

    @Override
    public Animal atualizarDadosAnimal(Animal novosDados, UUID animalId) {
        Usuario usuario = securityUtils.usuarioAutenticado();
        Animal existente = animalDomainRepository.findById(AnimalId.of(animalId), usuario)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Animal não encontrado com o ID:" + animalId));
        existente.atualizarAnimal(novosDados);
        return animalDomainRepository.salvar(existente);
    }
}
