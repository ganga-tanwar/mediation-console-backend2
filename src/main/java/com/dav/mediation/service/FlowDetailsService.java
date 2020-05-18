package com.dav.mediation.service;

import com.dav.mediation.domain.FlowDetails;
import com.dav.mediation.repository.FlowDetailsRepository;
import com.dav.mediation.service.dto.FlowDetailsDTO;
import com.dav.mediation.service.mapper.FlowDetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link FlowDetails}.
 */
@Service
@Transactional
public class FlowDetailsService {

    private final Logger log = LoggerFactory.getLogger(FlowDetailsService.class);

    private final FlowDetailsRepository flowDetailsRepository;

    private final FlowDetailsMapper flowDetailsMapper;

    public FlowDetailsService(FlowDetailsRepository flowDetailsRepository, FlowDetailsMapper flowDetailsMapper) {
        this.flowDetailsRepository = flowDetailsRepository;
        this.flowDetailsMapper = flowDetailsMapper;
    }

    /**
     * Save a flowDetails.
     *
     * @param flowDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    public FlowDetailsDTO save(FlowDetailsDTO flowDetailsDTO) {
        log.debug("Request to save FlowDetails : {}", flowDetailsDTO);
        FlowDetails flowDetails = flowDetailsMapper.toEntity(flowDetailsDTO);
        flowDetails = flowDetailsRepository.save(flowDetails);
        return flowDetailsMapper.toDto(flowDetails);
    }

    /**
     * Get all the flowDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<FlowDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FlowDetails");
        return flowDetailsRepository.findAll(pageable)
            .map(flowDetailsMapper::toDto);
    }


    /**
     * Get one flowDetails by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FlowDetailsDTO> findOne(Long id) {
        log.debug("Request to get FlowDetails : {}", id);
        return flowDetailsRepository.findById(id)
            .map(flowDetailsMapper::toDto);
    }

    /**
     * Delete the flowDetails by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FlowDetails : {}", id);

        flowDetailsRepository.deleteById(id);
    }
}
