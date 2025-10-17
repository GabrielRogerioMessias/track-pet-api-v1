package com.unifio.tcc.track_pet.domain.usecases.leituraqr;

import com.unifio.tcc.track_pet.domain.qr.LeituraQr;
import com.unifio.tcc.track_pet.domain.sk.AnimalId;

import java.util.List;

public interface ListarLeiturasQrUseCase {
    List<LeituraQr> listar(AnimalId animalId);
}
