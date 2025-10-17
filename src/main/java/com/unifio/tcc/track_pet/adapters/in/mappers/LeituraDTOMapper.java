package com.unifio.tcc.track_pet.adapters.in.mappers;

import com.unifio.tcc.track_pet.adapters.in.dtos.LeituraQrRegistrarDTO;
import com.unifio.tcc.track_pet.adapters.in.dtos.LeituraQrRespostaDTO;
import com.unifio.tcc.track_pet.domain.qr.LeituraQr;
import com.unifio.tcc.track_pet.domain.sk.AnimalId;
import com.unifio.tcc.track_pet.domain.sk.LeituraId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;


@Mapper(componentModel = "spring")
public interface LeituraDTOMapper {

    LeituraQr registrarDtoToEntity(LeituraQrRegistrarDTO dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "animalId", target = "idAnimal")
    LeituraQrRespostaDTO entityToDtoResposta(LeituraQr entity);

    default UUID mapUUID(LeituraId id) {
        return id == null ? null : id.getValue();
    }

    default LeituraId mapLeituraId(UUID id) {
        return id == null ? null : LeituraId.of(id);
    }

    default UUID animalIdToUUID(AnimalId animalId) {
        return animalId == null ? null : animalId.getValue();
    }
}
