package com.dav.mediation.web.rest;

import com.dav.mediation.service.FlowDetailsService;
import com.dav.mediation.web.rest.errors.BadRequestAlertException;
import com.dav.mediation.service.dto.FlowDetailsDTO;
import com.dav.mediation.service.dto.FlowDetailsCriteria;
import com.dav.mediation.service.FlowDetailsQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.dav.mediation.domain.FlowDetails}.
 */
@RestController
@RequestMapping("/api")
public class FlowDetailsResource {

    private final Logger log = LoggerFactory.getLogger(FlowDetailsResource.class);

    private static final String ENTITY_NAME = "mediationFlowDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FlowDetailsService flowDetailsService;

    private final FlowDetailsQueryService flowDetailsQueryService;

    public FlowDetailsResource(FlowDetailsService flowDetailsService, FlowDetailsQueryService flowDetailsQueryService) {
        this.flowDetailsService = flowDetailsService;
        this.flowDetailsQueryService = flowDetailsQueryService;
    }

    /**
     * {@code POST  /flow-details} : Create a new flowDetails.
     *
     * @param flowDetailsDTO the flowDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new flowDetailsDTO, or with status {@code 400 (Bad Request)} if the flowDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/flow-details")
    public ResponseEntity<FlowDetailsDTO> createFlowDetails(@Valid @RequestBody FlowDetailsDTO flowDetailsDTO) throws URISyntaxException {
        log.debug("REST request to save FlowDetails : {}", flowDetailsDTO);
        if (flowDetailsDTO.getId() != null) {
            throw new BadRequestAlertException("A new flowDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FlowDetailsDTO result = flowDetailsService.save(flowDetailsDTO);
        return ResponseEntity.created(new URI("/api/flow-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /flow-details} : Updates an existing flowDetails.
     *
     * @param flowDetailsDTO the flowDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated flowDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the flowDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the flowDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/flow-details")
    public ResponseEntity<FlowDetailsDTO> updateFlowDetails(@Valid @RequestBody FlowDetailsDTO flowDetailsDTO) throws URISyntaxException {
        log.debug("REST request to update FlowDetails : {}", flowDetailsDTO);
        if (flowDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FlowDetailsDTO result = flowDetailsService.save(flowDetailsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, flowDetailsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /flow-details} : get all the flowDetails.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of flowDetails in body.
     */
    @GetMapping("/flow-details")
    public ResponseEntity<List<FlowDetailsDTO>> getAllFlowDetails(FlowDetailsCriteria criteria, Pageable pageable) {
        log.debug("REST request to get FlowDetails by criteria: {}", criteria);
        Page<FlowDetailsDTO> page = flowDetailsQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /flow-details/count} : count all the flowDetails.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/flow-details/count")
    public ResponseEntity<Long> countFlowDetails(FlowDetailsCriteria criteria) {
        log.debug("REST request to count FlowDetails by criteria: {}", criteria);
        return ResponseEntity.ok().body(flowDetailsQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /flow-details/:id} : get the "id" flowDetails.
     *
     * @param id the id of the flowDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the flowDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/flow-details/{id}")
    public ResponseEntity<FlowDetailsDTO> getFlowDetails(@PathVariable Long id) {
        log.debug("REST request to get FlowDetails : {}", id);
        Optional<FlowDetailsDTO> flowDetailsDTO = flowDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(flowDetailsDTO);
    }

    /**
     * {@code DELETE  /flow-details/:id} : delete the "id" flowDetails.
     *
     * @param id the id of the flowDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/flow-details/{id}")
    public ResponseEntity<Void> deleteFlowDetails(@PathVariable Long id) {
        log.debug("REST request to delete FlowDetails : {}", id);

        flowDetailsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
