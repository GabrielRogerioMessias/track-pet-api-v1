package com.unifio.tcc.track_pet.application.services.animal;

import com.unifio.tcc.track_pet.domain.animal.Animal;
import com.unifio.tcc.track_pet.domain.repositories.AnimalDomainRepository;
import com.unifio.tcc.track_pet.domain.repositories.UsuarioDomainRepository;
import com.unifio.tcc.track_pet.domain.usecases.animal.RegistrarAnimalUseCase;
import com.unifio.tcc.track_pet.infra.security.SecurityUtils;
import org.springframework.stereotype.Service;

@Service
public class RegistrarAnimalService implements RegistrarAnimalUseCase {
    private final UsuarioDomainRepository usuarioRepository;
    private final AnimalDomainRepository animalRepository;
    private final SecurityUtils securityUtils;

    public RegistrarAnimalService(UsuarioDomainRepository usuarioRepository,
                                  AnimalDomainRepository animalRepository,
                                  SecurityUtils securityUtils) {
        this.usuarioRepository = usuarioRepository;
        this.animalRepository = animalRepository;
        this.securityUtils = securityUtils;
    }

    @Override
    public Animal registrarAnimal() {
        return null;
    }
}
