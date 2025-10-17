package com.unifio.tcc.track_pet.domain.usecases.leituraqr;

import com.unifio.tcc.track_pet.domain.qr.LeituraQr;
import com.unifio.tcc.track_pet.domain.sk.LeituraId;

public interface BuscarLeituraQrPorIdUseCase {
    LeituraQr buscarLeituraQrPorId(LeituraId leituraId);
}
