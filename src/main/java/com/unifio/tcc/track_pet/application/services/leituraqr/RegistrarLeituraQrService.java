package com.unifio.tcc.track_pet.application.services.leituraqr;

import com.unifio.tcc.track_pet.application.services.exceptions.EntidadeNaoEncontradaException;
import com.unifio.tcc.track_pet.domain.animal.Animal;
import com.unifio.tcc.track_pet.domain.qr.LeituraQr;
import com.unifio.tcc.track_pet.domain.repositories.AnimalDomainRepository;
import com.unifio.tcc.track_pet.domain.repositories.LeituraDomainRepository;
import com.unifio.tcc.track_pet.domain.sk.AnimalId;
import com.unifio.tcc.track_pet.domain.usecases.leituraqr.RegistrarLeituraQrUseCase;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RegistrarLeituraQrService implements RegistrarLeituraQrUseCase {
    private final LeituraDomainRepository leituraDomainRepository;
    private final AnimalDomainRepository animalDomainRepository;

    public RegistrarLeituraQrService(LeituraDomainRepository leituraDomainRepository,
                                     AnimalDomainRepository animalDomainRepository) {
        this.leituraDomainRepository = leituraDomainRepository;
        this.animalDomainRepository = animalDomainRepository;
    }

    @Override
    public LeituraQr registarLeituraQr(LeituraQr leituraQr, UUID idAnimal) {
        Animal animalPerdido = animalDomainRepository.findByIdNaoEntenticado(AnimalId.of(idAnimal))
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Animal não encontrado com o id: " + idAnimal));
        leituraQr.vincularAnimal(animalPerdido.getId());
        leituraQr.dataLeitura();
        return leituraDomainRepository.registrar(leituraQr);
    }
}
