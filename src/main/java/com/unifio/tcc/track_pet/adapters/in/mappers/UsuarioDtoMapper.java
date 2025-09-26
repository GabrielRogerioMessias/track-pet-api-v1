package com.unifio.tcc.track_pet.adapters.in.mappers;

import com.unifio.tcc.track_pet.adapters.in.dtos.UsuarioRegistrarDto;
import com.unifio.tcc.track_pet.domain.usuario.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioDtoMapper {
    Usuario registrarDtoToEntity(UsuarioRegistrarDto usuarioRegistrarDto);

}
