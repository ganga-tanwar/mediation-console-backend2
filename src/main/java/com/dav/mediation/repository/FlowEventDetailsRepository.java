package com.dav.mediation.repository;

import com.dav.mediation.domain.FlowEventDetails;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the FlowEventDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FlowEventDetailsRepository extends JpaRepository<FlowEventDetails, Long> {
}
