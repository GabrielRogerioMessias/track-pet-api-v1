package com.unifio.tcc.track_pet.application.services.animal;

import com.unifio.tcc.track_pet.application.services.exceptions.EntidadeNaoEncontradaException;
import com.unifio.tcc.track_pet.domain.animal.Animal;
import com.unifio.tcc.track_pet.domain.repositories.AnimalDomainRepository;
import com.unifio.tcc.track_pet.domain.sk.AnimalId;
import com.unifio.tcc.track_pet.domain.usecases.animal.DesativarAnimalUseCase;
import com.unifio.tcc.track_pet.domain.usuario.Usuario;
import com.unifio.tcc.track_pet.infra.security.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DesativarAnimalService implements DesativarAnimalUseCase {
    private final AnimalDomainRepository animalRepository;
    private final SecurityUtils securityUtils;

    public DesativarAnimalService(AnimalDomainRepository animalRepository, SecurityUtils securityUtils) {
        this.animalRepository = animalRepository;
        this.securityUtils = securityUtils;
    }

    @Override
    public void desativarAnimal(UUID animalId) {
        Usuario autenticado = securityUtils.usuarioAutenticado();
        Animal animal = animalRepository.findById(AnimalId.of(animalId), autenticado)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Animal não encontrado com ID: " + animalId));
        animal.desativarAnimal();
        animalRepository.salvar(animal);
    }
}
