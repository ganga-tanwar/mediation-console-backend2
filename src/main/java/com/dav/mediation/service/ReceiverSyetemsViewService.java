package com.dav.mediation.service;

import com.dav.mediation.domain.ReceiverSyetemsView;
import com.dav.mediation.repository.ReceiverSyetemsViewRepository;
import com.dav.mediation.service.dto.ReceiverSyetemsViewDTO;
import com.dav.mediation.service.mapper.ReceiverSyetemsViewMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ReceiverSyetemsView}.
 */
@Service
@Transactional
public class ReceiverSyetemsViewService {

    private final Logger log = LoggerFactory.getLogger(ReceiverSyetemsViewService.class);

    private final ReceiverSyetemsViewRepository receiverSyetemsViewRepository;

    private final ReceiverSyetemsViewMapper receiverSyetemsViewMapper;

    public ReceiverSyetemsViewService(ReceiverSyetemsViewRepository receiverSyetemsViewRepository, ReceiverSyetemsViewMapper receiverSyetemsViewMapper) {
        this.receiverSyetemsViewRepository = receiverSyetemsViewRepository;
        this.receiverSyetemsViewMapper = receiverSyetemsViewMapper;
    }

    /**
     * Save a receiverSyetemsView.
     *
     * @param receiverSyetemsViewDTO the entity to save.
     * @return the persisted entity.
     */
    public ReceiverSyetemsViewDTO save(ReceiverSyetemsViewDTO receiverSyetemsViewDTO) {
        log.debug("Request to save ReceiverSyetemsView : {}", receiverSyetemsViewDTO);
        ReceiverSyetemsView receiverSyetemsView = receiverSyetemsViewMapper.toEntity(receiverSyetemsViewDTO);
        receiverSyetemsView = receiverSyetemsViewRepository.save(receiverSyetemsView);
        return receiverSyetemsViewMapper.toDto(receiverSyetemsView);
    }

    /**
     * Get all the receiverSyetemsViews.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ReceiverSyetemsViewDTO> findAll() {
        log.debug("Request to get all ReceiverSyetemsViews");
        return receiverSyetemsViewRepository.findAll().stream()
            .map(receiverSyetemsViewMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one receiverSyetemsView by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ReceiverSyetemsViewDTO> findOne(Long id) {
        log.debug("Request to get ReceiverSyetemsView : {}", id);
        return receiverSyetemsViewRepository.findById(id)
            .map(receiverSyetemsViewMapper::toDto);
    }

    /**
     * Delete the receiverSyetemsView by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ReceiverSyetemsView : {}", id);

        receiverSyetemsViewRepository.deleteById(id);
    }
}
