package com.dav.mediation.repository;

import com.dav.mediation.domain.FlowStatus;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the FlowStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FlowStatusRepository extends JpaRepository<FlowStatus, Long> {
}
