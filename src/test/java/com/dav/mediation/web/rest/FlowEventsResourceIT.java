package com.dav.mediation.web.rest;

import com.dav.mediation.MediationApp;
import com.dav.mediation.domain.FlowEvents;
import com.dav.mediation.domain.FlowStatus;
import com.dav.mediation.domain.EventMaster;
import com.dav.mediation.domain.EventStatusMaster;
import com.dav.mediation.repository.FlowEventsRepository;
import com.dav.mediation.service.FlowEventsService;
import com.dav.mediation.service.dto.FlowEventsDTO;
import com.dav.mediation.service.mapper.FlowEventsMapper;

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
 * Integration tests for the {@link FlowEventsResource} REST controller.
 */
@SpringBootTest(classes = MediationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FlowEventsResourceIT {

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    private static final String DEFAULT_ERROR_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ERROR_CODE = "BBBBBBBBBB";

    @Autowired
    private FlowEventsRepository flowEventsRepository;

    @Autowired
    private FlowEventsMapper flowEventsMapper;

    @Autowired
    private FlowEventsService flowEventsService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFlowEventsMockMvc;

    private FlowEvents flowEvents;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FlowEvents createEntity(EntityManager em) {
        FlowEvents flowEvents = new FlowEvents()
            .remarks(DEFAULT_REMARKS)
            .errorCode(DEFAULT_ERROR_CODE);
        // Add required entity
        FlowStatus flowStatus;
        if (TestUtil.findAll(em, FlowStatus.class).isEmpty()) {
            flowStatus = FlowStatusResourceIT.createEntity(em);
            em.persist(flowStatus);
            em.flush();
        } else {
            flowStatus = TestUtil.findAll(em, FlowStatus.class).get(0);
        }
        flowEvents.setFlowTransactions(flowStatus);
        // Add required entity
        EventMaster eventMaster;
        if (TestUtil.findAll(em, EventMaster.class).isEmpty()) {
            eventMaster = EventMasterResourceIT.createEntity(em);
            em.persist(eventMaster);
            em.flush();
        } else {
            eventMaster = TestUtil.findAll(em, EventMaster.class).get(0);
        }
        flowEvents.setEvent(eventMaster);
        // Add required entity
        EventStatusMaster eventStatusMaster;
        if (TestUtil.findAll(em, EventStatusMaster.class).isEmpty()) {
            eventStatusMaster = EventStatusMasterResourceIT.createEntity(em);
            em.persist(eventStatusMaster);
            em.flush();
        } else {
            eventStatusMaster = TestUtil.findAll(em, EventStatusMaster.class).get(0);
        }
        flowEvents.setStatus(eventStatusMaster);
        return flowEvents;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FlowEvents createUpdatedEntity(EntityManager em) {
        FlowEvents flowEvents = new FlowEvents()
            .remarks(UPDATED_REMARKS)
            .errorCode(UPDATED_ERROR_CODE);
        // Add required entity
        FlowStatus flowStatus;
        if (TestUtil.findAll(em, FlowStatus.class).isEmpty()) {
            flowStatus = FlowStatusResourceIT.createUpdatedEntity(em);
            em.persist(flowStatus);
            em.flush();
        } else {
            flowStatus = TestUtil.findAll(em, FlowStatus.class).get(0);
        }
        flowEvents.setFlowTransactions(flowStatus);
        // Add required entity
        EventMaster eventMaster;
        if (TestUtil.findAll(em, EventMaster.class).isEmpty()) {
            eventMaster = EventMasterResourceIT.createUpdatedEntity(em);
            em.persist(eventMaster);
            em.flush();
        } else {
            eventMaster = TestUtil.findAll(em, EventMaster.class).get(0);
        }
        flowEvents.setEvent(eventMaster);
        // Add required entity
        EventStatusMaster eventStatusMaster;
        if (TestUtil.findAll(em, EventStatusMaster.class).isEmpty()) {
            eventStatusMaster = EventStatusMasterResourceIT.createUpdatedEntity(em);
            em.persist(eventStatusMaster);
            em.flush();
        } else {
            eventStatusMaster = TestUtil.findAll(em, EventStatusMaster.class).get(0);
        }
        flowEvents.setStatus(eventStatusMaster);
        return flowEvents;
    }

    @BeforeEach
    public void initTest() {
        flowEvents = createEntity(em);
    }

    @Test
    @Transactional
    public void createFlowEvents() throws Exception {
        int databaseSizeBeforeCreate = flowEventsRepository.findAll().size();
        // Create the FlowEvents
        FlowEventsDTO flowEventsDTO = flowEventsMapper.toDto(flowEvents);
        restFlowEventsMockMvc.perform(post("/api/flow-events")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowEventsDTO)))
            .andExpect(status().isCreated());

        // Validate the FlowEvents in the database
        List<FlowEvents> flowEventsList = flowEventsRepository.findAll();
        assertThat(flowEventsList).hasSize(databaseSizeBeforeCreate + 1);
        FlowEvents testFlowEvents = flowEventsList.get(flowEventsList.size() - 1);
        assertThat(testFlowEvents.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testFlowEvents.getErrorCode()).isEqualTo(DEFAULT_ERROR_CODE);
    }

    @Test
    @Transactional
    public void createFlowEventsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = flowEventsRepository.findAll().size();

        // Create the FlowEvents with an existing ID
        flowEvents.setId(1L);
        FlowEventsDTO flowEventsDTO = flowEventsMapper.toDto(flowEvents);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFlowEventsMockMvc.perform(post("/api/flow-events")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowEventsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FlowEvents in the database
        List<FlowEvents> flowEventsList = flowEventsRepository.findAll();
        assertThat(flowEventsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkErrorCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = flowEventsRepository.findAll().size();
        // set the field null
        flowEvents.setErrorCode(null);

        // Create the FlowEvents, which fails.
        FlowEventsDTO flowEventsDTO = flowEventsMapper.toDto(flowEvents);


        restFlowEventsMockMvc.perform(post("/api/flow-events")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowEventsDTO)))
            .andExpect(status().isBadRequest());

        List<FlowEvents> flowEventsList = flowEventsRepository.findAll();
        assertThat(flowEventsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFlowEvents() throws Exception {
        // Initialize the database
        flowEventsRepository.saveAndFlush(flowEvents);

        // Get all the flowEventsList
        restFlowEventsMockMvc.perform(get("/api/flow-events?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(flowEvents.getId().intValue())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)))
            .andExpect(jsonPath("$.[*].errorCode").value(hasItem(DEFAULT_ERROR_CODE)));
    }
    
    @Test
    @Transactional
    public void getFlowEvents() throws Exception {
        // Initialize the database
        flowEventsRepository.saveAndFlush(flowEvents);

        // Get the flowEvents
        restFlowEventsMockMvc.perform(get("/api/flow-events/{id}", flowEvents.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(flowEvents.getId().intValue()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS))
            .andExpect(jsonPath("$.errorCode").value(DEFAULT_ERROR_CODE));
    }
    @Test
    @Transactional
    public void getNonExistingFlowEvents() throws Exception {
        // Get the flowEvents
        restFlowEventsMockMvc.perform(get("/api/flow-events/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFlowEvents() throws Exception {
        // Initialize the database
        flowEventsRepository.saveAndFlush(flowEvents);

        int databaseSizeBeforeUpdate = flowEventsRepository.findAll().size();

        // Update the flowEvents
        FlowEvents updatedFlowEvents = flowEventsRepository.findById(flowEvents.getId()).get();
        // Disconnect from session so that the updates on updatedFlowEvents are not directly saved in db
        em.detach(updatedFlowEvents);
        updatedFlowEvents
            .remarks(UPDATED_REMARKS)
            .errorCode(UPDATED_ERROR_CODE);
        FlowEventsDTO flowEventsDTO = flowEventsMapper.toDto(updatedFlowEvents);

        restFlowEventsMockMvc.perform(put("/api/flow-events")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowEventsDTO)))
            .andExpect(status().isOk());

        // Validate the FlowEvents in the database
        List<FlowEvents> flowEventsList = flowEventsRepository.findAll();
        assertThat(flowEventsList).hasSize(databaseSizeBeforeUpdate);
        FlowEvents testFlowEvents = flowEventsList.get(flowEventsList.size() - 1);
        assertThat(testFlowEvents.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testFlowEvents.getErrorCode()).isEqualTo(UPDATED_ERROR_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingFlowEvents() throws Exception {
        int databaseSizeBeforeUpdate = flowEventsRepository.findAll().size();

        // Create the FlowEvents
        FlowEventsDTO flowEventsDTO = flowEventsMapper.toDto(flowEvents);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFlowEventsMockMvc.perform(put("/api/flow-events")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowEventsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FlowEvents in the database
        List<FlowEvents> flowEventsList = flowEventsRepository.findAll();
        assertThat(flowEventsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFlowEvents() throws Exception {
        // Initialize the database
        flowEventsRepository.saveAndFlush(flowEvents);

        int databaseSizeBeforeDelete = flowEventsRepository.findAll().size();

        // Delete the flowEvents
        restFlowEventsMockMvc.perform(delete("/api/flow-events/{id}", flowEvents.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FlowEvents> flowEventsList = flowEventsRepository.findAll();
        assertThat(flowEventsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
