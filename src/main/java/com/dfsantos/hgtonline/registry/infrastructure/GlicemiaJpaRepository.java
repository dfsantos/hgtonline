package com.dfsantos.hgtonline.registry.infrastructure;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface GlicemiaJpaRepository extends JpaRepository<GlicemiaEntity, UUID> {}
