package com.dav.mediation.web.rest;

import com.dav.mediation.service.FlowTransactionsViewService;
import com.dav.mediation.web.rest.errors.BadRequestAlertException;
import com.dav.mediation.service.dto.FlowTransactionsViewDTO;
import com.dav.mediation.service.dto.FlowTransactionsViewCriteria;
import com.dav.mediation.service.FlowTransactionsViewQueryService;

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
 * REST controller for managing {@link com.dav.mediation.domain.FlowTransactionsView}.
 */
@RestController
@RequestMapping("/api")
public class FlowTransactionsViewResource {

    private final Logger log = LoggerFactory.getLogger(FlowTransactionsViewResource.class);

    private static final String ENTITY_NAME = "mediationFlowTransactionsView";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FlowTransactionsViewService flowTransactionsViewService;

    private final FlowTransactionsViewQueryService flowTransactionsViewQueryService;

    public FlowTransactionsViewResource(FlowTransactionsViewService flowTransactionsViewService, FlowTransactionsViewQueryService flowTransactionsViewQueryService) {
        this.flowTransactionsViewService = flowTransactionsViewService;
        this.flowTransactionsViewQueryService = flowTransactionsViewQueryService;
    }

    /**
     * {@code POST  /flow-transactions-views} : Create a new flowTransactionsView.
     *
     * @param flowTransactionsViewDTO the flowTransactionsViewDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new flowTransactionsViewDTO, or with status {@code 400 (Bad Request)} if the flowTransactionsView has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/flow-transactions-views")
    public ResponseEntity<FlowTransactionsViewDTO> createFlowTransactionsView(@Valid @RequestBody FlowTransactionsViewDTO flowTransactionsViewDTO) throws URISyntaxException {
        log.debug("REST request to save FlowTransactionsView : {}", flowTransactionsViewDTO);
        if (flowTransactionsViewDTO.getId() != null) {
            throw new BadRequestAlertException("A new flowTransactionsView cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FlowTransactionsViewDTO result = flowTransactionsViewService.save(flowTransactionsViewDTO);
        return ResponseEntity.created(new URI("/api/flow-transactions-views/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /flow-transactions-views} : Updates an existing flowTransactionsView.
     *
     * @param flowTransactionsViewDTO the flowTransactionsViewDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated flowTransactionsViewDTO,
     * or with status {@code 400 (Bad Request)} if the flowTransactionsViewDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the flowTransactionsViewDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/flow-transactions-views")
    public ResponseEntity<FlowTransactionsViewDTO> updateFlowTransactionsView(@Valid @RequestBody FlowTransactionsViewDTO flowTransactionsViewDTO) throws URISyntaxException {
        log.debug("REST request to update FlowTransactionsView : {}", flowTransactionsViewDTO);
        if (flowTransactionsViewDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FlowTransactionsViewDTO result = flowTransactionsViewService.save(flowTransactionsViewDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, flowTransactionsViewDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /flow-transactions-views} : get all the flowTransactionsViews.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of flowTransactionsViews in body.
     */
    @GetMapping("/flow-transactions-views")
    public ResponseEntity<List<FlowTransactionsViewDTO>> getAllFlowTransactionsViews(FlowTransactionsViewCriteria criteria, Pageable pageable) {
        log.debug("REST request to get FlowTransactionsViews by criteria: {}", criteria);
        Page<FlowTransactionsViewDTO> page = flowTransactionsViewQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /flow-transactions-views/count} : count all the flowTransactionsViews.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/flow-transactions-views/count")
    public ResponseEntity<Long> countFlowTransactionsViews(FlowTransactionsViewCriteria criteria) {
        log.debug("REST request to count FlowTransactionsViews by criteria: {}", criteria);
        return ResponseEntity.ok().body(flowTransactionsViewQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /flow-transactions-views/:id} : get the "id" flowTransactionsView.
     *
     * @param id the id of the flowTransactionsViewDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the flowTransactionsViewDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/flow-transactions-views/{id}")
    public ResponseEntity<FlowTransactionsViewDTO> getFlowTransactionsView(@PathVariable Long id) {
        log.debug("REST request to get FlowTransactionsView : {}", id);
        Optional<FlowTransactionsViewDTO> flowTransactionsViewDTO = flowTransactionsViewService.findOne(id);
        return ResponseUtil.wrapOrNotFound(flowTransactionsViewDTO);
    }

    /**
     * {@code DELETE  /flow-transactions-views/:id} : delete the "id" flowTransactionsView.
     *
     * @param id the id of the flowTransactionsViewDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/flow-transactions-views/{id}")
    public ResponseEntity<Void> deleteFlowTransactionsView(@PathVariable Long id) {
        log.debug("REST request to delete FlowTransactionsView : {}", id);

        flowTransactionsViewService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
