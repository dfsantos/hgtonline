package com.dfsantos.hgtonline.hgt.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TesteGlicemiaRepository {
    TesteGlicemia salvar(TesteGlicemia testeGlicemia);

    Optional<TesteGlicemia> buscarPorId(UUID id);

    List<TesteGlicemia> buscarTodos();

    void excluir(UUID idTeste);
}
