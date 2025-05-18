package com.dfsantos.hgtonline.registry.domain;

import static com.dfsantos.hgtonline.registry.domain.MomentoDia.*;
import static java.util.UUID.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.dfsantos.hgtonline.registry.application.RegistrarGlicemiaUseCase;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RegistrarGlicemiaUseCaseImplTest {

  @Test
  @DisplayName("Deve registrar glicemia com sucesso")
  void deve_registrar_glicemia_com_sucesso() {
    // given
    var repository = mock(GlicemiaRepository.class);
    var useCase = new RegistrarGlicemiaUseCaseImpl(repository);

    // and
    var dataHora = LocalDateTime.now().minusMinutes(1);
    var input = new RegistrarGlicemiaUseCase.Input(120, dataHora, ANTES_CAFE_DA_MANHA);
    var glicemia = new Glicemia(randomUUID(), input.valor(), input.dataHora(), input.momentoDia());

    // and
    when(repository.salvar(any(Glicemia.class))).thenReturn(glicemia);

    // when
    var output = useCase.execute(input);

    // then
    assertNotNull(output.id());
    assertEquals(input.valor(), output.valor());
    assertEquals(input.dataHora(), output.dataHora());
    assertEquals(input.momentoDia(), output.momentoDia());
    verify(repository, times(1)).salvar(any(Glicemia.class));
  }

  @Test
  @DisplayName("Deve lançar exceção ao tentar registrar glicemia inválida")
  void deve_lancar_excecao_para_glicemia_invalida() {
    // given
    var repository = mock(GlicemiaRepository.class);
    var useCase = new RegistrarGlicemiaUseCaseImpl(repository);

    // and
    var dataHora = LocalDateTime.now().minusMinutes(1);
    var input = new RegistrarGlicemiaUseCase.Input(-10, dataHora, ANTES_CAFE_DA_MANHA);

    // when / then
    assertThrows(IllegalArgumentException.class, () -> useCase.execute(input));
    verify(repository, never()).salvar(any());
  }
}
