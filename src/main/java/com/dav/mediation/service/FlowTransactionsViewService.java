package com.dav.mediation.service;

import com.dav.mediation.domain.FlowTransactionsView;
import com.dav.mediation.repository.FlowTransactionsViewRepository;
import com.dav.mediation.service.dto.FlowTransactionsViewDTO;
import com.dav.mediation.service.mapper.FlowTransactionsViewMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link FlowTransactionsView}.
 */
@Service
@Transactional
public class FlowTransactionsViewService {

    private final Logger log = LoggerFactory.getLogger(FlowTransactionsViewService.class);

    private final FlowTransactionsViewRepository flowTransactionsViewRepository;

    private final FlowTransactionsViewMapper flowTransactionsViewMapper;

    public FlowTransactionsViewService(FlowTransactionsViewRepository flowTransactionsViewRepository, FlowTransactionsViewMapper flowTransactionsViewMapper) {
        this.flowTransactionsViewRepository = flowTransactionsViewRepository;
        this.flowTransactionsViewMapper = flowTransactionsViewMapper;
    }

    /**
     * Save a flowTransactionsView.
     *
     * @param flowTransactionsViewDTO the entity to save.
     * @return the persisted entity.
     */
    public FlowTransactionsViewDTO save(FlowTransactionsViewDTO flowTransactionsViewDTO) {
        log.debug("Request to save FlowTransactionsView : {}", flowTransactionsViewDTO);
        FlowTransactionsView flowTransactionsView = flowTransactionsViewMapper.toEntity(flowTransactionsViewDTO);
        flowTransactionsView = flowTransactionsViewRepository.save(flowTransactionsView);
        return flowTransactionsViewMapper.toDto(flowTransactionsView);
    }

    /**
     * Get all the flowTransactionsViews.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<FlowTransactionsViewDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FlowTransactionsViews");
        return flowTransactionsViewRepository.findAll(pageable)
            .map(flowTransactionsViewMapper::toDto);
    }


    /**
     * Get one flowTransactionsView by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FlowTransactionsViewDTO> findOne(Long id) {
        log.debug("Request to get FlowTransactionsView : {}", id);
        return flowTransactionsViewRepository.findById(id)
            .map(flowTransactionsViewMapper::toDto);
    }

    /**
     * Delete the flowTransactionsView by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FlowTransactionsView : {}", id);

        flowTransactionsViewRepository.deleteById(id);
    }
}
