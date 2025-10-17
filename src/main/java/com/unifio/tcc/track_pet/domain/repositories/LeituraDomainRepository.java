package com.unifio.tcc.track_pet.domain.repositories;

import com.unifio.tcc.track_pet.domain.qr.LeituraQr;
import com.unifio.tcc.track_pet.domain.sk.AnimalId;
import com.unifio.tcc.track_pet.domain.usuario.Usuario;

import java.util.List;
import java.util.Optional;

public interface LeituraDomainRepository {
    LeituraQr registrar(LeituraQr leituraQr);

    Optional<LeituraQr> findById(Long id);

    List<LeituraQr> findAll(AnimalId animalId, Usuario usuarioAutenticado);

    void delete(LeituraQr leituraQr);
}
