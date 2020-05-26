package com.dav.mediation.web.rest;

import com.dav.mediation.service.EventMasterService;
import com.dav.mediation.web.rest.errors.BadRequestAlertException;
import com.dav.mediation.service.dto.EventMasterDTO;

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
 * REST controller for managing {@link com.dav.mediation.domain.EventMaster}.
 */
@RestController
@RequestMapping("/api")
public class EventMasterResource {

    private final Logger log = LoggerFactory.getLogger(EventMasterResource.class);

    private static final String ENTITY_NAME = "mediationEventMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EventMasterService eventMasterService;

    public EventMasterResource(EventMasterService eventMasterService) {
        this.eventMasterService = eventMasterService;
    }

    /**
     * {@code POST  /event-masters} : Create a new eventMaster.
     *
     * @param eventMasterDTO the eventMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new eventMasterDTO, or with status {@code 400 (Bad Request)} if the eventMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/event-masters")
    public ResponseEntity<EventMasterDTO> createEventMaster(@Valid @RequestBody EventMasterDTO eventMasterDTO) throws URISyntaxException {
        log.debug("REST request to save EventMaster : {}", eventMasterDTO);
        if (eventMasterDTO.getId() != null) {
            throw new BadRequestAlertException("A new eventMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EventMasterDTO result = eventMasterService.save(eventMasterDTO);
        return ResponseEntity.created(new URI("/api/event-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /event-masters} : Updates an existing eventMaster.
     *
     * @param eventMasterDTO the eventMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated eventMasterDTO,
     * or with status {@code 400 (Bad Request)} if the eventMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the eventMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/event-masters")
    public ResponseEntity<EventMasterDTO> updateEventMaster(@Valid @RequestBody EventMasterDTO eventMasterDTO) throws URISyntaxException {
        log.debug("REST request to update EventMaster : {}", eventMasterDTO);
        if (eventMasterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EventMasterDTO result = eventMasterService.save(eventMasterDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, eventMasterDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /event-masters} : get all the eventMasters.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of eventMasters in body.
     */
    @GetMapping("/event-masters")
    public List<EventMasterDTO> getAllEventMasters() {
        log.debug("REST request to get all EventMasters");
        return eventMasterService.findAll();
    }

    /**
     * {@code GET  /event-masters/:id} : get the "id" eventMaster.
     *
     * @param id the id of the eventMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the eventMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/event-masters/{id}")
    public ResponseEntity<EventMasterDTO> getEventMaster(@PathVariable Long id) {
        log.debug("REST request to get EventMaster : {}", id);
        Optional<EventMasterDTO> eventMasterDTO = eventMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(eventMasterDTO);
    }

    /**
     * {@code DELETE  /event-masters/:id} : delete the "id" eventMaster.
     *
     * @param id the id of the eventMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/event-masters/{id}")
    public ResponseEntity<Void> deleteEventMaster(@PathVariable Long id) {
        log.debug("REST request to delete EventMaster : {}", id);

        eventMasterService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
