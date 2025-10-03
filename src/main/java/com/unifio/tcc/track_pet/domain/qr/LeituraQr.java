package com.unifio.tcc.track_pet.domain.qr;

import com.unifio.tcc.track_pet.domain.sk.AnimalId;
import com.unifio.tcc.track_pet.domain.sk.LeituraId;

import java.time.LocalDateTime;
import java.util.Objects;

public class LeituraQr {
    private LeituraId id;
    private AnimalId animalId;
    private LocalDateTime dataHora;
    private String latitude;
    private String longitude;
    private String mensagem;

    protected LeituraQr(LeituraBuilder b) {
        this.id = Objects.requireNonNull(b.id, "id não pode ser nulo.");
        this.animalId = Objects.requireNonNull(b.animalId, "Id do animal não pode ser nulo.");
        this.dataHora = b.dataHora;
        this.latitude = b.latitude;
        this.longitude = b.longitude;
        this.mensagem = b.mensagem;
    }

    public static LeituraBuilder builder() {
        return new LeituraBuilder();
    }

    public LeituraId getId() {
        return id;
    }

    public AnimalId getAnimalId() {
        return animalId;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getMensagem() {
        return mensagem;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LeituraQr leituraQr = (LeituraQr) o;
        return Objects.equals(id, leituraQr.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
