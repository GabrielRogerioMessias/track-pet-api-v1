package com.unifio.tcc.track_pet.adapters.out.persistence.repositories;

import com.unifio.tcc.track_pet.adapters.out.persistence.entities.AnimalEntityJpa;
import com.unifio.tcc.track_pet.adapters.out.persistence.entities.UsuarioEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface AnimalJpaRepository extends JpaRepository<AnimalEntityJpa, UUID> {
    @Query("SELECT a FROM AnimalEntityJpa AS a WHERE a.usuario = :usuario")
    List<AnimalEntityJpa> findAllByUsuario(@Param(value = "usuario") UsuarioEntityJpa usuario);

    @Query("SELECT a FROM AnimalEntityJpa AS a WHERE a.usuario = :usuario and a.id = :id")
    Optional<AnimalEntityJpa> findByIdAndUsuario(@Param(value = "id") UUID id,
                                                 @Param(value = "usuario") UsuarioEntityJpa usuario);
}
