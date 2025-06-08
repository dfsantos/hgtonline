package com.dfsantos.hgtonline.hgt.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface GlicemiaJpaRepository extends JpaRepository<TesteGlicemiaEntity, UUID> {
}
