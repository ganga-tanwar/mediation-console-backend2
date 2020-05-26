package com.dav.mediation.service;

import com.dav.mediation.domain.FlowNameTypeView;
import com.dav.mediation.repository.FlowNameTypeViewRepository;
import com.dav.mediation.service.dto.FlowNameTypeViewDTO;
import com.dav.mediation.service.mapper.FlowNameTypeViewMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link FlowNameTypeView}.
 */
@Service
@Transactional
public class FlowNameTypeViewService {

    private final Logger log = LoggerFactory.getLogger(FlowNameTypeViewService.class);

    private final FlowNameTypeViewRepository flowNameTypeViewRepository;

    private final FlowNameTypeViewMapper flowNameTypeViewMapper;

    public FlowNameTypeViewService(FlowNameTypeViewRepository flowNameTypeViewRepository, FlowNameTypeViewMapper flowNameTypeViewMapper) {
        this.flowNameTypeViewRepository = flowNameTypeViewRepository;
        this.flowNameTypeViewMapper = flowNameTypeViewMapper;
    }

    /**
     * Save a flowNameTypeView.
     *
     * @param flowNameTypeViewDTO the entity to save.
     * @return the persisted entity.
     */
    public FlowNameTypeViewDTO save(FlowNameTypeViewDTO flowNameTypeViewDTO) {
        log.debug("Request to save FlowNameTypeView : {}", flowNameTypeViewDTO);
        FlowNameTypeView flowNameTypeView = flowNameTypeViewMapper.toEntity(flowNameTypeViewDTO);
        flowNameTypeView = flowNameTypeViewRepository.save(flowNameTypeView);
        return flowNameTypeViewMapper.toDto(flowNameTypeView);
    }

    /**
     * Get all the flowNameTypeViews.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<FlowNameTypeViewDTO> findAll() {
        log.debug("Request to get all FlowNameTypeViews");
        return flowNameTypeViewRepository.findAll().stream()
            .map(flowNameTypeViewMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one flowNameTypeView by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FlowNameTypeViewDTO> findOne(Long id) {
        log.debug("Request to get FlowNameTypeView : {}", id);
        return flowNameTypeViewRepository.findById(id)
            .map(flowNameTypeViewMapper::toDto);
    }

    /**
     * Delete the flowNameTypeView by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FlowNameTypeView : {}", id);

        flowNameTypeViewRepository.deleteById(id);
    }
}
