package com.dav.mediation.web.rest;

import com.dav.mediation.service.FlowEventsService;
import com.dav.mediation.web.rest.errors.BadRequestAlertException;
import com.dav.mediation.service.dto.FlowEventsDTO;

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
 * REST controller for managing {@link com.dav.mediation.domain.FlowEvents}.
 */
@RestController
@RequestMapping("/api")
public class FlowEventsResource {

    private final Logger log = LoggerFactory.getLogger(FlowEventsResource.class);

    private static final String ENTITY_NAME = "mediationFlowEvents";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FlowEventsService flowEventsService;

    public FlowEventsResource(FlowEventsService flowEventsService) {
        this.flowEventsService = flowEventsService;
    }

    /**
     * {@code POST  /flow-events} : Create a new flowEvents.
     *
     * @param flowEventsDTO the flowEventsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new flowEventsDTO, or with status {@code 400 (Bad Request)} if the flowEvents has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/flow-events")
    public ResponseEntity<FlowEventsDTO> createFlowEvents(@Valid @RequestBody FlowEventsDTO flowEventsDTO) throws URISyntaxException {
        log.debug("REST request to save FlowEvents : {}", flowEventsDTO);
        if (flowEventsDTO.getId() != null) {
            throw new BadRequestAlertException("A new flowEvents cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FlowEventsDTO result = flowEventsService.save(flowEventsDTO);
        return ResponseEntity.created(new URI("/api/flow-events/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /flow-events} : Updates an existing flowEvents.
     *
     * @param flowEventsDTO the flowEventsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated flowEventsDTO,
     * or with status {@code 400 (Bad Request)} if the flowEventsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the flowEventsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/flow-events")
    public ResponseEntity<FlowEventsDTO> updateFlowEvents(@Valid @RequestBody FlowEventsDTO flowEventsDTO) throws URISyntaxException {
        log.debug("REST request to update FlowEvents : {}", flowEventsDTO);
        if (flowEventsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FlowEventsDTO result = flowEventsService.save(flowEventsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, flowEventsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /flow-events} : get all the flowEvents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of flowEvents in body.
     */
    @GetMapping("/flow-events")
    public List<FlowEventsDTO> getAllFlowEvents() {
        log.debug("REST request to get all FlowEvents");
        return flowEventsService.findAll();
    }

    /**
     * {@code GET  /flow-events/:id} : get the "id" flowEvents.
     *
     * @param id the id of the flowEventsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the flowEventsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/flow-events/{id}")
    public ResponseEntity<FlowEventsDTO> getFlowEvents(@PathVariable Long id) {
        log.debug("REST request to get FlowEvents : {}", id);
        Optional<FlowEventsDTO> flowEventsDTO = flowEventsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(flowEventsDTO);
    }

    /**
     * {@code DELETE  /flow-events/:id} : delete the "id" flowEvents.
     *
     * @param id the id of the flowEventsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/flow-events/{id}")
    public ResponseEntity<Void> deleteFlowEvents(@PathVariable Long id) {
        log.debug("REST request to delete FlowEvents : {}", id);

        flowEventsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
