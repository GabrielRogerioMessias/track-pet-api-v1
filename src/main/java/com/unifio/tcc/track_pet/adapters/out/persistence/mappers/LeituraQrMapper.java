package com.unifio.tcc.track_pet.adapters.out.persistence.mappers;

import com.unifio.tcc.track_pet.adapters.out.persistence.entities.AnimalEntityJpa;
import com.unifio.tcc.track_pet.adapters.out.persistence.entities.LeituraEntityJpa;
import com.unifio.tcc.track_pet.domain.qr.LeituraQr;
import com.unifio.tcc.track_pet.domain.sk.AnimalId;
import com.unifio.tcc.track_pet.domain.sk.LeituraId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface LeituraQrMapper {

    @Mapping(source = "animal.id", target = "animalId") // UUID → AnimalId
    @Mapping(source = "id", target = "id")
        // UUID → LeituraId
    LeituraQr toDomain(LeituraEntityJpa entity);

    @Mapping(source = "animalId", target = "animal")    // AnimalId → AnimalEntityJpa
    @Mapping(source = "id", target = "id")
        // LeituraId → UUID
    LeituraEntityJpa toJpa(LeituraQr domain);

    // Conversão UUID -> LeituraId
    default LeituraId map(UUID id) {
        return id == null ? null : LeituraId.of(id);
    }

    // Conversão LeituraId -> UUID
    default UUID map(LeituraId leituraId) {
        return leituraId == null ? null : leituraId.getValue();
    }

    // Conversão UUID -> AnimalId
    default AnimalId mapAnimal(UUID id) {
        return id == null ? null : AnimalId.of(id);
    }

    // Conversão AnimalId -> AnimalEntityJpa (stub)
    default AnimalEntityJpa map(AnimalId animalId) {
        if (animalId == null) return null;
        AnimalEntityJpa animal = new AnimalEntityJpa();
        animal.setId(animalId.getValue());
        return animal;
    }
}
