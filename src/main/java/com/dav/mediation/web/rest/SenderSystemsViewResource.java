package com.dav.mediation.web.rest;

import com.dav.mediation.service.SenderSystemsViewService;
import com.dav.mediation.web.rest.errors.BadRequestAlertException;
import com.dav.mediation.service.dto.SenderSystemsViewDTO;

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
 * REST controller for managing {@link com.dav.mediation.domain.SenderSystemsView}.
 */
@RestController
@RequestMapping("/api")
public class SenderSystemsViewResource {

    private final Logger log = LoggerFactory.getLogger(SenderSystemsViewResource.class);

    private static final String ENTITY_NAME = "mediationSenderSystemsView";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SenderSystemsViewService senderSystemsViewService;

    public SenderSystemsViewResource(SenderSystemsViewService senderSystemsViewService) {
        this.senderSystemsViewService = senderSystemsViewService;
    }

    /**
     * {@code POST  /sender-systems-views} : Create a new senderSystemsView.
     *
     * @param senderSystemsViewDTO the senderSystemsViewDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new senderSystemsViewDTO, or with status {@code 400 (Bad Request)} if the senderSystemsView has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sender-systems-views")
    public ResponseEntity<SenderSystemsViewDTO> createSenderSystemsView(@Valid @RequestBody SenderSystemsViewDTO senderSystemsViewDTO) throws URISyntaxException {
        log.debug("REST request to save SenderSystemsView : {}", senderSystemsViewDTO);
        if (senderSystemsViewDTO.getId() != null) {
            throw new BadRequestAlertException("A new senderSystemsView cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SenderSystemsViewDTO result = senderSystemsViewService.save(senderSystemsViewDTO);
        return ResponseEntity.created(new URI("/api/sender-systems-views/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sender-systems-views} : Updates an existing senderSystemsView.
     *
     * @param senderSystemsViewDTO the senderSystemsViewDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated senderSystemsViewDTO,
     * or with status {@code 400 (Bad Request)} if the senderSystemsViewDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the senderSystemsViewDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sender-systems-views")
    public ResponseEntity<SenderSystemsViewDTO> updateSenderSystemsView(@Valid @RequestBody SenderSystemsViewDTO senderSystemsViewDTO) throws URISyntaxException {
        log.debug("REST request to update SenderSystemsView : {}", senderSystemsViewDTO);
        if (senderSystemsViewDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SenderSystemsViewDTO result = senderSystemsViewService.save(senderSystemsViewDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, senderSystemsViewDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sender-systems-views} : get all the senderSystemsViews.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of senderSystemsViews in body.
     */
    @GetMapping("/sender-systems-views")
    public List<SenderSystemsViewDTO> getAllSenderSystemsViews() {
        log.debug("REST request to get all SenderSystemsViews");
        return senderSystemsViewService.findAll();
    }

    /**
     * {@code GET  /sender-systems-views/:id} : get the "id" senderSystemsView.
     *
     * @param id the id of the senderSystemsViewDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the senderSystemsViewDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sender-systems-views/{id}")
    public ResponseEntity<SenderSystemsViewDTO> getSenderSystemsView(@PathVariable Long id) {
        log.debug("REST request to get SenderSystemsView : {}", id);
        Optional<SenderSystemsViewDTO> senderSystemsViewDTO = senderSystemsViewService.findOne(id);
        return ResponseUtil.wrapOrNotFound(senderSystemsViewDTO);
    }

    /**
     * {@code DELETE  /sender-systems-views/:id} : delete the "id" senderSystemsView.
     *
     * @param id the id of the senderSystemsViewDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sender-systems-views/{id}")
    public ResponseEntity<Void> deleteSenderSystemsView(@PathVariable Long id) {
        log.debug("REST request to delete SenderSystemsView : {}", id);

        senderSystemsViewService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
