package com.dav.mediation.repository;

import com.dav.mediation.domain.EventMaster;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EventMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EventMasterRepository extends JpaRepository<EventMaster, Long> {
}
