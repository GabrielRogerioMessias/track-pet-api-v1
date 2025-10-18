package com.unifio.tcc.track_pet.adapters.in.mappers;

import com.unifio.tcc.track_pet.adapters.in.dtos.UsuarioAtualizarDTO;
import com.unifio.tcc.track_pet.adapters.in.dtos.UsuarioRegistrarDTO;
import com.unifio.tcc.track_pet.adapters.in.dtos.UsuarioRespostaDTO;
import com.unifio.tcc.track_pet.domain.sk.UsuarioId;
import com.unifio.tcc.track_pet.domain.usuario.Usuario;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UsuarioDTOMapper {
    Usuario registrarDtoToEntity(UsuarioRegistrarDTO usuarioRegistrarDto);

    UsuarioRespostaDTO entityToDto(Usuario usuario);

    Usuario atualizarDtoToEntity(UsuarioAtualizarDTO atualizarDto);

    default UUID mapUUID(UsuarioId id) {
        return id.getValue();
    }

}
