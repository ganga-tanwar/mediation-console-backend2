package com.dav.mediation.web.rest;

import com.dav.mediation.service.FlowStatusService;
import com.dav.mediation.web.rest.errors.BadRequestAlertException;
import com.dav.mediation.service.dto.FlowStatusDTO;

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
 * REST controller for managing {@link com.dav.mediation.domain.FlowStatus}.
 */
@RestController
@RequestMapping("/api")
public class FlowStatusResource {

    private final Logger log = LoggerFactory.getLogger(FlowStatusResource.class);

    private static final String ENTITY_NAME = "mediationFlowStatus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FlowStatusService flowStatusService;

    public FlowStatusResource(FlowStatusService flowStatusService) {
        this.flowStatusService = flowStatusService;
    }

    /**
     * {@code POST  /flow-statuses} : Create a new flowStatus.
     *
     * @param flowStatusDTO the flowStatusDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new flowStatusDTO, or with status {@code 400 (Bad Request)} if the flowStatus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/flow-statuses")
    public ResponseEntity<FlowStatusDTO> createFlowStatus(@Valid @RequestBody FlowStatusDTO flowStatusDTO) throws URISyntaxException {
        log.debug("REST request to save FlowStatus : {}", flowStatusDTO);
        if (flowStatusDTO.getId() != null) {
            throw new BadRequestAlertException("A new flowStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FlowStatusDTO result = flowStatusService.save(flowStatusDTO);
        return ResponseEntity.created(new URI("/api/flow-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /flow-statuses} : Updates an existing flowStatus.
     *
     * @param flowStatusDTO the flowStatusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated flowStatusDTO,
     * or with status {@code 400 (Bad Request)} if the flowStatusDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the flowStatusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/flow-statuses")
    public ResponseEntity<FlowStatusDTO> updateFlowStatus(@Valid @RequestBody FlowStatusDTO flowStatusDTO) throws URISyntaxException {
        log.debug("REST request to update FlowStatus : {}", flowStatusDTO);
        if (flowStatusDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FlowStatusDTO result = flowStatusService.save(flowStatusDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, flowStatusDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /flow-statuses} : get all the flowStatuses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of flowStatuses in body.
     */
    @GetMapping("/flow-statuses")
    public List<FlowStatusDTO> getAllFlowStatuses() {
        log.debug("REST request to get all FlowStatuses");
        return flowStatusService.findAll();
    }

    /**
     * {@code GET  /flow-statuses/:id} : get the "id" flowStatus.
     *
     * @param id the id of the flowStatusDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the flowStatusDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/flow-statuses/{id}")
    public ResponseEntity<FlowStatusDTO> getFlowStatus(@PathVariable Long id) {
        log.debug("REST request to get FlowStatus : {}", id);
        Optional<FlowStatusDTO> flowStatusDTO = flowStatusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(flowStatusDTO);
    }

    /**
     * {@code DELETE  /flow-statuses/:id} : delete the "id" flowStatus.
     *
     * @param id the id of the flowStatusDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/flow-statuses/{id}")
    public ResponseEntity<Void> deleteFlowStatus(@PathVariable Long id) {
        log.debug("REST request to delete FlowStatus : {}", id);

        flowStatusService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
