package com.dfsantos.hgtonline.registry.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GlicemiaTest {

  private static final int VALOR_GLICEMIA_VALIDO = 110;

  @Test
  @DisplayName("Deve instanciar um valor de glicemia válido")
  void deve_criar_glicemia_valida() {
    // given
    var valor = VALOR_GLICEMIA_VALIDO;

    // when
    var glicemia = Glicemia.of(valor);

    // then
    assertEquals(valor, glicemia.getNivel());
  }

  @Test
  @DisplayName("Não deve permitir valor de glicemia negativo")
  void nao_deve_permitir_valor_negativo() {
    // expect
    assertThrows(IllegalArgumentException.class, () -> Glicemia.of(-1));
  }

  @Test
  @DisplayName("Não deve permitir valor de glicemia igual a zero")
  void nao_deve_permitir_valor_zero() {
    // expect
    assertThrows(IllegalArgumentException.class, () -> Glicemia.of(0));
  }

  // testar a igualdade de objetos Glicemia
  @Test
  @DisplayName("Deve considerar glicemias com o mesmo valor como iguais")
  void deve_considerar_glicemias_iguais() {
    // given
    var glicemia1 = Glicemia.of(VALOR_GLICEMIA_VALIDO);
    var glicemia2 = Glicemia.of(VALOR_GLICEMIA_VALIDO);

    // then
    assertEquals(glicemia1, glicemia2);
  }

  @Test
  @DisplayName("Deve considerar glicemias com valores diferentes como diferentes")
  void deve_considerar_glicemias_diferentes() {
    // given
    var glicemia1 = Glicemia.of(VALOR_GLICEMIA_VALIDO);
    var glicemia2 = Glicemia.of(VALOR_GLICEMIA_VALIDO + 10);

    // then
    assertEquals(false, glicemia1.equals(glicemia2));
  }
}
