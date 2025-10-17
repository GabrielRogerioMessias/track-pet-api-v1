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

    protected LeituraQr(LeituraBuilder builder) {
        this.id = Objects.requireNonNull(builder.id, "id não pode ser nulo.");
        this.animalId = builder.animalId;
        this.dataHora = builder.dataHora;
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
        this.mensagem = builder.mensagem;
    }

    public void vincularAnimal(AnimalId animalId) {
        this.animalId = animalId;
    }

    public void dataLeitura() {
        this.dataHora = LocalDateTime.now();
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
