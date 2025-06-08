package com.dfsantos.hgtonline.hgt.infrastructure;

import com.dfsantos.hgtonline.hgt.domain.TesteGlicemia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static com.dfsantos.hgtonline.hgt.domain.Glicemia.of;
import static com.dfsantos.hgtonline.hgt.domain.MomentoDia.*;
import static java.time.LocalDateTime.now;
import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class TesteGlicemiaRepositoryImplTest {

    private GlicemiaJpaRepository jpaRepository;
    private TesteGlicemiaRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        jpaRepository = mock(GlicemiaJpaRepository.class);
        repository = new TesteGlicemiaRepositoryImpl(jpaRepository);
    }

    @Test
    @DisplayName("Deve salvar teste de glicemia")
    void deve_salvar_glicemia() {
        // given
        var glicemia = of(120);
        var teste = new TesteGlicemia(glicemia, now(), APOS_JANTAR);
        var entity = TesteGlicemiaEntity.fromDomain(teste);

        // and
        when(jpaRepository.save(any(TesteGlicemiaEntity.class))).thenReturn(entity);

        // when
        var resultado = repository.salvar(teste);

        // then
        assertEquals(glicemia.getNivel(), resultado.getGlicemia().getNivel());
        verify(jpaRepository).save(any(TesteGlicemiaEntity.class));
    }

    @Test
    @DisplayName("Deve buscar por id e retornar preenchido quando encontrado")
    void deve_retornar_optional_preenchido() {
        // given
        var id = randomUUID();
        var entity = new TesteGlicemiaEntity(id, 100, now(), APOS_ALMOCO);

        // and
        when(jpaRepository.findById(id)).thenReturn(Optional.of(entity));

        // when
        var resultado = repository.buscarPorId(id);

        // then
        assertTrue(resultado.isPresent());
        verify(jpaRepository).findById(id);
    }

    @Test
    @DisplayName("Deve buscar por id e retornar vazio quando não encontrado")
    void deve_retornar_optional_vazio() {
        // given
        var id = randomUUID();

        // and
        when(jpaRepository.findById(id)).thenReturn(Optional.empty());

        // when
        var resultado = repository.buscarPorId(id);

        // then
        assertTrue(resultado.isEmpty());
        verify(jpaRepository).findById(id);
    }

    @Test
    @DisplayName("Deve buscar todos os testes de glicemia")
    void deve_retornar_lista() {
        // given
        var entity1 = new TesteGlicemiaEntity(randomUUID(), 90, now(), ANTES_JANTAR);
        var entity2 = new TesteGlicemiaEntity(randomUUID(), 110, now(), APOS_JANTAR);

        // and
        when(jpaRepository.findAll()).thenReturn(List.of(entity1, entity2));

        // when
        var resultado = repository.buscarTodos();

        // then
        assertEquals(2, resultado.size());
        verify(jpaRepository).findAll();
    }

    @Test
    @DisplayName(
            "Deve buscar todos os testes de glicemia e retornar uma lista vazia quando não houver testes")
    void deve_retornar_lista_vazia() {
        // given
        when(jpaRepository.findAll()).thenReturn(List.of());

        // when
        var resultado = repository.buscarTodos();

        // then
        assertTrue(resultado.isEmpty());
        verify(jpaRepository).findAll();
    }
}
