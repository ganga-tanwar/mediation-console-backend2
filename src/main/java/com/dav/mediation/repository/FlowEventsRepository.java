package com.dav.mediation.repository;

import com.dav.mediation.domain.FlowEvents;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the FlowEvents entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FlowEventsRepository extends JpaRepository<FlowEvents, Long> {
}
