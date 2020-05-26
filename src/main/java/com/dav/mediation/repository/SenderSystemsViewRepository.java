package com.dav.mediation.repository;

import com.dav.mediation.domain.SenderSystemsView;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SenderSystemsView entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SenderSystemsViewRepository extends JpaRepository<SenderSystemsView, Long> {
}
