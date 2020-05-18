package com.dav.mediation.service;

import com.dav.mediation.domain.FlowEventDetails;
import com.dav.mediation.repository.FlowEventDetailsRepository;
import com.dav.mediation.service.dto.FlowEventDetailsDTO;
import com.dav.mediation.service.mapper.FlowEventDetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link FlowEventDetails}.
 */
@Service
@Transactional
public class FlowEventDetailsService {

    private final Logger log = LoggerFactory.getLogger(FlowEventDetailsService.class);

    private final FlowEventDetailsRepository flowEventDetailsRepository;

    private final FlowEventDetailsMapper flowEventDetailsMapper;

    public FlowEventDetailsService(FlowEventDetailsRepository flowEventDetailsRepository, FlowEventDetailsMapper flowEventDetailsMapper) {
        this.flowEventDetailsRepository = flowEventDetailsRepository;
        this.flowEventDetailsMapper = flowEventDetailsMapper;
    }

    /**
     * Save a flowEventDetails.
     *
     * @param flowEventDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    public FlowEventDetailsDTO save(FlowEventDetailsDTO flowEventDetailsDTO) {
        log.debug("Request to save FlowEventDetails : {}", flowEventDetailsDTO);
        FlowEventDetails flowEventDetails = flowEventDetailsMapper.toEntity(flowEventDetailsDTO);
        flowEventDetails = flowEventDetailsRepository.save(flowEventDetails);
        return flowEventDetailsMapper.toDto(flowEventDetails);
    }

    /**
     * Get all the flowEventDetails.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<FlowEventDetailsDTO> findAll() {
        log.debug("Request to get all FlowEventDetails");
        return flowEventDetailsRepository.findAll().stream()
            .map(flowEventDetailsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one flowEventDetails by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FlowEventDetailsDTO> findOne(Long id) {
        log.debug("Request to get FlowEventDetails : {}", id);
        return flowEventDetailsRepository.findById(id)
            .map(flowEventDetailsMapper::toDto);
    }

    /**
     * Delete the flowEventDetails by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FlowEventDetails : {}", id);

        flowEventDetailsRepository.deleteById(id);
    }
}
