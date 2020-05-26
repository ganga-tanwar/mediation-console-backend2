package com.dav.mediation.service;

import com.dav.mediation.domain.EventStatusMaster;
import com.dav.mediation.repository.EventStatusMasterRepository;
import com.dav.mediation.service.dto.EventStatusMasterDTO;
import com.dav.mediation.service.mapper.EventStatusMasterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link EventStatusMaster}.
 */
@Service
@Transactional
public class EventStatusMasterService {

    private final Logger log = LoggerFactory.getLogger(EventStatusMasterService.class);

    private final EventStatusMasterRepository eventStatusMasterRepository;

    private final EventStatusMasterMapper eventStatusMasterMapper;

    public EventStatusMasterService(EventStatusMasterRepository eventStatusMasterRepository, EventStatusMasterMapper eventStatusMasterMapper) {
        this.eventStatusMasterRepository = eventStatusMasterRepository;
        this.eventStatusMasterMapper = eventStatusMasterMapper;
    }

    /**
     * Save a eventStatusMaster.
     *
     * @param eventStatusMasterDTO the entity to save.
     * @return the persisted entity.
     */
    public EventStatusMasterDTO save(EventStatusMasterDTO eventStatusMasterDTO) {
        log.debug("Request to save EventStatusMaster : {}", eventStatusMasterDTO);
        EventStatusMaster eventStatusMaster = eventStatusMasterMapper.toEntity(eventStatusMasterDTO);
        eventStatusMaster = eventStatusMasterRepository.save(eventStatusMaster);
        return eventStatusMasterMapper.toDto(eventStatusMaster);
    }

    /**
     * Get all the eventStatusMasters.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EventStatusMasterDTO> findAll() {
        log.debug("Request to get all EventStatusMasters");
        return eventStatusMasterRepository.findAll().stream()
            .map(eventStatusMasterMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one eventStatusMaster by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EventStatusMasterDTO> findOne(Long id) {
        log.debug("Request to get EventStatusMaster : {}", id);
        return eventStatusMasterRepository.findById(id)
            .map(eventStatusMasterMapper::toDto);
    }

    /**
     * Delete the eventStatusMaster by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete EventStatusMaster : {}", id);

        eventStatusMasterRepository.deleteById(id);
    }
}
