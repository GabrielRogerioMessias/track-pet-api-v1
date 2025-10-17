package com.unifio.tcc.track_pet.domain.usecases.leituraqr;

import com.unifio.tcc.track_pet.domain.qr.LeituraQr;

import java.util.UUID;

public interface RegistrarLeituraQrUseCase {
    LeituraQr registarLeituraQr(LeituraQr leituraQr, UUID idAnimal);
}
