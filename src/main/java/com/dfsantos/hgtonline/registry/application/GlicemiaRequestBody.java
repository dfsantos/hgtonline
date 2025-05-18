package com.dfsantos.hgtonline.registry.application;

import com.dfsantos.hgtonline.registry.domain.MomentoDia;
import java.time.LocalDateTime;

record GlicemiaRequestBody(int valor, LocalDateTime dataHora, MomentoDia momentoDia) {}
