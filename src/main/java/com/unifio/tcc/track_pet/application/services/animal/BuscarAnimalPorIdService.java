package com.unifio.tcc.track_pet.application.services.animal;

import com.unifio.tcc.track_pet.application.services.exceptions.EntidadeNaoEncontradaException;
import com.unifio.tcc.track_pet.domain.animal.Animal;
import com.unifio.tcc.track_pet.domain.repositories.AnimalDomainRepository;
import com.unifio.tcc.track_pet.domain.sk.AnimalId;
import com.unifio.tcc.track_pet.domain.usecases.animal.BuscarAnimalPorIdUseCase;
import com.unifio.tcc.track_pet.domain.usuario.Usuario;
import com.unifio.tcc.track_pet.infra.security.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BuscarAnimalPorIdService implements BuscarAnimalPorIdUseCase {
    private final AnimalDomainRepository animalRepository;
    private final SecurityUtils securityUtils;

    public BuscarAnimalPorIdService(AnimalDomainRepository animalRepository, SecurityUtils securityUtils) {
        this.animalRepository = animalRepository;
        this.securityUtils = securityUtils;
    }

    @Override
    public Animal buscarAnimalPorId(UUID id) {
        Usuario autenticado = securityUtils.usuarioAutenticado();
        return animalRepository.findById(AnimalId.of(id), autenticado).orElseThrow(() -> new EntidadeNaoEncontradaException("Animal não encontrado com o ID: " + id));
    }
}
