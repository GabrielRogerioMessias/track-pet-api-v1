package com.unifio.tcc.track_pet.domain.usecases.animal;

import com.unifio.tcc.track_pet.domain.animal.Animal;
import com.unifio.tcc.track_pet.domain.sk.AnimalId;

public interface BuscarAnimalPorIDNaoAutenticadoUseCase {
    Animal buscarAnimalPorIDSemTokenUseCase(AnimalId animalId);
}
