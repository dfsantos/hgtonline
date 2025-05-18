package com.dfsantos.hgtonline.registry.infrastructure;

import static com.dfsantos.hgtonline.registry.domain.MomentoDia.ANTES_CAFE_DA_MANHA;
import static org.assertj.core.api.Assertions.assertThat;

import com.dfsantos.hgtonline.registry.domain.Glicemia;
import com.dfsantos.hgtonline.registry.domain.GlicemiaRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(GlicemiaRepositoryImpl.class)
class GlicemiaRepositoryImplIT {

  @Autowired private GlicemiaRepository glicemiaRepository;

  @Test
  @DisplayName("Deve salvar e buscar registro de glicemia no banco H2")
  void salvar_e_buscar_glicemia() {
    var dataHora = LocalDateTime.now();
    var glicemia = Glicemia.of(120, dataHora, ANTES_CAFE_DA_MANHA);

    var salvo = glicemiaRepository.salvar(glicemia);

    assertThat(salvo.getId()).isNotNull();
    assertThat(salvo.getValor()).isEqualTo(120);
    assertThat(salvo.getDataHora()).isEqualTo(dataHora);
    assertThat(salvo.getMomentoDia()).isEqualTo(ANTES_CAFE_DA_MANHA);

    var encontrado = glicemiaRepository.buscarPorId(salvo.getId());
    assertThat(encontrado).isPresent();
    assertThat(encontrado.get().getValor()).isEqualTo(120);
  }

  @Test
  @DisplayName("Deve retornar lista vazia quando não houver registros")
  void buscar_todos_vazio() {
    var todos = glicemiaRepository.buscarTodos();
    assertThat(todos).isEmpty();
  }
}
