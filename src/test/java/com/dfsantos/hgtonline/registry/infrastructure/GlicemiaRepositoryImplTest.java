package com.dfsantos.hgtonline.registry.infrastructure;

import static com.dfsantos.hgtonline.registry.domain.Glicemia.*;
import static java.util.UUID.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.dfsantos.hgtonline.registry.domain.MomentoDia;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GlicemiaRepositoryImplTest {

  private GlicemiaJpaRepository jpaRepository;
  private GlicemiaRepositoryImpl repository;

  @BeforeEach
  void setUp() {
    jpaRepository = mock(GlicemiaJpaRepository.class);
    repository = new GlicemiaRepositoryImpl(jpaRepository);
  }

  @Test
  @DisplayName("Deve salvar nova entidade de glicemia")
  void salvar_deve_salvar_nova_glicemia() {
    var glicemia = of(120, LocalDateTime.now(), MomentoDia.ANTES_CAFE_DA_MANHA);
    var entity =
        new GlicemiaEntity(
            randomUUID(), glicemia.getValor(), glicemia.getDataHora(), glicemia.getMomentoDia());

    when(jpaRepository.save(any(GlicemiaEntity.class))).thenReturn(entity);

    var resultado = repository.salvar(glicemia);

    assertNotNull(resultado.getId());
    assertEquals(glicemia.getValor(), resultado.getValor());
    assertEquals(glicemia.getDataHora(), resultado.getDataHora());
    assertEquals(glicemia.getMomentoDia(), resultado.getMomentoDia());
    verify(jpaRepository).save(any(GlicemiaEntity.class));
  }

  @Test
  @DisplayName("Deve salvar entidade existente de glicemia")
  void salvar_deve_salvar_glicemia_existente() {
    var glicemia = of(randomUUID(), 120, LocalDateTime.now(), MomentoDia.ANTES_CAFE_DA_MANHA);
    var entity = GlicemiaEntity.fromDomain(glicemia);

    when(jpaRepository.save(any(GlicemiaEntity.class))).thenReturn(entity);

    var resultado = repository.salvar(glicemia);

    assertEquals(glicemia.getId(), resultado.getId());
    assertEquals(glicemia.getValor(), resultado.getValor());
    assertEquals(glicemia.getDataHora(), resultado.getDataHora());
    assertEquals(glicemia.getMomentoDia(), resultado.getMomentoDia());
    verify(jpaRepository).save(any(GlicemiaEntity.class));
  }

  @Test
  @DisplayName("Deve buscar por id e retornar preenchido quando encontrado")
  void buscar_por_id_deve_retornar_optional_preenchido() {
    var id = randomUUID();
    var entity = new GlicemiaEntity(id, 100, LocalDateTime.now(), MomentoDia.APOS_ALMOCO);

    when(jpaRepository.findById(id)).thenReturn(Optional.of(entity));

    var resultado = repository.buscarPorId(id);

    assertTrue(resultado.isPresent());
    assertEquals(id, resultado.get().getId());
    verify(jpaRepository).findById(id);
  }

  @Test
  @DisplayName("Deve buscar por id e retornar vazio quando não encontrado")
  void buscar_por_id_deve_retornar_optional_vazio() {
    var id = randomUUID();

    when(jpaRepository.findById(id)).thenReturn(Optional.empty());

    var resultado = repository.buscarPorId(id);

    assertTrue(resultado.isEmpty());
    verify(jpaRepository).findById(id);
  }

  @Test
  @DisplayName("Deve buscar todos os registros de glicemia")
  void buscar_todos_deve_retornar_lista() {
    var entity1 =
        new GlicemiaEntity(randomUUID(), 90, LocalDateTime.now(), MomentoDia.ANTES_JANTAR);
    var entity2 =
        new GlicemiaEntity(randomUUID(), 110, LocalDateTime.now(), MomentoDia.APOS_JANTAR);

    when(jpaRepository.findAll()).thenReturn(List.of(entity1, entity2));

    var resultado = repository.buscarTodos();

    assertEquals(2, resultado.size());
    verify(jpaRepository).findAll();
  }
}
