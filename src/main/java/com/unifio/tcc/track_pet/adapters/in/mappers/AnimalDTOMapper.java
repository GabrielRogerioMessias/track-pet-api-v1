package com.unifio.tcc.track_pet.adapters.in.mappers;

import com.unifio.tcc.track_pet.adapters.in.dtos.AnimalAtualizarDTO;
import com.unifio.tcc.track_pet.adapters.in.dtos.AnimalEncontroDTO;
import com.unifio.tcc.track_pet.adapters.in.dtos.AnimalRegistrarDTO;
import com.unifio.tcc.track_pet.adapters.in.dtos.AnimalRespostaDTO;
import com.unifio.tcc.track_pet.domain.animal.Animal;
import com.unifio.tcc.track_pet.domain.sk.AnimalId;
import org.mapstruct.Mapper;


import java.util.UUID;

@Mapper(componentModel = "spring")
public interface AnimalDTOMapper {
    Animal registrarDtoToEntity(AnimalRegistrarDTO animalRegistrarDto);

    AnimalRespostaDTO domainToDto(Animal animal);

    Animal atualizarDtoToEntity(AnimalAtualizarDTO animalAtualizarDto);

    AnimalEncontroDTO entityToDtoEncontro(Animal animal);

    default AnimalId mapAnimalId(UUID id) {
        return id == null ? null : AnimalId.of(id);
    }

    default UUID mapAnimalId(AnimalId animalId) {
        return animalId == null ? null : animalId.getValue();
    }
}
