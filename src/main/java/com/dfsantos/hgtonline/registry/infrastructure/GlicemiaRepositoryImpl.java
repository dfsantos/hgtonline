package com.dfsantos.hgtonline.registry.infrastructure;

import com.dfsantos.hgtonline.registry.domain.Glicemia;
import com.dfsantos.hgtonline.registry.domain.GlicemiaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
class GlicemiaRepositoryImpl implements GlicemiaRepository {

  private final GlicemiaJpaRepository glicemiaJpaRepository;

  public GlicemiaRepositoryImpl(final GlicemiaJpaRepository glicemiaJpaRepository) {
    this.glicemiaJpaRepository = glicemiaJpaRepository;
  }

  @Override
  public Glicemia salvar(final Glicemia glicemia) {
    var entity = GlicemiaEntity.fromDomain(glicemia);
    return glicemiaJpaRepository.save(entity).toDomain();
  }

  @Override
  public Optional<Glicemia> buscarPorId(final UUID id) {
    return glicemiaJpaRepository.findById(id).map(GlicemiaEntity::toDomain);
  }

  @Override
  public List<Glicemia> buscarTodos() {
    return glicemiaJpaRepository.findAll().stream().map(GlicemiaEntity::toDomain).toList();
  }
}
