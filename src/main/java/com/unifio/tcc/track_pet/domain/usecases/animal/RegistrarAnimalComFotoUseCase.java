package com.unifio.tcc.track_pet.domain.usecases.animal;

import com.unifio.tcc.track_pet.domain.animal.Animal;
import org.springframework.web.multipart.MultipartFile;

public interface RegistrarAnimalComFotoUseCase {
    Animal registrarAnimalComFoto(Animal animal, MultipartFile foto);
}
