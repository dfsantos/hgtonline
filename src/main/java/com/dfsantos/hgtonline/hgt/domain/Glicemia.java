package com.dfsantos.hgtonline.registry.domain;

import java.io.Serializable;

public class Glicemia implements Serializable {

  private final int nivel;

  private Glicemia(final int nivel) {
    if (nivel <= 0) {
      throw new IllegalArgumentException("Valor de glicemia deve ser maior que zero");
    }

    this.nivel = nivel;
  }

  public static Glicemia of(final int valor) {
    return new Glicemia(valor);
  }

  public int getNivel() {
    return nivel;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Glicemia glicemia)) return false;

    return nivel == glicemia.nivel;
  }

  @Override
  public int hashCode() {
    return Integer.hashCode(nivel);
  }
}
