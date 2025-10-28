package com.unifio.tcc.track_pet.application.services.animal;

import com.unifio.tcc.track_pet.adapters.out.files.FileStorageService;
import com.unifio.tcc.track_pet.domain.animal.Animal;
import com.unifio.tcc.track_pet.domain.repositories.AnimalDomainRepository;
import com.unifio.tcc.track_pet.domain.usecases.animal.RegistrarAnimalComFotoUseCase;
import com.unifio.tcc.track_pet.domain.usuario.Usuario;
import com.unifio.tcc.track_pet.infra.security.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class RegistrarAnimalComFotoService implements RegistrarAnimalComFotoUseCase {
   private final  AnimalDomainRepository  animalDomainRepository;
   private SecurityUtils  securityUtils;
   private final FileStorageService fileStorageService;

    public RegistrarAnimalComFotoService(AnimalDomainRepository animalDomainRepository,
                                         FileStorageService fileStorageService,
                                         SecurityUtils securityUtils) {
        this.animalDomainRepository = animalDomainRepository;
        this.fileStorageService = fileStorageService;
        this.securityUtils = securityUtils;
    }

    @Override
    public Animal registrarAnimalComFoto(Animal animal, MultipartFile foto) {
        Usuario autenticado =   securityUtils.usuarioAutenticado();
        animal.ativarAnimal();
        animal.vincularUsuario(autenticado);

        Animal salvo = animalDomainRepository.salvar(animal);

        if (foto != null && !foto.isEmpty()) {
            String fotoUrl = fileStorageService.salvar(foto, salvo.getId().getValue().toString());
            salvo = Animal.builder()
                    .id(salvo.getId())
                    .usuarioId(salvo.getUsuarioId())
                    .nome(salvo.getNome())
                    .dataNascimento(salvo.getDataNascimento())
                    .peso(salvo.getPeso())
                    .situacao(salvo.getSituacao())
                    .fotoUrl(fotoUrl)
                    .raca(salvo.getRaca())
                    .sexo(salvo.getSexo())
                    .cor(salvo.getCor())
                    .ativo(salvo.getAtivo())
                    .build();
            salvo = animalDomainRepository.salvar(salvo);
        }
        return salvo;
    }
}
