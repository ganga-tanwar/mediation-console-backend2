package com.dav.mediation.web.rest;

import com.dav.mediation.MediationApp;
import com.dav.mediation.domain.EventStatusMaster;
import com.dav.mediation.repository.EventStatusMasterRepository;
import com.dav.mediation.service.EventStatusMasterService;
import com.dav.mediation.service.dto.EventStatusMasterDTO;
import com.dav.mediation.service.mapper.EventStatusMasterMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link EventStatusMasterResource} REST controller.
 */
@SpringBootTest(classes = MediationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EventStatusMasterResourceIT {

    private static final Integer DEFAULT_STATUS_ID = 1;
    private static final Integer UPDATED_STATUS_ID = 2;

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    @Autowired
    private EventStatusMasterRepository eventStatusMasterRepository;

    @Autowired
    private EventStatusMasterMapper eventStatusMasterMapper;

    @Autowired
    private EventStatusMasterService eventStatusMasterService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEventStatusMasterMockMvc;

    private EventStatusMaster eventStatusMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EventStatusMaster createEntity(EntityManager em) {
        EventStatusMaster eventStatusMaster = new EventStatusMaster()
            .statusId(DEFAULT_STATUS_ID)
            .status(DEFAULT_STATUS);
        return eventStatusMaster;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EventStatusMaster createUpdatedEntity(EntityManager em) {
        EventStatusMaster eventStatusMaster = new EventStatusMaster()
            .statusId(UPDATED_STATUS_ID)
            .status(UPDATED_STATUS);
        return eventStatusMaster;
    }

    @BeforeEach
    public void initTest() {
        eventStatusMaster = createEntity(em);
    }

    @Test
    @Transactional
    public void createEventStatusMaster() throws Exception {
        int databaseSizeBeforeCreate = eventStatusMasterRepository.findAll().size();
        // Create the EventStatusMaster
        EventStatusMasterDTO eventStatusMasterDTO = eventStatusMasterMapper.toDto(eventStatusMaster);
        restEventStatusMasterMockMvc.perform(post("/api/event-status-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventStatusMasterDTO)))
            .andExpect(status().isCreated());

        // Validate the EventStatusMaster in the database
        List<EventStatusMaster> eventStatusMasterList = eventStatusMasterRepository.findAll();
        assertThat(eventStatusMasterList).hasSize(databaseSizeBeforeCreate + 1);
        EventStatusMaster testEventStatusMaster = eventStatusMasterList.get(eventStatusMasterList.size() - 1);
        assertThat(testEventStatusMaster.getStatusId()).isEqualTo(DEFAULT_STATUS_ID);
        assertThat(testEventStatusMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createEventStatusMasterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = eventStatusMasterRepository.findAll().size();

        // Create the EventStatusMaster with an existing ID
        eventStatusMaster.setId(1L);
        EventStatusMasterDTO eventStatusMasterDTO = eventStatusMasterMapper.toDto(eventStatusMaster);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEventStatusMasterMockMvc.perform(post("/api/event-status-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventStatusMasterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EventStatusMaster in the database
        List<EventStatusMaster> eventStatusMasterList = eventStatusMasterRepository.findAll();
        assertThat(eventStatusMasterList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkStatusIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = eventStatusMasterRepository.findAll().size();
        // set the field null
        eventStatusMaster.setStatusId(null);

        // Create the EventStatusMaster, which fails.
        EventStatusMasterDTO eventStatusMasterDTO = eventStatusMasterMapper.toDto(eventStatusMaster);


        restEventStatusMasterMockMvc.perform(post("/api/event-status-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventStatusMasterDTO)))
            .andExpect(status().isBadRequest());

        List<EventStatusMaster> eventStatusMasterList = eventStatusMasterRepository.findAll();
        assertThat(eventStatusMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = eventStatusMasterRepository.findAll().size();
        // set the field null
        eventStatusMaster.setStatus(null);

        // Create the EventStatusMaster, which fails.
        EventStatusMasterDTO eventStatusMasterDTO = eventStatusMasterMapper.toDto(eventStatusMaster);


        restEventStatusMasterMockMvc.perform(post("/api/event-status-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventStatusMasterDTO)))
            .andExpect(status().isBadRequest());

        List<EventStatusMaster> eventStatusMasterList = eventStatusMasterRepository.findAll();
        assertThat(eventStatusMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEventStatusMasters() throws Exception {
        // Initialize the database
        eventStatusMasterRepository.saveAndFlush(eventStatusMaster);

        // Get all the eventStatusMasterList
        restEventStatusMasterMockMvc.perform(get("/api/event-status-masters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(eventStatusMaster.getId().intValue())))
            .andExpect(jsonPath("$.[*].statusId").value(hasItem(DEFAULT_STATUS_ID)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    
    @Test
    @Transactional
    public void getEventStatusMaster() throws Exception {
        // Initialize the database
        eventStatusMasterRepository.saveAndFlush(eventStatusMaster);

        // Get the eventStatusMaster
        restEventStatusMasterMockMvc.perform(get("/api/event-status-masters/{id}", eventStatusMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(eventStatusMaster.getId().intValue()))
            .andExpect(jsonPath("$.statusId").value(DEFAULT_STATUS_ID))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }
    @Test
    @Transactional
    public void getNonExistingEventStatusMaster() throws Exception {
        // Get the eventStatusMaster
        restEventStatusMasterMockMvc.perform(get("/api/event-status-masters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEventStatusMaster() throws Exception {
        // Initialize the database
        eventStatusMasterRepository.saveAndFlush(eventStatusMaster);

        int databaseSizeBeforeUpdate = eventStatusMasterRepository.findAll().size();

        // Update the eventStatusMaster
        EventStatusMaster updatedEventStatusMaster = eventStatusMasterRepository.findById(eventStatusMaster.getId()).get();
        // Disconnect from session so that the updates on updatedEventStatusMaster are not directly saved in db
        em.detach(updatedEventStatusMaster);
        updatedEventStatusMaster
            .statusId(UPDATED_STATUS_ID)
            .status(UPDATED_STATUS);
        EventStatusMasterDTO eventStatusMasterDTO = eventStatusMasterMapper.toDto(updatedEventStatusMaster);

        restEventStatusMasterMockMvc.perform(put("/api/event-status-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventStatusMasterDTO)))
            .andExpect(status().isOk());

        // Validate the EventStatusMaster in the database
        List<EventStatusMaster> eventStatusMasterList = eventStatusMasterRepository.findAll();
        assertThat(eventStatusMasterList).hasSize(databaseSizeBeforeUpdate);
        EventStatusMaster testEventStatusMaster = eventStatusMasterList.get(eventStatusMasterList.size() - 1);
        assertThat(testEventStatusMaster.getStatusId()).isEqualTo(UPDATED_STATUS_ID);
        assertThat(testEventStatusMaster.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingEventStatusMaster() throws Exception {
        int databaseSizeBeforeUpdate = eventStatusMasterRepository.findAll().size();

        // Create the EventStatusMaster
        EventStatusMasterDTO eventStatusMasterDTO = eventStatusMasterMapper.toDto(eventStatusMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEventStatusMasterMockMvc.perform(put("/api/event-status-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventStatusMasterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EventStatusMaster in the database
        List<EventStatusMaster> eventStatusMasterList = eventStatusMasterRepository.findAll();
        assertThat(eventStatusMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEventStatusMaster() throws Exception {
        // Initialize the database
        eventStatusMasterRepository.saveAndFlush(eventStatusMaster);

        int databaseSizeBeforeDelete = eventStatusMasterRepository.findAll().size();

        // Delete the eventStatusMaster
        restEventStatusMasterMockMvc.perform(delete("/api/event-status-masters/{id}", eventStatusMaster.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EventStatusMaster> eventStatusMasterList = eventStatusMasterRepository.findAll();
        assertThat(eventStatusMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
