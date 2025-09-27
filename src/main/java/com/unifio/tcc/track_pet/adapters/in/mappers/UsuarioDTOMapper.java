package com.unifio.tcc.track_pet.adapters.in.mappers;

import com.unifio.tcc.track_pet.adapters.in.dtos.UsuarioRegistrarDTO;
import com.unifio.tcc.track_pet.domain.usuario.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioDTOMapper {
    Usuario registrarDtoToEntity(UsuarioRegistrarDTO usuarioRegistrarDto);
}
