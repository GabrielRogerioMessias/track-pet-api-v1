package com.unifio.tcc.track_pet.domain.usecases.animal;

import com.unifio.tcc.track_pet.domain.animal.Animal;

import java.util.UUID;

public interface MarcarAnimalComoPerdidoUseCase {
    Animal marcarComoPerdido(UUID animalId);
}
