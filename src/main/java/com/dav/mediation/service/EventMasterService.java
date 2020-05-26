package com.dav.mediation.service;

import com.dav.mediation.domain.EventMaster;
import com.dav.mediation.repository.EventMasterRepository;
import com.dav.mediation.service.dto.EventMasterDTO;
import com.dav.mediation.service.mapper.EventMasterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link EventMaster}.
 */
@Service
@Transactional
public class EventMasterService {

    private final Logger log = LoggerFactory.getLogger(EventMasterService.class);

    private final EventMasterRepository eventMasterRepository;

    private final EventMasterMapper eventMasterMapper;

    public EventMasterService(EventMasterRepository eventMasterRepository, EventMasterMapper eventMasterMapper) {
        this.eventMasterRepository = eventMasterRepository;
        this.eventMasterMapper = eventMasterMapper;
    }

    /**
     * Save a eventMaster.
     *
     * @param eventMasterDTO the entity to save.
     * @return the persisted entity.
     */
    public EventMasterDTO save(EventMasterDTO eventMasterDTO) {
        log.debug("Request to save EventMaster : {}", eventMasterDTO);
        EventMaster eventMaster = eventMasterMapper.toEntity(eventMasterDTO);
        eventMaster = eventMasterRepository.save(eventMaster);
        return eventMasterMapper.toDto(eventMaster);
    }

    /**
     * Get all the eventMasters.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EventMasterDTO> findAll() {
        log.debug("Request to get all EventMasters");
        return eventMasterRepository.findAll().stream()
            .map(eventMasterMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one eventMaster by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EventMasterDTO> findOne(Long id) {
        log.debug("Request to get EventMaster : {}", id);
        return eventMasterRepository.findById(id)
            .map(eventMasterMapper::toDto);
    }

    /**
     * Delete the eventMaster by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete EventMaster : {}", id);

        eventMasterRepository.deleteById(id);
    }
}
