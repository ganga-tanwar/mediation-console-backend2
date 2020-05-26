package com.dav.mediation.service;

import com.dav.mediation.domain.SenderSystemsView;
import com.dav.mediation.repository.SenderSystemsViewRepository;
import com.dav.mediation.service.dto.SenderSystemsViewDTO;
import com.dav.mediation.service.mapper.SenderSystemsViewMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link SenderSystemsView}.
 */
@Service
@Transactional
public class SenderSystemsViewService {

    private final Logger log = LoggerFactory.getLogger(SenderSystemsViewService.class);

    private final SenderSystemsViewRepository senderSystemsViewRepository;

    private final SenderSystemsViewMapper senderSystemsViewMapper;

    public SenderSystemsViewService(SenderSystemsViewRepository senderSystemsViewRepository, SenderSystemsViewMapper senderSystemsViewMapper) {
        this.senderSystemsViewRepository = senderSystemsViewRepository;
        this.senderSystemsViewMapper = senderSystemsViewMapper;
    }

    /**
     * Save a senderSystemsView.
     *
     * @param senderSystemsViewDTO the entity to save.
     * @return the persisted entity.
     */
    public SenderSystemsViewDTO save(SenderSystemsViewDTO senderSystemsViewDTO) {
        log.debug("Request to save SenderSystemsView : {}", senderSystemsViewDTO);
        SenderSystemsView senderSystemsView = senderSystemsViewMapper.toEntity(senderSystemsViewDTO);
        senderSystemsView = senderSystemsViewRepository.save(senderSystemsView);
        return senderSystemsViewMapper.toDto(senderSystemsView);
    }

    /**
     * Get all the senderSystemsViews.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<SenderSystemsViewDTO> findAll() {
        log.debug("Request to get all SenderSystemsViews");
        return senderSystemsViewRepository.findAll().stream()
            .map(senderSystemsViewMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one senderSystemsView by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SenderSystemsViewDTO> findOne(Long id) {
        log.debug("Request to get SenderSystemsView : {}", id);
        return senderSystemsViewRepository.findById(id)
            .map(senderSystemsViewMapper::toDto);
    }

    /**
     * Delete the senderSystemsView by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete SenderSystemsView : {}", id);

        senderSystemsViewRepository.deleteById(id);
    }
}
