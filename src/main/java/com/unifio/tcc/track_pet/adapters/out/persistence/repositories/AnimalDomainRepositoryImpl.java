package com.unifio.tcc.track_pet.adapters.out.persistence.repositories;

import com.unifio.tcc.track_pet.adapters.out.persistence.entities.AnimalEntityJpa;
import com.unifio.tcc.track_pet.adapters.out.persistence.entities.UsuarioEntityJpa;
import com.unifio.tcc.track_pet.adapters.out.persistence.mappers.AnimalMapper;
import com.unifio.tcc.track_pet.adapters.out.persistence.mappers.UsuarioMapper;
import com.unifio.tcc.track_pet.domain.animal.Animal;
import com.unifio.tcc.track_pet.domain.repositories.AnimalDomainRepository;
import com.unifio.tcc.track_pet.domain.sk.AnimalId;
import com.unifio.tcc.track_pet.domain.usuario.Usuario;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AnimalDomainRepositoryImpl implements AnimalDomainRepository {
    private final AnimalJpaRepository animalJpaRepository;
    private final AnimalMapper animalMapper;
    private final UsuarioMapper usuarioMapper;

    public AnimalDomainRepositoryImpl(AnimalJpaRepository animalJpaRepository,
                                      AnimalMapper animalMapper,
                                      UsuarioMapper usuarioMapper) {
        this.animalJpaRepository = animalJpaRepository;
        this.animalMapper = animalMapper;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public Animal salvar(Animal animal) {
        AnimalEntityJpa entityJpa = animalMapper.toJpa(animal);
        return animalMapper.toDomain(animalJpaRepository.save(entityJpa));
    }

    @Override
    public Optional<Animal> findById(AnimalId id, Usuario usuarioAutenticado) {
        Optional<AnimalEntityJpa> result = animalJpaRepository.findByIdAndUsuario(id.getValue(),
                usuarioMapper.toJpa(usuarioAutenticado));
        return result.map(animalMapper::toDomain);
    }

    @Override
    public List<Animal> findAll(Usuario usuarioAutenticado) {
        UsuarioEntityJpa usuarioEntityJpa = usuarioMapper.toJpa(usuarioAutenticado);
        return animalJpaRepository.findAllByUsuario(usuarioEntityJpa)
                .stream()
                .map(animalMapper::toDomain)
                .toList();
    }

    @Override
    public void delete(Animal animal) {
        animalJpaRepository.delete(animalMapper.toJpa(animal));
    }
}
