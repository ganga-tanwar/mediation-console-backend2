package com.dav.mediation.web.rest;

import com.dav.mediation.service.FlowNameTypeViewService;
import com.dav.mediation.web.rest.errors.BadRequestAlertException;
import com.dav.mediation.service.dto.FlowNameTypeViewDTO;

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
 * REST controller for managing {@link com.dav.mediation.domain.FlowNameTypeView}.
 */
@RestController
@RequestMapping("/api")
public class FlowNameTypeViewResource {

    private final Logger log = LoggerFactory.getLogger(FlowNameTypeViewResource.class);

    private static final String ENTITY_NAME = "mediationFlowNameTypeView";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FlowNameTypeViewService flowNameTypeViewService;

    public FlowNameTypeViewResource(FlowNameTypeViewService flowNameTypeViewService) {
        this.flowNameTypeViewService = flowNameTypeViewService;
    }

    /**
     * {@code POST  /flow-name-type-views} : Create a new flowNameTypeView.
     *
     * @param flowNameTypeViewDTO the flowNameTypeViewDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new flowNameTypeViewDTO, or with status {@code 400 (Bad Request)} if the flowNameTypeView has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/flow-name-type-views")
    public ResponseEntity<FlowNameTypeViewDTO> createFlowNameTypeView(@Valid @RequestBody FlowNameTypeViewDTO flowNameTypeViewDTO) throws URISyntaxException {
        log.debug("REST request to save FlowNameTypeView : {}", flowNameTypeViewDTO);
        if (flowNameTypeViewDTO.getId() != null) {
            throw new BadRequestAlertException("A new flowNameTypeView cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FlowNameTypeViewDTO result = flowNameTypeViewService.save(flowNameTypeViewDTO);
        return ResponseEntity.created(new URI("/api/flow-name-type-views/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /flow-name-type-views} : Updates an existing flowNameTypeView.
     *
     * @param flowNameTypeViewDTO the flowNameTypeViewDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated flowNameTypeViewDTO,
     * or with status {@code 400 (Bad Request)} if the flowNameTypeViewDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the flowNameTypeViewDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/flow-name-type-views")
    public ResponseEntity<FlowNameTypeViewDTO> updateFlowNameTypeView(@Valid @RequestBody FlowNameTypeViewDTO flowNameTypeViewDTO) throws URISyntaxException {
        log.debug("REST request to update FlowNameTypeView : {}", flowNameTypeViewDTO);
        if (flowNameTypeViewDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FlowNameTypeViewDTO result = flowNameTypeViewService.save(flowNameTypeViewDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, flowNameTypeViewDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /flow-name-type-views} : get all the flowNameTypeViews.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of flowNameTypeViews in body.
     */
    @GetMapping("/flow-name-type-views")
    public List<FlowNameTypeViewDTO> getAllFlowNameTypeViews() {
        log.debug("REST request to get all FlowNameTypeViews");
        return flowNameTypeViewService.findAll();
    }

    /**
     * {@code GET  /flow-name-type-views/:id} : get the "id" flowNameTypeView.
     *
     * @param id the id of the flowNameTypeViewDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the flowNameTypeViewDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/flow-name-type-views/{id}")
    public ResponseEntity<FlowNameTypeViewDTO> getFlowNameTypeView(@PathVariable Long id) {
        log.debug("REST request to get FlowNameTypeView : {}", id);
        Optional<FlowNameTypeViewDTO> flowNameTypeViewDTO = flowNameTypeViewService.findOne(id);
        return ResponseUtil.wrapOrNotFound(flowNameTypeViewDTO);
    }

    /**
     * {@code DELETE  /flow-name-type-views/:id} : delete the "id" flowNameTypeView.
     *
     * @param id the id of the flowNameTypeViewDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/flow-name-type-views/{id}")
    public ResponseEntity<Void> deleteFlowNameTypeView(@PathVariable Long id) {
        log.debug("REST request to delete FlowNameTypeView : {}", id);

        flowNameTypeViewService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
