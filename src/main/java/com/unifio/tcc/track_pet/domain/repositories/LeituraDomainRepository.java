package com.unifio.tcc.track_pet.domain.repositories;

import com.unifio.tcc.track_pet.domain.qr.LeituraQr;

import java.util.List;
import java.util.Optional;

public interface LeituraDomainRepository {
    LeituraQr registrar(LeituraQr leituraQr);

    Optional<LeituraQr> findById(Long id);

    List<LeituraQr> findAll();

    void delete(LeituraQr leituraQr);
}
