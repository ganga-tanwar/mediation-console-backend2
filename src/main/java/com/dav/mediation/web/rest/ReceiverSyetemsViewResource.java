package com.dav.mediation.web.rest;

import com.dav.mediation.service.ReceiverSyetemsViewService;
import com.dav.mediation.web.rest.errors.BadRequestAlertException;
import com.dav.mediation.service.dto.ReceiverSyetemsViewDTO;

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
 * REST controller for managing {@link com.dav.mediation.domain.ReceiverSyetemsView}.
 */
@RestController
@RequestMapping("/api")
public class ReceiverSyetemsViewResource {

    private final Logger log = LoggerFactory.getLogger(ReceiverSyetemsViewResource.class);

    private static final String ENTITY_NAME = "mediationReceiverSyetemsView";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReceiverSyetemsViewService receiverSyetemsViewService;

    public ReceiverSyetemsViewResource(ReceiverSyetemsViewService receiverSyetemsViewService) {
        this.receiverSyetemsViewService = receiverSyetemsViewService;
    }

    /**
     * {@code POST  /receiver-syetems-views} : Create a new receiverSyetemsView.
     *
     * @param receiverSyetemsViewDTO the receiverSyetemsViewDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new receiverSyetemsViewDTO, or with status {@code 400 (Bad Request)} if the receiverSyetemsView has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/receiver-syetems-views")
    public ResponseEntity<ReceiverSyetemsViewDTO> createReceiverSyetemsView(@Valid @RequestBody ReceiverSyetemsViewDTO receiverSyetemsViewDTO) throws URISyntaxException {
        log.debug("REST request to save ReceiverSyetemsView : {}", receiverSyetemsViewDTO);
        if (receiverSyetemsViewDTO.getId() != null) {
            throw new BadRequestAlertException("A new receiverSyetemsView cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReceiverSyetemsViewDTO result = receiverSyetemsViewService.save(receiverSyetemsViewDTO);
        return ResponseEntity.created(new URI("/api/receiver-syetems-views/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /receiver-syetems-views} : Updates an existing receiverSyetemsView.
     *
     * @param receiverSyetemsViewDTO the receiverSyetemsViewDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated receiverSyetemsViewDTO,
     * or with status {@code 400 (Bad Request)} if the receiverSyetemsViewDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the receiverSyetemsViewDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/receiver-syetems-views")
    public ResponseEntity<ReceiverSyetemsViewDTO> updateReceiverSyetemsView(@Valid @RequestBody ReceiverSyetemsViewDTO receiverSyetemsViewDTO) throws URISyntaxException {
        log.debug("REST request to update ReceiverSyetemsView : {}", receiverSyetemsViewDTO);
        if (receiverSyetemsViewDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ReceiverSyetemsViewDTO result = receiverSyetemsViewService.save(receiverSyetemsViewDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, receiverSyetemsViewDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /receiver-syetems-views} : get all the receiverSyetemsViews.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of receiverSyetemsViews in body.
     */
    @GetMapping("/receiver-syetems-views")
    public List<ReceiverSyetemsViewDTO> getAllReceiverSyetemsViews() {
        log.debug("REST request to get all ReceiverSyetemsViews");
        return receiverSyetemsViewService.findAll();
    }

    /**
     * {@code GET  /receiver-syetems-views/:id} : get the "id" receiverSyetemsView.
     *
     * @param id the id of the receiverSyetemsViewDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the receiverSyetemsViewDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/receiver-syetems-views/{id}")
    public ResponseEntity<ReceiverSyetemsViewDTO> getReceiverSyetemsView(@PathVariable Long id) {
        log.debug("REST request to get ReceiverSyetemsView : {}", id);
        Optional<ReceiverSyetemsViewDTO> receiverSyetemsViewDTO = receiverSyetemsViewService.findOne(id);
        return ResponseUtil.wrapOrNotFound(receiverSyetemsViewDTO);
    }

    /**
     * {@code DELETE  /receiver-syetems-views/:id} : delete the "id" receiverSyetemsView.
     *
     * @param id the id of the receiverSyetemsViewDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/receiver-syetems-views/{id}")
    public ResponseEntity<Void> deleteReceiverSyetemsView(@PathVariable Long id) {
        log.debug("REST request to delete ReceiverSyetemsView : {}", id);

        receiverSyetemsViewService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
