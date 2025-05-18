package com.dfsantos.hgtonline.registry.application;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class GlicemiaControllerIT {

  @Autowired private MockMvc mockMvc;

  @Test
  @DisplayName("Deve registrar glicemia com sucesso via API REST")
  void registrar_glicemia_sucesso() throws Exception {
    var body =
        """
      {
        "valor": 120,
        "dataHora": "%s",
        "momentoDia": "ANTES_CAFE_DA_MANHA"
      }
      """
            .formatted(LocalDateTime.now().toString());

    mockMvc
        .perform(
            post("/api/glicemias")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(body))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").exists())
        .andExpect(jsonPath("$.valor").value(120))
        .andExpect(jsonPath("$.momentoDia").value("ANTES_CAFE_DA_MANHA"));
  }
}
