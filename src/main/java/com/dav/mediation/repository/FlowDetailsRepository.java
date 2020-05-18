package com.dav.mediation.repository;

import com.dav.mediation.domain.FlowDetails;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the FlowDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FlowDetailsRepository extends JpaRepository<FlowDetails, Long>, JpaSpecificationExecutor<FlowDetails> {
}
