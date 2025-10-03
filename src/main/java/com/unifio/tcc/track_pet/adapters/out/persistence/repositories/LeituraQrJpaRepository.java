package com.unifio.tcc.track_pet.adapters.out.persistence.repositories;

import com.unifio.tcc.track_pet.adapters.out.persistence.entities.LeituraEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LeituraQrJpaRepository extends JpaRepository<LeituraEntityJpa, Long> {
}
