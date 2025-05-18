package com.dfsantos.hgtonline.registry.application;

import static com.dfsantos.hgtonline.registry.application.RegistrarGlicemiaUseCase.*;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/glicemias")
class GlicemiaController {

  private final RegistrarGlicemiaUseCase registrarGlicemiaUseCase;

  public GlicemiaController(final RegistrarGlicemiaUseCase registrarGlicemiaUseCase) {
    this.registrarGlicemiaUseCase = registrarGlicemiaUseCase;
  }

  @PostMapping
  public Output registrarGlicemia(@RequestBody final GlicemiaRequestBody requestBody) {
    var input = new Input(requestBody.valor(), requestBody.dataHora(), requestBody.momentoDia());
    return registrarGlicemiaUseCase.execute(input);
  }
}
