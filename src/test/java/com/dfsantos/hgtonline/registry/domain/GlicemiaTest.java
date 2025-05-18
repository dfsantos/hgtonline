package com.dfsantos.hgtonline.registry.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GlicemiaTest {

  @Test
  @DisplayName("Deve criar um registro de glicemia válido com todos os campos corretos")
  void deve_criar_glicemia_valida() {
    // given
    var id = UUID.randomUUID();
    var valor = 110;
    var dataHora = LocalDateTime.now().minusMinutes(5);
    var momento = MomentoDia.ANTES_CAFE_DA_MANHA;

    // when
    var glicemia = new Glicemia(id, valor, dataHora, momento);

    // then
    assertEquals(id, glicemia.getId());
    assertEquals(valor, glicemia.getValor());
    assertEquals(dataHora, glicemia.getDataHora());
    assertEquals(momento, glicemia.getMomentoDia());
  }

  @Test
  @DisplayName("Não deve permitir valor de glicemia negativo")
  void nao_deve_permitir_valor_negativo() {
    // setup
    var id = UUID.randomUUID();
    var dataHora = LocalDateTime.now().minusMinutes(1);

    // expect
    assertThrows(
        IllegalArgumentException.class,
        () -> new Glicemia(id, -1, dataHora, MomentoDia.ANTES_CAFE_DA_MANHA));
  }

  @Test
  @DisplayName("Não deve permitir valor de glicemia igual a zero")
  void nao_deve_permitir_valor_zero() {
    // setup
    var id = UUID.randomUUID();
    var dataHora = LocalDateTime.now().minusMinutes(1);

    // expect
    assertThrows(
        IllegalArgumentException.class,
        () -> new Glicemia(id, 0, dataHora, MomentoDia.ANTES_CAFE_DA_MANHA));
  }

  @Test
  @DisplayName("Não deve permitir data/hora futura no registro de glicemia")
  void nao_deve_permitir_data_futura() {
    // setup
    var id = UUID.randomUUID();
    var dataHora = LocalDateTime.now().plusMinutes(10);

    // expect
    assertThrows(
        IllegalArgumentException.class,
        () -> new Glicemia(id, 100, dataHora, MomentoDia.ANTES_CAFE_DA_MANHA));
  }

  @Test
  @DisplayName("Não deve permitir momento do dia nulo no registro de glicemia")
  void nao_deve_permitir_momento_dia_nulo() {
    // setup
    var id = UUID.randomUUID();
    var dataHora = LocalDateTime.now().minusMinutes(1);

    // expect
    assertThrows(NullPointerException.class, () -> new Glicemia(id, 100, dataHora, null));
  }

  @Test
  @DisplayName("Não deve permitir data/hora nula no registro de glicemia")
  void nao_deve_permitir_data_hora_nula() {
    // setup
    var id = UUID.randomUUID();

    // expect
    assertThrows(
        NullPointerException.class,
        () -> new Glicemia(id, 100, null, MomentoDia.ANTES_CAFE_DA_MANHA));
  }
}
