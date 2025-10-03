package com.unifio.tcc.track_pet.adapters.out.persistence.mappers;

import com.unifio.tcc.track_pet.adapters.out.persistence.entities.AnimalEntityJpa;
import com.unifio.tcc.track_pet.adapters.out.persistence.entities.UsuarioEntityJpa;
import com.unifio.tcc.track_pet.domain.animal.Animal;
import com.unifio.tcc.track_pet.domain.sk.AnimalId;
import com.unifio.tcc.track_pet.domain.sk.UsuarioId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface AnimalMapper {
    @Mapping(source = "usuario.id", target = "usuarioId")
    Animal toDomain(AnimalEntityJpa entity);

    @Mapping(source = "usuarioId", target = "usuario")
    AnimalEntityJpa toJpa(Animal domain);

    // Converte UUID para AnimalId
    default AnimalId map(UUID id) {
        return id == null ? null : AnimalId.of(id);
    }

    // Converte AnimalId para UUID
    default UUID map(AnimalId animalId) {
        return animalId == null ? null : animalId.getValue();
    }

    // Converte UUID do Usuario para UsuarioId
    default UsuarioId mapUsuarioId(UUID id) {
        return id == null ? null : UsuarioId.of(id);
    }

    // Converte UsuarioId para stub de UsuarioEntityJpa
    default UsuarioEntityJpa map(UsuarioId usuarioId) {
        if (usuarioId == null) return null;
        UsuarioEntityJpa usuario = new UsuarioEntityJpa();
        usuario.setId(usuarioId.getValue());
        return usuario;
    }
}
