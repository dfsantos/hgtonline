package com.dfsantos.hgtonline.hgt.application;

import com.dfsantos.hgtonline.hgt.domain.MomentoDia;

import java.time.LocalDateTime;

record HgRequestBody(int nivelGlicemico, LocalDateTime dataHora, MomentoDia momentoDia) {
}
