package com.dfsantos.hgtonline.registry.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GlicemiaRepository {
  Glicemia salvar(Glicemia glicemia);

  Optional<Glicemia> buscarPorId(UUID id);

  List<Glicemia> buscarTodos();
}
