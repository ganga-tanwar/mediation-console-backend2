package com.dav.mediation.web.rest;

import com.dav.mediation.service.EventStatusMasterService;
import com.dav.mediation.web.rest.errors.BadRequestAlertException;
import com.dav.mediation.service.dto.EventStatusMasterDTO;

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
 * REST controller for managing {@link com.dav.mediation.domain.EventStatusMaster}.
 */
@RestController
@RequestMapping("/api")
public class EventStatusMasterResource {

    private final Logger log = LoggerFactory.getLogger(EventStatusMasterResource.class);

    private static final String ENTITY_NAME = "mediationEventStatusMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EventStatusMasterService eventStatusMasterService;

    public EventStatusMasterResource(EventStatusMasterService eventStatusMasterService) {
        this.eventStatusMasterService = eventStatusMasterService;
    }

    /**
     * {@code POST  /event-status-masters} : Create a new eventStatusMaster.
     *
     * @param eventStatusMasterDTO the eventStatusMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new eventStatusMasterDTO, or with status {@code 400 (Bad Request)} if the eventStatusMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/event-status-masters")
    public ResponseEntity<EventStatusMasterDTO> createEventStatusMaster(@Valid @RequestBody EventStatusMasterDTO eventStatusMasterDTO) throws URISyntaxException {
        log.debug("REST request to save EventStatusMaster : {}", eventStatusMasterDTO);
        if (eventStatusMasterDTO.getId() != null) {
            throw new BadRequestAlertException("A new eventStatusMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EventStatusMasterDTO result = eventStatusMasterService.save(eventStatusMasterDTO);
        return ResponseEntity.created(new URI("/api/event-status-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /event-status-masters} : Updates an existing eventStatusMaster.
     *
     * @param eventStatusMasterDTO the eventStatusMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated eventStatusMasterDTO,
     * or with status {@code 400 (Bad Request)} if the eventStatusMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the eventStatusMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/event-status-masters")
    public ResponseEntity<EventStatusMasterDTO> updateEventStatusMaster(@Valid @RequestBody EventStatusMasterDTO eventStatusMasterDTO) throws URISyntaxException {
        log.debug("REST request to update EventStatusMaster : {}", eventStatusMasterDTO);
        if (eventStatusMasterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EventStatusMasterDTO result = eventStatusMasterService.save(eventStatusMasterDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, eventStatusMasterDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /event-status-masters} : get all the eventStatusMasters.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of eventStatusMasters in body.
     */
    @GetMapping("/event-status-masters")
    public List<EventStatusMasterDTO> getAllEventStatusMasters() {
        log.debug("REST request to get all EventStatusMasters");
        return eventStatusMasterService.findAll();
    }

    /**
     * {@code GET  /event-status-masters/:id} : get the "id" eventStatusMaster.
     *
     * @param id the id of the eventStatusMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the eventStatusMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/event-status-masters/{id}")
    public ResponseEntity<EventStatusMasterDTO> getEventStatusMaster(@PathVariable Long id) {
        log.debug("REST request to get EventStatusMaster : {}", id);
        Optional<EventStatusMasterDTO> eventStatusMasterDTO = eventStatusMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(eventStatusMasterDTO);
    }

    /**
     * {@code DELETE  /event-status-masters/:id} : delete the "id" eventStatusMaster.
     *
     * @param id the id of the eventStatusMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/event-status-masters/{id}")
    public ResponseEntity<Void> deleteEventStatusMaster(@PathVariable Long id) {
        log.debug("REST request to delete EventStatusMaster : {}", id);

        eventStatusMasterService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
