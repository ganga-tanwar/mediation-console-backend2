package com.dav.mediation.service;

import com.dav.mediation.domain.FlowStatus;
import com.dav.mediation.repository.FlowStatusRepository;
import com.dav.mediation.service.dto.FlowStatusDTO;
import com.dav.mediation.service.mapper.FlowStatusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link FlowStatus}.
 */
@Service
@Transactional
public class FlowStatusService {

    private final Logger log = LoggerFactory.getLogger(FlowStatusService.class);

    private final FlowStatusRepository flowStatusRepository;

    private final FlowStatusMapper flowStatusMapper;

    public FlowStatusService(FlowStatusRepository flowStatusRepository, FlowStatusMapper flowStatusMapper) {
        this.flowStatusRepository = flowStatusRepository;
        this.flowStatusMapper = flowStatusMapper;
    }

    /**
     * Save a flowStatus.
     *
     * @param flowStatusDTO the entity to save.
     * @return the persisted entity.
     */
    public FlowStatusDTO save(FlowStatusDTO flowStatusDTO) {
        log.debug("Request to save FlowStatus : {}", flowStatusDTO);
        FlowStatus flowStatus = flowStatusMapper.toEntity(flowStatusDTO);
        flowStatus = flowStatusRepository.save(flowStatus);
        return flowStatusMapper.toDto(flowStatus);
    }

    /**
     * Get all the flowStatuses.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<FlowStatusDTO> findAll() {
        log.debug("Request to get all FlowStatuses");
        return flowStatusRepository.findAll().stream()
            .map(flowStatusMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one flowStatus by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FlowStatusDTO> findOne(Long id) {
        log.debug("Request to get FlowStatus : {}", id);
        return flowStatusRepository.findById(id)
            .map(flowStatusMapper::toDto);
    }

    /**
     * Delete the flowStatus by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FlowStatus : {}", id);

        flowStatusRepository.deleteById(id);
    }
}
