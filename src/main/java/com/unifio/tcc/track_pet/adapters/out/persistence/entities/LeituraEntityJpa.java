package com.unifio.tcc.track_pet.adapters.out.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "leitura_qr")
public class LeituraEntityJpa {
    @Id
    private UUID id;
    @Column(name = "data_hora")
    private LocalDateTime dataHora;
    private String latitude;
    private String longitude;
    private String mensagem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id")
    private AnimalEntityJpa animal;

}
