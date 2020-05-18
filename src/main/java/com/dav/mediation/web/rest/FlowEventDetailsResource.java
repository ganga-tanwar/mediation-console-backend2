package com.dav.mediation.web.rest;

import com.dav.mediation.service.FlowEventDetailsService;
import com.dav.mediation.web.rest.errors.BadRequestAlertException;
import com.dav.mediation.service.dto.FlowEventDetailsDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.dav.mediation.domain.FlowEventDetails}.
 */
@RestController
@RequestMapping("/api")
public class FlowEventDetailsResource {

    private final Logger log = LoggerFactory.getLogger(FlowEventDetailsResource.class);

    private static final String ENTITY_NAME = "mediationFlowEventDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FlowEventDetailsService flowEventDetailsService;

    public FlowEventDetailsResource(FlowEventDetailsService flowEventDetailsService) {
        this.flowEventDetailsService = flowEventDetailsService;
    }

    /**
     * {@code POST  /flow-event-details} : Create a new flowEventDetails.
     *
     * @param flowEventDetailsDTO the flowEventDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new flowEventDetailsDTO, or with status {@code 400 (Bad Request)} if the flowEventDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/flow-event-details")
    public ResponseEntity<FlowEventDetailsDTO> createFlowEventDetails(@Valid @RequestBody FlowEventDetailsDTO flowEventDetailsDTO) throws URISyntaxException {
        log.debug("REST request to save FlowEventDetails : {}", flowEventDetailsDTO);
        if (flowEventDetailsDTO.getId() != null) {
            throw new BadRequestAlertException("A new flowEventDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FlowEventDetailsDTO result = flowEventDetailsService.save(flowEventDetailsDTO);
        return ResponseEntity.created(new URI("/api/flow-event-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /flow-event-details} : Updates an existing flowEventDetails.
     *
     * @param flowEventDetailsDTO the flowEventDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated flowEventDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the flowEventDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the flowEventDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/flow-event-details")
    public ResponseEntity<FlowEventDetailsDTO> updateFlowEventDetails(@Valid @RequestBody FlowEventDetailsDTO flowEventDetailsDTO) throws URISyntaxException {
        log.debug("REST request to update FlowEventDetails : {}", flowEventDetailsDTO);
        if (flowEventDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FlowEventDetailsDTO result = flowEventDetailsService.save(flowEventDetailsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, flowEventDetailsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /flow-event-details} : get all the flowEventDetails.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of flowEventDetails in body.
     */
    @GetMapping("/flow-event-details")
    public List<FlowEventDetailsDTO> getAllFlowEventDetails() {
        log.debug("REST request to get all FlowEventDetails");
        return flowEventDetailsService.findAll();
    }

    /**
     * {@code GET  /flow-event-details/:id} : get the "id" flowEventDetails.
     *
     * @param id the id of the flowEventDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the flowEventDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/flow-event-details/{id}")
    public ResponseEntity<FlowEventDetailsDTO> getFlowEventDetails(@PathVariable Long id) {
        log.debug("REST request to get FlowEventDetails : {}", id);
        Optional<FlowEventDetailsDTO> flowEventDetailsDTO = flowEventDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(flowEventDetailsDTO);
    }

    /**
     * {@code DELETE  /flow-event-details/:id} : delete the "id" flowEventDetails.
     *
     * @param id the id of the flowEventDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/flow-event-details/{id}")
    public ResponseEntity<Void> deleteFlowEventDetails(@PathVariable Long id) {
        log.debug("REST request to delete FlowEventDetails : {}", id);

        flowEventDetailsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
