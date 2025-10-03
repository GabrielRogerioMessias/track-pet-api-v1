package com.unifio.tcc.track_pet.adapters.out.persistence.repositories;

import com.unifio.tcc.track_pet.adapters.out.persistence.entities.AnimalEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface AnimalJpaRepository extends JpaRepository<AnimalEntityJpa, UUID> {
}
