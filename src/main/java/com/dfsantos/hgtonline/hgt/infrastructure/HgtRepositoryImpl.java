package com.dfsantos.hgtonline.hgt.infrastructure;

import com.dfsantos.hgtonline.hgt.domain.TesteGlicemia;
import com.dfsantos.hgtonline.hgt.domain.TesteGlicemiaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
class TesteGlicemiaRepositoryImpl implements TesteGlicemiaRepository {

    private final GlicemiaJpaRepository glicemiaJpaRepository;

    public TesteGlicemiaRepositoryImpl(final GlicemiaJpaRepository glicemiaJpaRepository) {
        this.glicemiaJpaRepository = glicemiaJpaRepository;
    }

    @Override
    public TesteGlicemia salvar(final TesteGlicemia teste) {
        var entity = TesteGlicemiaEntity.fromDomain(teste);
        return glicemiaJpaRepository.save(entity).toDomain();
    }

    @Override
    public Optional<TesteGlicemia> buscarPorId(final UUID id) {
        return glicemiaJpaRepository.findById(id).map(TesteGlicemiaEntity::toDomain);
    }

    @Override
    public List<TesteGlicemia> buscarTodos() {
        return glicemiaJpaRepository.findAll().stream().map(TesteGlicemiaEntity::toDomain).toList();
    }

    @Override
    public void excluir(final UUID idTeste) {
    }
}
