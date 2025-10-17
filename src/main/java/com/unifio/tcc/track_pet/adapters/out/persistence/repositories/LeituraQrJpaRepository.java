package com.unifio.tcc.track_pet.adapters.out.persistence.repositories;

import com.unifio.tcc.track_pet.adapters.out.persistence.entities.LeituraEntityJpa;
import com.unifio.tcc.track_pet.adapters.out.persistence.entities.UsuarioEntityJpa;
import com.unifio.tcc.track_pet.domain.sk.AnimalId;
import com.unifio.tcc.track_pet.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface LeituraQrJpaRepository extends JpaRepository<LeituraEntityJpa, Long> {
    @Query(value = "SELECT l FROM LeituraEntityJpa AS l " +
            "WHERE l.animal.id = :animalId " +
            "AND l.animal.usuario= :usuarioAutenticado")
    List<LeituraEntityJpa> findByAnimalIdAndUser(@Param(value = "animalId") UUID animalId,
                                                 @Param(value = "usuarioAutenticado")
                                                 UsuarioEntityJpa usuarioAutenticado);

    @Query(value = "SELECT l FROM LeituraEntityJpa AS l " +
            "WHERE l.id = :leituraId " +
            "AND l.animal.usuario= :usuarioAutenticado")
    Optional<LeituraEntityJpa> findByIdAndAutenticatedUser(@Param(value = "leituraId")
                                                           UUID leituraId,
                                                           @Param(value = "usuarioAutenticado")
                                                           UsuarioEntityJpa usuarioAutenticado);
}
