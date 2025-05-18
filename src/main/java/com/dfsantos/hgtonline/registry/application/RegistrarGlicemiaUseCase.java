package com.dfsantos.hgtonline.registry.application;

import com.dfsantos.hgtonline.registry.domain.MomentoDia;
import java.time.LocalDateTime;
import java.util.UUID;

public interface RegistrarGlicemiaUseCase {
  Output execute(Input input);

  record Input(int valor, LocalDateTime dataHora, MomentoDia momentoDia) {}

  record Output(UUID id, int valor, LocalDateTime dataHora, MomentoDia momentoDia) {}
}
