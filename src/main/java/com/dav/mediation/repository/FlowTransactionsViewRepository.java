package com.dav.mediation.repository;

import com.dav.mediation.domain.FlowTransactionsView;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the FlowTransactionsView entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FlowTransactionsViewRepository extends JpaRepository<FlowTransactionsView, Long>, JpaSpecificationExecutor<FlowTransactionsView> {
}
