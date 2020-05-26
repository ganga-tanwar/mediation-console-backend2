package com.dav.mediation.repository;

import com.dav.mediation.domain.FlowNameTypeView;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the FlowNameTypeView entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FlowNameTypeViewRepository extends JpaRepository<FlowNameTypeView, Long> {
}
