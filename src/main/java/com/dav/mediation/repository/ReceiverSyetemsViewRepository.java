package com.dav.mediation.repository;

import com.dav.mediation.domain.ReceiverSyetemsView;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ReceiverSyetemsView entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReceiverSyetemsViewRepository extends JpaRepository<ReceiverSyetemsView, Long> {
}
