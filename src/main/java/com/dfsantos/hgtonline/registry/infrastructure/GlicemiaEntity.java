package com.dfsantos.hgtonline.registry.infrastructure;

import com.dfsantos.hgtonline.registry.domain.Glicemia;
import com.dfsantos.hgtonline.registry.domain.MomentoDia;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "glicemia")
class GlicemiaEntity {

  @Id @GeneratedValue private UUID id;

  private int valor;

  private LocalDateTime dataHora;

  @Enumerated(EnumType.STRING)
  private MomentoDia momentoDia;

  public GlicemiaEntity() {}

  public GlicemiaEntity(
      final UUID id, final int valor, final LocalDateTime dataHora, final MomentoDia momentoDia) {
    this.id = id;
    this.valor = valor;
    this.dataHora = dataHora;
    this.momentoDia = momentoDia;
  }

  static GlicemiaEntity fromDomain(final Glicemia glicemia) {
    return new GlicemiaEntity(
        glicemia.getId(), glicemia.getValor(), glicemia.getDataHora(), glicemia.getMomentoDia());
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

  Glicemia toDomain() {
    return Glicemia.of(id, valor, dataHora, momentoDia);
  }
}
