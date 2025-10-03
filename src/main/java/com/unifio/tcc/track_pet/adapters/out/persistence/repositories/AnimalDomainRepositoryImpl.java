package com.unifio.tcc.track_pet.adapters.out.persistence.repositories;

import com.unifio.tcc.track_pet.adapters.out.persistence.entities.AnimalEntityJpa;
import com.unifio.tcc.track_pet.adapters.out.persistence.mappers.AnimalMapper;
import com.unifio.tcc.track_pet.domain.animal.Animal;
import com.unifio.tcc.track_pet.domain.repositories.AnimalDomainRepository;
import com.unifio.tcc.track_pet.domain.sk.AnimalId;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AnimalDomainRepositoryImpl implements AnimalDomainRepository {
    private final AnimalJpaRepository animalJpaRepository;
    private final AnimalMapper animalMapper;

    public AnimalDomainRepositoryImpl(AnimalJpaRepository animalJpaRepository, AnimalMapper animalMapper) {
        this.animalJpaRepository = animalJpaRepository;
        this.animalMapper = animalMapper;
    }

    @Override
    public Animal salvar(Animal animal) {
        AnimalEntityJpa entityJpa = animalMapper.toJpa(animal);
        return animalMapper.toDomain(animalJpaRepository.save(entityJpa));
    }

    @Override
    public Optional<Animal> findById(AnimalId id) {
        Optional<AnimalEntityJpa> result = animalJpaRepository.findById(id.getValue());
        return result.map(animalMapper::toDomain);
    }

    @Override
    public List<Animal> findAll() {
        return List.of();
    }

    @Override
    public void delete(Animal animal) {

    }
}
