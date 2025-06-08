package com.dfsantos.hgtonline.hgt.infrastructure;

import com.dfsantos.hgtonline.hgt.domain.HGT;
import com.dfsantos.hgtonline.hgt.domain.MomentoDia;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.dfsantos.hgtonline.hgt.domain.Glicemia.of;

@Entity
@Table(name = "teste_glicemia")
class TesteGlicemiaEntity {

    @Id
    private UUID id;

    private int nivelGlicemico;

    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    private MomentoDia momentoDia;

    public TesteGlicemiaEntity() {
    }

    public TesteGlicemiaEntity(
            final UUID id,
            final int nivelGlicemico,
            final LocalDateTime dataHora,
            final MomentoDia momentoDia) {
        this.id = id;
        this.nivelGlicemico = nivelGlicemico;
        this.dataHora = dataHora;
        this.momentoDia = momentoDia;
    }

    static TesteGlicemiaEntity fromDomain(final HGT teste) {
        return new TesteGlicemiaEntity(
                teste.getId(), teste.getGlicemia().getNivel(), teste.getDataHora(), teste.getMomentoDia());
    }

    public UUID getId() {
        return id;
    }

    public int getNivelGlicemico() {
        return nivelGlicemico;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public MomentoDia getMomentoDia() {
        return momentoDia;
    }

    HGT toDomain() {
        return new HGT(of(nivelGlicemico), dataHora, momentoDia);
    }
}
