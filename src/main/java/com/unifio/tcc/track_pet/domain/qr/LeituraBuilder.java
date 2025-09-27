package com.unifio.tcc.track_pet.domain.qr;

import com.unifio.tcc.track_pet.domain.sk.AnimalId;
import com.unifio.tcc.track_pet.domain.sk.LeituraId;

import java.time.LocalDateTime;

public class LeituraBuilder {
    LeituraId id;
    AnimalId animalId;
    LocalDateTime dataHora;
    String latitude;
    String longitude;
    String message;

    public LeituraBuilder id(LeituraId id) {
        this.id = id;
        return this;
    }

    public LeituraBuilder animalId(AnimalId animalId) {
        this.animalId = animalId;
        return this;
    }

    public LeituraBuilder dataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
        return this;
    }

    public LeituraBuilder latitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public LeituraBuilder longitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public LeituraBuilder message(String message) {
        this.message = message;
        return this;
    }

    public LeituraQr build() {
        return new LeituraQr(this);
    }
}
