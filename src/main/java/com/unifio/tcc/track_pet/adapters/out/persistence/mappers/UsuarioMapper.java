package com.unifio.tcc.track_pet.adapters.out.persistence.mappers;

import com.unifio.tcc.track_pet.adapters.out.persistence.entities.UsuarioEntityJpa;
import com.unifio.tcc.track_pet.domain.sk.UsuarioId;
import com.unifio.tcc.track_pet.domain.usuario.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    @Mapping(source = "id", target = "id")
    Usuario toDomain(UsuarioEntityJpa usuarioEntityJpa);

    @Mapping(source = "id", target = "id")
    UsuarioEntityJpa toJpa(Usuario usuario);

    default UsuarioId map(UUID id) {
        return UsuarioId.of(id);
    }

    default UUID map(UsuarioId usuarioId) {
        return usuarioId.getValue();
    }
}
