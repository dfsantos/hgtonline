package com.dfsantos.hgtonline.registry.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static com.dfsantos.hgtonline.registry.domain.Glicemia.of;
import static com.dfsantos.hgtonline.registry.domain.MomentoDia.ANTES_CAFE_DA_MANHA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class RegistrarHGTUseCaseTest {

    @Test
    @DisplayName("Deve registrar glicemia com sucesso")
    void deve_registrar_glicemia_com_sucesso() {
        // given
        var repository = mock(TesteGlicemiaRepository.class);
        var useCase = new RegistrarHGTUseCase(repository);

        // and
        var dataHora = LocalDateTime.now().minusMinutes(1);
        var glicemia = of(120);
        var input = new RegistrarHGTUseCase.Input(glicemia, dataHora, ANTES_CAFE_DA_MANHA);

        // and
        var teste = new TesteGlicemia(glicemia, input.dataHora(), input.momentoDia());
        when(repository.salvar(any(TesteGlicemia.class))).thenReturn(teste);

        // when
        var output = useCase.execute(input);

        // then
        assertNotNull(output.id());
        assertEquals(input.glicemia(), output.glicemia());
        assertEquals(input.dataHora(), output.dataHora());
        assertEquals(input.momentoDia(), output.momentoDia());
        verify(repository, times(1)).salvar(any(TesteGlicemia.class));
    }
}
