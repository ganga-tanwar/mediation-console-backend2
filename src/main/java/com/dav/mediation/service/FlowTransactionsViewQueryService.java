package com.dav.mediation.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.dav.mediation.domain.FlowTransactionsView;
import com.dav.mediation.domain.*; // for static metamodels
import com.dav.mediation.repository.FlowTransactionsViewRepository;
import com.dav.mediation.service.dto.FlowTransactionsViewCriteria;
import com.dav.mediation.service.dto.FlowTransactionsViewDTO;
import com.dav.mediation.service.mapper.FlowTransactionsViewMapper;

/**
 * Service for executing complex queries for {@link FlowTransactionsView} entities in the database.
 * The main input is a {@link FlowTransactionsViewCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FlowTransactionsViewDTO} or a {@link Page} of {@link FlowTransactionsViewDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FlowTransactionsViewQueryService extends QueryService<FlowTransactionsView> {

    private final Logger log = LoggerFactory.getLogger(FlowTransactionsViewQueryService.class);

    private final FlowTransactionsViewRepository flowTransactionsViewRepository;

    private final FlowTransactionsViewMapper flowTransactionsViewMapper;

    public FlowTransactionsViewQueryService(FlowTransactionsViewRepository flowTransactionsViewRepository, FlowTransactionsViewMapper flowTransactionsViewMapper) {
        this.flowTransactionsViewRepository = flowTransactionsViewRepository;
        this.flowTransactionsViewMapper = flowTransactionsViewMapper;
    }

    /**
     * Return a {@link List} of {@link FlowTransactionsViewDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<FlowTransactionsViewDTO> findByCriteria(FlowTransactionsViewCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<FlowTransactionsView> specification = createSpecification(criteria);
        return flowTransactionsViewMapper.toDto(flowTransactionsViewRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link FlowTransactionsViewDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<FlowTransactionsViewDTO> findByCriteria(FlowTransactionsViewCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<FlowTransactionsView> specification = createSpecification(criteria);
        return flowTransactionsViewRepository.findAll(specification, page)
            .map(flowTransactionsViewMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(FlowTransactionsViewCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<FlowTransactionsView> specification = createSpecification(criteria);
        return flowTransactionsViewRepository.count(specification);
    }

    /**
     * Function to convert {@link FlowTransactionsViewCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<FlowTransactionsView> createSpecification(FlowTransactionsViewCriteria criteria) {
        Specification<FlowTransactionsView> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), FlowTransactionsView_.id));
            }
            if (criteria.getFlowId() != null) {
                specification = specification.and(buildSpecification(criteria.getFlowId(), FlowTransactionsView_.flowId));
            }
            if (criteria.getFlowName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFlowName(), FlowTransactionsView_.flowName));
            }
            if (criteria.getFlowType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFlowType(), FlowTransactionsView_.flowType));
            }
            if (criteria.getSenderIs() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSenderIs(), FlowTransactionsView_.senderIs));
            }
            if (criteria.getSenderProtocol() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSenderProtocol(), FlowTransactionsView_.senderProtocol));
            }
            if (criteria.getSourceInstance() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSourceInstance(), FlowTransactionsView_.sourceInstance));
            }
            if (criteria.getReceiverIs() != null) {
                specification = specification.and(buildStringSpecification(criteria.getReceiverIs(), FlowTransactionsView_.receiverIs));
            }
            if (criteria.getReceiverProtocol() != null) {
                specification = specification.and(buildStringSpecification(criteria.getReceiverProtocol(), FlowTransactionsView_.receiverProtocol));
            }
            if (criteria.getTargetInstance() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTargetInstance(), FlowTransactionsView_.targetInstance));
            }
            if (criteria.getFilePayload() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFilePayload(), FlowTransactionsView_.filePayload));
            }
            if (criteria.getFlowDateTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFlowDateTime(), FlowTransactionsView_.flowDateTime));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStatus(), FlowTransactionsView_.status));
            }
        }
        return specification;
    }
}
