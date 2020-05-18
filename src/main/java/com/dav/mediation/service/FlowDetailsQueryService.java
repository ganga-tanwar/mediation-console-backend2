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

import com.dav.mediation.domain.FlowDetails;
import com.dav.mediation.domain.*; // for static metamodels
import com.dav.mediation.repository.FlowDetailsRepository;
import com.dav.mediation.service.dto.FlowDetailsCriteria;
import com.dav.mediation.service.dto.FlowDetailsDTO;
import com.dav.mediation.service.mapper.FlowDetailsMapper;

/**
 * Service for executing complex queries for {@link FlowDetails} entities in the database.
 * The main input is a {@link FlowDetailsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FlowDetailsDTO} or a {@link Page} of {@link FlowDetailsDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FlowDetailsQueryService extends QueryService<FlowDetails> {

    private final Logger log = LoggerFactory.getLogger(FlowDetailsQueryService.class);

    private final FlowDetailsRepository flowDetailsRepository;

    private final FlowDetailsMapper flowDetailsMapper;

    public FlowDetailsQueryService(FlowDetailsRepository flowDetailsRepository, FlowDetailsMapper flowDetailsMapper) {
        this.flowDetailsRepository = flowDetailsRepository;
        this.flowDetailsMapper = flowDetailsMapper;
    }

    /**
     * Return a {@link List} of {@link FlowDetailsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<FlowDetailsDTO> findByCriteria(FlowDetailsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<FlowDetails> specification = createSpecification(criteria);
        return flowDetailsMapper.toDto(flowDetailsRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link FlowDetailsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<FlowDetailsDTO> findByCriteria(FlowDetailsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<FlowDetails> specification = createSpecification(criteria);
        return flowDetailsRepository.findAll(specification, page)
            .map(flowDetailsMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(FlowDetailsCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<FlowDetails> specification = createSpecification(criteria);
        return flowDetailsRepository.count(specification);
    }

    /**
     * Function to convert {@link FlowDetailsCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<FlowDetails> createSpecification(FlowDetailsCriteria criteria) {
        Specification<FlowDetails> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), FlowDetails_.id));
            }
            if (criteria.getFlowId() != null) {
                specification = specification.and(buildSpecification(criteria.getFlowId(), FlowDetails_.flowId));
            }
            if (criteria.getFlowName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFlowName(), FlowDetails_.flowName));
            }
            if (criteria.getMediationSystem() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMediationSystem(), FlowDetails_.mediationSystem));
            }
            if (criteria.getSource() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSource(), FlowDetails_.source));
            }
            if (criteria.getDestination() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDestination(), FlowDetails_.destination));
            }
            if (criteria.getFileName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFileName(), FlowDetails_.fileName));
            }
            if (criteria.getTransactionDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTransactionDate(), FlowDetails_.transactionDate));
            }
            if (criteria.getTransactionId() != null) {
                specification = specification.and(buildSpecification(criteria.getTransactionId(), FlowDetails_.transactionId));
            }
            if (criteria.getFormat() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFormat(), FlowDetails_.format));
            }
            if (criteria.getSourceEndpointInterface() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSourceEndpointInterface(), FlowDetails_.sourceEndpointInterface));
            }
            if (criteria.getDestinationEndpointInterface() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDestinationEndpointInterface(), FlowDetails_.destinationEndpointInterface));
            }
            if (criteria.getAcknowledgmentExpected() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAcknowledgmentExpected(), FlowDetails_.acknowledgmentExpected));
            }
            if (criteria.getAcknowledgmentReceived() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAcknowledgmentReceived(), FlowDetails_.acknowledgmentReceived));
            }
        }
        return specification;
    }
}
