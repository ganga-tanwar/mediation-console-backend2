package com.dav.mediation.service;

import com.dav.mediation.domain.FlowEvents;
import com.dav.mediation.repository.FlowEventsRepository;
import com.dav.mediation.service.dto.FlowEventsDTO;
import com.dav.mediation.service.mapper.FlowEventsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link FlowEvents}.
 */
@Service
@Transactional
public class FlowEventsService {

    private final Logger log = LoggerFactory.getLogger(FlowEventsService.class);

    private final FlowEventsRepository flowEventsRepository;

    private final FlowEventsMapper flowEventsMapper;

    public FlowEventsService(FlowEventsRepository flowEventsRepository, FlowEventsMapper flowEventsMapper) {
        this.flowEventsRepository = flowEventsRepository;
        this.flowEventsMapper = flowEventsMapper;
    }

    /**
     * Save a flowEvents.
     *
     * @param flowEventsDTO the entity to save.
     * @return the persisted entity.
     */
    public FlowEventsDTO save(FlowEventsDTO flowEventsDTO) {
        log.debug("Request to save FlowEvents : {}", flowEventsDTO);
        FlowEvents flowEvents = flowEventsMapper.toEntity(flowEventsDTO);
        flowEvents = flowEventsRepository.save(flowEvents);
        return flowEventsMapper.toDto(flowEvents);
    }

    /**
     * Get all the flowEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<FlowEventsDTO> findAll() {
        log.debug("Request to get all FlowEvents");
        return flowEventsRepository.findAll().stream()
            .map(flowEventsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one flowEvents by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FlowEventsDTO> findOne(Long id) {
        log.debug("Request to get FlowEvents : {}", id);
        return flowEventsRepository.findById(id)
            .map(flowEventsMapper::toDto);
    }

    /**
     * Delete the flowEvents by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FlowEvents : {}", id);

        flowEventsRepository.deleteById(id);
    }
}
