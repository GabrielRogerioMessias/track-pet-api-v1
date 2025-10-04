package com.unifio.tcc.track_pet.application.services.animal;

import com.unifio.tcc.track_pet.domain.animal.Animal;
import com.unifio.tcc.track_pet.domain.repositories.AnimalDomainRepository;
import com.unifio.tcc.track_pet.domain.repositories.UsuarioDomainRepository;
import com.unifio.tcc.track_pet.domain.usecases.animal.ListarAnimalUseCase;
import com.unifio.tcc.track_pet.domain.usuario.Usuario;
import com.unifio.tcc.track_pet.infra.security.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarAnimalService implements ListarAnimalUseCase {
    private final AnimalDomainRepository animalRepository;
    private final SecurityUtils securityUtils;

    public ListarAnimalService(
            AnimalDomainRepository animalRepository,
            SecurityUtils securityUtils) {
        this.animalRepository = animalRepository;
        this.securityUtils = securityUtils;
    }

    @Override
    public List<Animal> listarAnimal() {
        Usuario autenticado = securityUtils.usuarioAutenticado();
        return animalRepository.findAll(autenticado);
    }
}
