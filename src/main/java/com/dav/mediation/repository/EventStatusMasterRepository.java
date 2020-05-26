package com.dav.mediation.repository;

import com.dav.mediation.domain.EventStatusMaster;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EventStatusMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EventStatusMasterRepository extends JpaRepository<EventStatusMaster, Long> {
}
