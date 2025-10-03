package com.unifio.tcc.track_pet.domain.repositories;

import com.unifio.tcc.track_pet.domain.animal.Animal;
import com.unifio.tcc.track_pet.domain.sk.AnimalId;

import java.util.List;
import java.util.Optional;

public interface AnimalDomainRepository {
    Animal salvar(Animal animal);

    Optional<Animal> findById(AnimalId id);

    List<Animal> findAll();

    void delete(Animal animal);
}
