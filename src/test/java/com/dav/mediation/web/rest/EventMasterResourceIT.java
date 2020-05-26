package com.dav.mediation.web.rest;

import com.dav.mediation.MediationApp;
import com.dav.mediation.domain.EventMaster;
import com.dav.mediation.repository.EventMasterRepository;
import com.dav.mediation.service.EventMasterService;
import com.dav.mediation.service.dto.EventMasterDTO;
import com.dav.mediation.service.mapper.EventMasterMapper;

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
 * Integration tests for the {@link EventMasterResource} REST controller.
 */
@SpringBootTest(classes = MediationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EventMasterResourceIT {

    private static final Integer DEFAULT_EVENT_ID = 1;
    private static final Integer UPDATED_EVENT_ID = 2;

    private static final String DEFAULT_EVENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EVENT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private EventMasterRepository eventMasterRepository;

    @Autowired
    private EventMasterMapper eventMasterMapper;

    @Autowired
    private EventMasterService eventMasterService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEventMasterMockMvc;

    private EventMaster eventMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EventMaster createEntity(EntityManager em) {
        EventMaster eventMaster = new EventMaster()
            .eventId(DEFAULT_EVENT_ID)
            .eventName(DEFAULT_EVENT_NAME)
            .description(DEFAULT_DESCRIPTION);
        return eventMaster;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EventMaster createUpdatedEntity(EntityManager em) {
        EventMaster eventMaster = new EventMaster()
            .eventId(UPDATED_EVENT_ID)
            .eventName(UPDATED_EVENT_NAME)
            .description(UPDATED_DESCRIPTION);
        return eventMaster;
    }

    @BeforeEach
    public void initTest() {
        eventMaster = createEntity(em);
    }

    @Test
    @Transactional
    public void createEventMaster() throws Exception {
        int databaseSizeBeforeCreate = eventMasterRepository.findAll().size();
        // Create the EventMaster
        EventMasterDTO eventMasterDTO = eventMasterMapper.toDto(eventMaster);
        restEventMasterMockMvc.perform(post("/api/event-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventMasterDTO)))
            .andExpect(status().isCreated());

        // Validate the EventMaster in the database
        List<EventMaster> eventMasterList = eventMasterRepository.findAll();
        assertThat(eventMasterList).hasSize(databaseSizeBeforeCreate + 1);
        EventMaster testEventMaster = eventMasterList.get(eventMasterList.size() - 1);
        assertThat(testEventMaster.getEventId()).isEqualTo(DEFAULT_EVENT_ID);
        assertThat(testEventMaster.getEventName()).isEqualTo(DEFAULT_EVENT_NAME);
        assertThat(testEventMaster.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createEventMasterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = eventMasterRepository.findAll().size();

        // Create the EventMaster with an existing ID
        eventMaster.setId(1L);
        EventMasterDTO eventMasterDTO = eventMasterMapper.toDto(eventMaster);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEventMasterMockMvc.perform(post("/api/event-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventMasterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EventMaster in the database
        List<EventMaster> eventMasterList = eventMasterRepository.findAll();
        assertThat(eventMasterList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkEventIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = eventMasterRepository.findAll().size();
        // set the field null
        eventMaster.setEventId(null);

        // Create the EventMaster, which fails.
        EventMasterDTO eventMasterDTO = eventMasterMapper.toDto(eventMaster);


        restEventMasterMockMvc.perform(post("/api/event-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventMasterDTO)))
            .andExpect(status().isBadRequest());

        List<EventMaster> eventMasterList = eventMasterRepository.findAll();
        assertThat(eventMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEventNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = eventMasterRepository.findAll().size();
        // set the field null
        eventMaster.setEventName(null);

        // Create the EventMaster, which fails.
        EventMasterDTO eventMasterDTO = eventMasterMapper.toDto(eventMaster);


        restEventMasterMockMvc.perform(post("/api/event-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventMasterDTO)))
            .andExpect(status().isBadRequest());

        List<EventMaster> eventMasterList = eventMasterRepository.findAll();
        assertThat(eventMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEventMasters() throws Exception {
        // Initialize the database
        eventMasterRepository.saveAndFlush(eventMaster);

        // Get all the eventMasterList
        restEventMasterMockMvc.perform(get("/api/event-masters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(eventMaster.getId().intValue())))
            .andExpect(jsonPath("$.[*].eventId").value(hasItem(DEFAULT_EVENT_ID)))
            .andExpect(jsonPath("$.[*].eventName").value(hasItem(DEFAULT_EVENT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getEventMaster() throws Exception {
        // Initialize the database
        eventMasterRepository.saveAndFlush(eventMaster);

        // Get the eventMaster
        restEventMasterMockMvc.perform(get("/api/event-masters/{id}", eventMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(eventMaster.getId().intValue()))
            .andExpect(jsonPath("$.eventId").value(DEFAULT_EVENT_ID))
            .andExpect(jsonPath("$.eventName").value(DEFAULT_EVENT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingEventMaster() throws Exception {
        // Get the eventMaster
        restEventMasterMockMvc.perform(get("/api/event-masters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEventMaster() throws Exception {
        // Initialize the database
        eventMasterRepository.saveAndFlush(eventMaster);

        int databaseSizeBeforeUpdate = eventMasterRepository.findAll().size();

        // Update the eventMaster
        EventMaster updatedEventMaster = eventMasterRepository.findById(eventMaster.getId()).get();
        // Disconnect from session so that the updates on updatedEventMaster are not directly saved in db
        em.detach(updatedEventMaster);
        updatedEventMaster
            .eventId(UPDATED_EVENT_ID)
            .eventName(UPDATED_EVENT_NAME)
            .description(UPDATED_DESCRIPTION);
        EventMasterDTO eventMasterDTO = eventMasterMapper.toDto(updatedEventMaster);

        restEventMasterMockMvc.perform(put("/api/event-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventMasterDTO)))
            .andExpect(status().isOk());

        // Validate the EventMaster in the database
        List<EventMaster> eventMasterList = eventMasterRepository.findAll();
        assertThat(eventMasterList).hasSize(databaseSizeBeforeUpdate);
        EventMaster testEventMaster = eventMasterList.get(eventMasterList.size() - 1);
        assertThat(testEventMaster.getEventId()).isEqualTo(UPDATED_EVENT_ID);
        assertThat(testEventMaster.getEventName()).isEqualTo(UPDATED_EVENT_NAME);
        assertThat(testEventMaster.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingEventMaster() throws Exception {
        int databaseSizeBeforeUpdate = eventMasterRepository.findAll().size();

        // Create the EventMaster
        EventMasterDTO eventMasterDTO = eventMasterMapper.toDto(eventMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEventMasterMockMvc.perform(put("/api/event-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventMasterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EventMaster in the database
        List<EventMaster> eventMasterList = eventMasterRepository.findAll();
        assertThat(eventMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEventMaster() throws Exception {
        // Initialize the database
        eventMasterRepository.saveAndFlush(eventMaster);

        int databaseSizeBeforeDelete = eventMasterRepository.findAll().size();

        // Delete the eventMaster
        restEventMasterMockMvc.perform(delete("/api/event-masters/{id}", eventMaster.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EventMaster> eventMasterList = eventMasterRepository.findAll();
        assertThat(eventMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
