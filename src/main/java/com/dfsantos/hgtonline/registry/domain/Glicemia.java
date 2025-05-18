package com.dfsantos.hgtonline.registry.domain;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.UUID;

public class Glicemia {

  private final UUID id;
  private final int valor;
  private final LocalDateTime dataHora;
  private final MomentoDia momentoDia;

  protected Glicemia(
      final UUID id, final int valor, final LocalDateTime dataHora, final MomentoDia momentoDia) {
    if (valor <= 0) {
      throw new IllegalArgumentException("Valor de glicemia deve ser maior que zero");
    }

    requireNonNull(dataHora);
    if (dataHora.isAfter(LocalDateTime.now())) {
      throw new IllegalArgumentException("Data/hora não pode ser futura");
    }

    this.id = id;
    this.valor = valor;
    this.dataHora = dataHora;
    this.momentoDia = requireNonNull(momentoDia);
  }

  public static Glicemia of(
      final int valor, final LocalDateTime dataHora, final MomentoDia momentoDia) {
    return new Glicemia(null, valor, dataHora, momentoDia);
  }

  public static Glicemia of(
      final UUID id, final int valor, final LocalDateTime dataHora, final MomentoDia momentoDia) {
    return new Glicemia(id, valor, dataHora, momentoDia);
  }

  public UUID getId() {
    return id;
  }

  public int getValor() {
    return valor;
  }

  public LocalDateTime getDataHora() {
    return dataHora;
  }

  public MomentoDia getMomentoDia() {
    return momentoDia;
  }
}
