package com.dfsantos.hgtonline.hgt.infrastructure;

import com.dfsantos.hgtonline.hgt.domain.TesteGlicemia;
import com.dfsantos.hgtonline.hgt.domain.TesteGlicemiaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static com.dfsantos.hgtonline.hgt.domain.Glicemia.of;
import static com.dfsantos.hgtonline.hgt.domain.MomentoDia.APOS_ALMOCO;
import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(TesteGlicemiaRepositoryImpl.class)
class TesteGlicemiaRepositoryImplIT {

    public static final int NIVEL_GLICEMICO = 120;

    @Autowired
    private TesteGlicemiaRepository testeGlicemiaRepository;

    @Test
    @DisplayName("Deve salvar e buscar registro de glicemia no banco H2")
    void salvar_e_buscar_glicemia() {
        // given
        var glicemia = of(NIVEL_GLICEMICO);
        var teste = new TesteGlicemia(glicemia, now(), APOS_ALMOCO);

        // when
        var salvo = testeGlicemiaRepository.salvar(teste);

        // then
        assertThat(salvo.getGlicemia().getNivel()).isEqualTo(120);

        // and
        var encontrado = testeGlicemiaRepository.buscarPorId(teste.getId());
        assertThat(encontrado).isPresent();
        assertThat(encontrado.get().getGlicemia().getNivel()).isEqualTo(120);
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando não houver registros")
    void buscar_todos_vazio() {
        var todos = testeGlicemiaRepository.buscarTodos();
        assertThat(todos).isEmpty();
    }
}
