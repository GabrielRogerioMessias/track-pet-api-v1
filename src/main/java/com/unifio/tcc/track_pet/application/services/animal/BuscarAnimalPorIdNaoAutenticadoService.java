package com.unifio.tcc.track_pet.application.services.animal;

import com.unifio.tcc.track_pet.application.services.exceptions.EntidadeNaoEncontradaException;
import com.unifio.tcc.track_pet.domain.animal.Animal;
import com.unifio.tcc.track_pet.domain.repositories.AnimalDomainRepository;
import com.unifio.tcc.track_pet.domain.sk.AnimalId;
import com.unifio.tcc.track_pet.domain.usecases.animal.BuscarAnimalPorIDNaoAutenticadoUseCase;
import org.springframework.stereotype.Service;

@Service
public class BuscarAnimalPorIdNaoAutenticadoService implements BuscarAnimalPorIDNaoAutenticadoUseCase {
    private final AnimalDomainRepository animalDomainRepository;

    public BuscarAnimalPorIdNaoAutenticadoService(AnimalDomainRepository animalDomainRepository) {
        this.animalDomainRepository = animalDomainRepository;
    }

    @Override
    public Animal buscarAnimalPorIDSemTokenUseCase(AnimalId animalId) {
        return animalDomainRepository.findByIdNaoEntenticado(animalId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Animal não encontrado com o ID: " + animalId));
    }
}
