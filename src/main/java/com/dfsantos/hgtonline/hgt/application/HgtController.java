package com.dfsantos.hgtonline.registry.application;

import com.dfsantos.hgtonline.registry.domain.RegistrarHGTUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

import static com.dfsantos.hgtonline.registry.domain.Glicemia.of;
import static com.dfsantos.hgtonline.registry.domain.RegistrarHGTUseCase.Input;

@RestController
@RequestMapping("/api/hgts")
class HGTController {

    private final RegistrarHGTUseCase registrarHGTUseCase;

    public HGTController(final RegistrarHGTUseCase registrarHGTUseCase) {
        this.registrarHGTUseCase = registrarHGTUseCase;
    }

    @PostMapping
    public ResponseEntity<HGTResponseBody> registrar(@RequestBody final HGTRequestBody body) {
        var input = new Input(of(body.nivelGlicemico()), body.dataHora(), body.momentoDia());

        var output = registrarHGTUseCase.execute(input);
        var response = new HGTResponseBody(output.id(), output.glicemia().getNivel(), output.dataHora(), output.momentoDia());

        var location = String.format("/api/testes/%s", output.id());
        var uri = URI.create(location);
        return ResponseEntity.created(uri).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") UUID id) {
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(new ClientErrorResponse("Teste de glicemia inválido"));
    }

    record ClientErrorResponse(String message) {
    }
}
