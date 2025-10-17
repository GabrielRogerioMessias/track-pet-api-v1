package com.unifio.tcc.track_pet.adapters.out.persistence.repositories;

import com.unifio.tcc.track_pet.adapters.out.persistence.entities.LeituraEntityJpa;
import com.unifio.tcc.track_pet.adapters.out.persistence.mappers.LeituraQrMapper;
import com.unifio.tcc.track_pet.adapters.out.persistence.mappers.UsuarioMapper;
import com.unifio.tcc.track_pet.domain.qr.LeituraQr;
import com.unifio.tcc.track_pet.domain.repositories.AnimalDomainRepository;
import com.unifio.tcc.track_pet.domain.repositories.LeituraDomainRepository;
import com.unifio.tcc.track_pet.domain.sk.AnimalId;
import com.unifio.tcc.track_pet.domain.sk.LeituraId;
import com.unifio.tcc.track_pet.domain.usuario.Usuario;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class LeituraQrDomainRepositoryImpl implements LeituraDomainRepository {
    private final LeituraQrJpaRepository leituraQrJpaRepository;
    private final LeituraQrMapper leituraQrMapper;
    private final UsuarioMapper usuarioMapper;
    private final AnimalJpaRepository animalJpaRepository;

    public LeituraQrDomainRepositoryImpl(LeituraQrJpaRepository leituraQrJpaRepository,
                                         LeituraQrMapper leituraQrMapper,
                                         UsuarioMapper usuarioMapper, AnimalJpaRepository animalJpaRepository) {
        this.leituraQrJpaRepository = leituraQrJpaRepository;
        this.leituraQrMapper = leituraQrMapper;
        this.usuarioMapper = usuarioMapper;
        this.animalJpaRepository = animalJpaRepository;
    }

    @Override
    public LeituraQr registrar(LeituraQr leituraQr) {
        LeituraEntityJpa leituraEntityJpa = leituraQrMapper.toJpa(leituraQr);
        return leituraQrMapper.toDomain(leituraQrJpaRepository.save(leituraEntityJpa));
    }

    @Override
    public Optional<LeituraQr> findById(UUID idLeituraQr, Usuario usuario) {
        Optional<LeituraEntityJpa> leituraQr = leituraQrJpaRepository.findByIdAndAutenticatedUser(idLeituraQr, usuarioMapper.toJpa(usuario));
        return leituraQr.map(leituraQrMapper::toDomain);
    }

    @Override
    public List<LeituraQr> findAll(AnimalId animalId, Usuario usuarioAutenticado) {
        return leituraQrJpaRepository.findByAnimalIdAndUser(animalId.getValue(), usuarioMapper.toJpa(usuarioAutenticado))
                .stream()
                .map(leituraQrMapper::toDomain)
                .toList();
    }

    @Override
    public void delete(LeituraQr leituraQr) {
        leituraQrJpaRepository.delete(leituraQrMapper.toJpa(leituraQr));
    }
}
