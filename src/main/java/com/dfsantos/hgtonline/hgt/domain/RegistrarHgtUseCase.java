package com.dfsantos.hgtonline.registry.domain;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RegistrarHGTUseCase {

    private final TesteGlicemiaRepository testeGlicemiaRepository;

    public RegistrarHGTUseCase(final TesteGlicemiaRepository testeGlicemiaRepository) {
        this.testeGlicemiaRepository = testeGlicemiaRepository;
    }

    public Output execute(final Input input) {
        var teste = new TesteGlicemia(input.glicemia(), input.dataHora(), input.momentoDia());

        var testeRegistrado = testeGlicemiaRepository.salvar(teste);

        return new Output(
                testeRegistrado.getId(),
                testeRegistrado.getGlicemia(),
                testeRegistrado.getDataHora(),
                testeRegistrado.getMomentoDia());
    }

    public record Input(Glicemia glicemia, LocalDateTime dataHora, MomentoDia momentoDia) {
    }

    public record Output(UUID id, Glicemia glicemia, LocalDateTime dataHora, MomentoDia momentoDia) {
    }
}
