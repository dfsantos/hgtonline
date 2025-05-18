package com.dfsantos.hgtonline.registry.domain;

import static com.dfsantos.hgtonline.registry.domain.Glicemia.*;

import com.dfsantos.hgtonline.registry.application.RegistrarGlicemiaUseCase;
import org.springframework.stereotype.Service;

@Service
class RegistrarGlicemiaUseCaseImpl implements RegistrarGlicemiaUseCase {

  private final GlicemiaRepository glicemiaRepository;

  public RegistrarGlicemiaUseCaseImpl(final GlicemiaRepository glicemiaRepository) {
    this.glicemiaRepository = glicemiaRepository;
  }

  @Override
  public Output execute(final Input input) {
    var glicemia = of(input.valor(), input.dataHora(), input.momentoDia());

    var glicemiaPersistida = glicemiaRepository.salvar(glicemia);

    return new Output(
        glicemiaPersistida.getId(),
        glicemiaPersistida.getValor(),
        glicemiaPersistida.getDataHora(),
        glicemiaPersistida.getMomentoDia());
  }
}
