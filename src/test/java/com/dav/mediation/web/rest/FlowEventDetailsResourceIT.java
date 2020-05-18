package com.dav.mediation.web.rest;

import com.dav.mediation.MediationApp;
import com.dav.mediation.domain.FlowEventDetails;
import com.dav.mediation.domain.FlowDetails;
import com.dav.mediation.repository.FlowEventDetailsRepository;
import com.dav.mediation.service.FlowEventDetailsService;
import com.dav.mediation.service.dto.FlowEventDetailsDTO;
import com.dav.mediation.service.mapper.FlowEventDetailsMapper;

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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link FlowEventDetailsResource} REST controller.
 */
@SpringBootTest(classes = MediationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FlowEventDetailsResourceIT {

    private static final UUID DEFAULT_EVENT_ID = UUID.randomUUID();
    private static final UUID UPDATED_EVENT_ID = UUID.randomUUID();

    private static final String DEFAULT_EVENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EVENT_NAME = "BBBBBBBBBB";

    private static final Instant DEFAULT_TRANSACTION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TRANSACTION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_PARAMETERS = "AAAAAAAAAA";
    private static final String UPDATED_PARAMETERS = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_ERROR_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ERROR_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_ERROR_INFO = "AAAAAAAAAA";
    private static final String UPDATED_ERROR_INFO = "BBBBBBBBBB";

    private static final String DEFAULT_ERROR_CODE_RETRIABLE = "AAAAAAAAAA";
    private static final String UPDATED_ERROR_CODE_RETRIABLE = "BBBBBBBBBB";

    @Autowired
    private FlowEventDetailsRepository flowEventDetailsRepository;

    @Autowired
    private FlowEventDetailsMapper flowEventDetailsMapper;

    @Autowired
    private FlowEventDetailsService flowEventDetailsService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFlowEventDetailsMockMvc;

    private FlowEventDetails flowEventDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FlowEventDetails createEntity(EntityManager em) {
        FlowEventDetails flowEventDetails = new FlowEventDetails()
            .eventId(DEFAULT_EVENT_ID)
            .eventName(DEFAULT_EVENT_NAME)
            .transactionDate(DEFAULT_TRANSACTION_DATE)
            .parameters(DEFAULT_PARAMETERS)
            .status(DEFAULT_STATUS)
            .errorCode(DEFAULT_ERROR_CODE)
            .errorInfo(DEFAULT_ERROR_INFO)
            .errorCodeRetriable(DEFAULT_ERROR_CODE_RETRIABLE);
        // Add required entity
        FlowDetails flowDetails;
        if (TestUtil.findAll(em, FlowDetails.class).isEmpty()) {
            flowDetails = FlowDetailsResourceIT.createEntity(em);
            em.persist(flowDetails);
            em.flush();
        } else {
            flowDetails = TestUtil.findAll(em, FlowDetails.class).get(0);
        }
        flowEventDetails.setFlowId(flowDetails);
        return flowEventDetails;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FlowEventDetails createUpdatedEntity(EntityManager em) {
        FlowEventDetails flowEventDetails = new FlowEventDetails()
            .eventId(UPDATED_EVENT_ID)
            .eventName(UPDATED_EVENT_NAME)
            .transactionDate(UPDATED_TRANSACTION_DATE)
            .parameters(UPDATED_PARAMETERS)
            .status(UPDATED_STATUS)
            .errorCode(UPDATED_ERROR_CODE)
            .errorInfo(UPDATED_ERROR_INFO)
            .errorCodeRetriable(UPDATED_ERROR_CODE_RETRIABLE);
        // Add required entity
        FlowDetails flowDetails;
        if (TestUtil.findAll(em, FlowDetails.class).isEmpty()) {
            flowDetails = FlowDetailsResourceIT.createUpdatedEntity(em);
            em.persist(flowDetails);
            em.flush();
        } else {
            flowDetails = TestUtil.findAll(em, FlowDetails.class).get(0);
        }
        flowEventDetails.setFlowId(flowDetails);
        return flowEventDetails;
    }

    @BeforeEach
    public void initTest() {
        flowEventDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createFlowEventDetails() throws Exception {
        int databaseSizeBeforeCreate = flowEventDetailsRepository.findAll().size();
        // Create the FlowEventDetails
        FlowEventDetailsDTO flowEventDetailsDTO = flowEventDetailsMapper.toDto(flowEventDetails);
        restFlowEventDetailsMockMvc.perform(post("/api/flow-event-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowEventDetailsDTO)))
            .andExpect(status().isCreated());

        // Validate the FlowEventDetails in the database
        List<FlowEventDetails> flowEventDetailsList = flowEventDetailsRepository.findAll();
        assertThat(flowEventDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        FlowEventDetails testFlowEventDetails = flowEventDetailsList.get(flowEventDetailsList.size() - 1);
        assertThat(testFlowEventDetails.getEventId()).isEqualTo(DEFAULT_EVENT_ID);
        assertThat(testFlowEventDetails.getEventName()).isEqualTo(DEFAULT_EVENT_NAME);
        assertThat(testFlowEventDetails.getTransactionDate()).isEqualTo(DEFAULT_TRANSACTION_DATE);
        assertThat(testFlowEventDetails.getParameters()).isEqualTo(DEFAULT_PARAMETERS);
        assertThat(testFlowEventDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testFlowEventDetails.getErrorCode()).isEqualTo(DEFAULT_ERROR_CODE);
        assertThat(testFlowEventDetails.getErrorInfo()).isEqualTo(DEFAULT_ERROR_INFO);
        assertThat(testFlowEventDetails.getErrorCodeRetriable()).isEqualTo(DEFAULT_ERROR_CODE_RETRIABLE);
    }

    @Test
    @Transactional
    public void createFlowEventDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = flowEventDetailsRepository.findAll().size();

        // Create the FlowEventDetails with an existing ID
        flowEventDetails.setId(1L);
        FlowEventDetailsDTO flowEventDetailsDTO = flowEventDetailsMapper.toDto(flowEventDetails);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFlowEventDetailsMockMvc.perform(post("/api/flow-event-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowEventDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FlowEventDetails in the database
        List<FlowEventDetails> flowEventDetailsList = flowEventDetailsRepository.findAll();
        assertThat(flowEventDetailsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkEventIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = flowEventDetailsRepository.findAll().size();
        // set the field null
        flowEventDetails.setEventId(null);

        // Create the FlowEventDetails, which fails.
        FlowEventDetailsDTO flowEventDetailsDTO = flowEventDetailsMapper.toDto(flowEventDetails);


        restFlowEventDetailsMockMvc.perform(post("/api/flow-event-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowEventDetailsDTO)))
            .andExpect(status().isBadRequest());

        List<FlowEventDetails> flowEventDetailsList = flowEventDetailsRepository.findAll();
        assertThat(flowEventDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEventNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = flowEventDetailsRepository.findAll().size();
        // set the field null
        flowEventDetails.setEventName(null);

        // Create the FlowEventDetails, which fails.
        FlowEventDetailsDTO flowEventDetailsDTO = flowEventDetailsMapper.toDto(flowEventDetails);


        restFlowEventDetailsMockMvc.perform(post("/api/flow-event-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowEventDetailsDTO)))
            .andExpect(status().isBadRequest());

        List<FlowEventDetails> flowEventDetailsList = flowEventDetailsRepository.findAll();
        assertThat(flowEventDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTransactionDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = flowEventDetailsRepository.findAll().size();
        // set the field null
        flowEventDetails.setTransactionDate(null);

        // Create the FlowEventDetails, which fails.
        FlowEventDetailsDTO flowEventDetailsDTO = flowEventDetailsMapper.toDto(flowEventDetails);


        restFlowEventDetailsMockMvc.perform(post("/api/flow-event-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowEventDetailsDTO)))
            .andExpect(status().isBadRequest());

        List<FlowEventDetails> flowEventDetailsList = flowEventDetailsRepository.findAll();
        assertThat(flowEventDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkParametersIsRequired() throws Exception {
        int databaseSizeBeforeTest = flowEventDetailsRepository.findAll().size();
        // set the field null
        flowEventDetails.setParameters(null);

        // Create the FlowEventDetails, which fails.
        FlowEventDetailsDTO flowEventDetailsDTO = flowEventDetailsMapper.toDto(flowEventDetails);


        restFlowEventDetailsMockMvc.perform(post("/api/flow-event-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowEventDetailsDTO)))
            .andExpect(status().isBadRequest());

        List<FlowEventDetails> flowEventDetailsList = flowEventDetailsRepository.findAll();
        assertThat(flowEventDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = flowEventDetailsRepository.findAll().size();
        // set the field null
        flowEventDetails.setStatus(null);

        // Create the FlowEventDetails, which fails.
        FlowEventDetailsDTO flowEventDetailsDTO = flowEventDetailsMapper.toDto(flowEventDetails);


        restFlowEventDetailsMockMvc.perform(post("/api/flow-event-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowEventDetailsDTO)))
            .andExpect(status().isBadRequest());

        List<FlowEventDetails> flowEventDetailsList = flowEventDetailsRepository.findAll();
        assertThat(flowEventDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkErrorCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = flowEventDetailsRepository.findAll().size();
        // set the field null
        flowEventDetails.setErrorCode(null);

        // Create the FlowEventDetails, which fails.
        FlowEventDetailsDTO flowEventDetailsDTO = flowEventDetailsMapper.toDto(flowEventDetails);


        restFlowEventDetailsMockMvc.perform(post("/api/flow-event-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowEventDetailsDTO)))
            .andExpect(status().isBadRequest());

        List<FlowEventDetails> flowEventDetailsList = flowEventDetailsRepository.findAll();
        assertThat(flowEventDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkErrorInfoIsRequired() throws Exception {
        int databaseSizeBeforeTest = flowEventDetailsRepository.findAll().size();
        // set the field null
        flowEventDetails.setErrorInfo(null);

        // Create the FlowEventDetails, which fails.
        FlowEventDetailsDTO flowEventDetailsDTO = flowEventDetailsMapper.toDto(flowEventDetails);


        restFlowEventDetailsMockMvc.perform(post("/api/flow-event-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowEventDetailsDTO)))
            .andExpect(status().isBadRequest());

        List<FlowEventDetails> flowEventDetailsList = flowEventDetailsRepository.findAll();
        assertThat(flowEventDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkErrorCodeRetriableIsRequired() throws Exception {
        int databaseSizeBeforeTest = flowEventDetailsRepository.findAll().size();
        // set the field null
        flowEventDetails.setErrorCodeRetriable(null);

        // Create the FlowEventDetails, which fails.
        FlowEventDetailsDTO flowEventDetailsDTO = flowEventDetailsMapper.toDto(flowEventDetails);


        restFlowEventDetailsMockMvc.perform(post("/api/flow-event-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowEventDetailsDTO)))
            .andExpect(status().isBadRequest());

        List<FlowEventDetails> flowEventDetailsList = flowEventDetailsRepository.findAll();
        assertThat(flowEventDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFlowEventDetails() throws Exception {
        // Initialize the database
        flowEventDetailsRepository.saveAndFlush(flowEventDetails);

        // Get all the flowEventDetailsList
        restFlowEventDetailsMockMvc.perform(get("/api/flow-event-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(flowEventDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].eventId").value(hasItem(DEFAULT_EVENT_ID.toString())))
            .andExpect(jsonPath("$.[*].eventName").value(hasItem(DEFAULT_EVENT_NAME)))
            .andExpect(jsonPath("$.[*].transactionDate").value(hasItem(DEFAULT_TRANSACTION_DATE.toString())))
            .andExpect(jsonPath("$.[*].parameters").value(hasItem(DEFAULT_PARAMETERS)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].errorCode").value(hasItem(DEFAULT_ERROR_CODE)))
            .andExpect(jsonPath("$.[*].errorInfo").value(hasItem(DEFAULT_ERROR_INFO)))
            .andExpect(jsonPath("$.[*].errorCodeRetriable").value(hasItem(DEFAULT_ERROR_CODE_RETRIABLE)));
    }
    
    @Test
    @Transactional
    public void getFlowEventDetails() throws Exception {
        // Initialize the database
        flowEventDetailsRepository.saveAndFlush(flowEventDetails);

        // Get the flowEventDetails
        restFlowEventDetailsMockMvc.perform(get("/api/flow-event-details/{id}", flowEventDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(flowEventDetails.getId().intValue()))
            .andExpect(jsonPath("$.eventId").value(DEFAULT_EVENT_ID.toString()))
            .andExpect(jsonPath("$.eventName").value(DEFAULT_EVENT_NAME))
            .andExpect(jsonPath("$.transactionDate").value(DEFAULT_TRANSACTION_DATE.toString()))
            .andExpect(jsonPath("$.parameters").value(DEFAULT_PARAMETERS))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.errorCode").value(DEFAULT_ERROR_CODE))
            .andExpect(jsonPath("$.errorInfo").value(DEFAULT_ERROR_INFO))
            .andExpect(jsonPath("$.errorCodeRetriable").value(DEFAULT_ERROR_CODE_RETRIABLE));
    }
    @Test
    @Transactional
    public void getNonExistingFlowEventDetails() throws Exception {
        // Get the flowEventDetails
        restFlowEventDetailsMockMvc.perform(get("/api/flow-event-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFlowEventDetails() throws Exception {
        // Initialize the database
        flowEventDetailsRepository.saveAndFlush(flowEventDetails);

        int databaseSizeBeforeUpdate = flowEventDetailsRepository.findAll().size();

        // Update the flowEventDetails
        FlowEventDetails updatedFlowEventDetails = flowEventDetailsRepository.findById(flowEventDetails.getId()).get();
        // Disconnect from session so that the updates on updatedFlowEventDetails are not directly saved in db
        em.detach(updatedFlowEventDetails);
        updatedFlowEventDetails
            .eventId(UPDATED_EVENT_ID)
            .eventName(UPDATED_EVENT_NAME)
            .transactionDate(UPDATED_TRANSACTION_DATE)
            .parameters(UPDATED_PARAMETERS)
            .status(UPDATED_STATUS)
            .errorCode(UPDATED_ERROR_CODE)
            .errorInfo(UPDATED_ERROR_INFO)
            .errorCodeRetriable(UPDATED_ERROR_CODE_RETRIABLE);
        FlowEventDetailsDTO flowEventDetailsDTO = flowEventDetailsMapper.toDto(updatedFlowEventDetails);

        restFlowEventDetailsMockMvc.perform(put("/api/flow-event-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowEventDetailsDTO)))
            .andExpect(status().isOk());

        // Validate the FlowEventDetails in the database
        List<FlowEventDetails> flowEventDetailsList = flowEventDetailsRepository.findAll();
        assertThat(flowEventDetailsList).hasSize(databaseSizeBeforeUpdate);
        FlowEventDetails testFlowEventDetails = flowEventDetailsList.get(flowEventDetailsList.size() - 1);
        assertThat(testFlowEventDetails.getEventId()).isEqualTo(UPDATED_EVENT_ID);
        assertThat(testFlowEventDetails.getEventName()).isEqualTo(UPDATED_EVENT_NAME);
        assertThat(testFlowEventDetails.getTransactionDate()).isEqualTo(UPDATED_TRANSACTION_DATE);
        assertThat(testFlowEventDetails.getParameters()).isEqualTo(UPDATED_PARAMETERS);
        assertThat(testFlowEventDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testFlowEventDetails.getErrorCode()).isEqualTo(UPDATED_ERROR_CODE);
        assertThat(testFlowEventDetails.getErrorInfo()).isEqualTo(UPDATED_ERROR_INFO);
        assertThat(testFlowEventDetails.getErrorCodeRetriable()).isEqualTo(UPDATED_ERROR_CODE_RETRIABLE);
    }

    @Test
    @Transactional
    public void updateNonExistingFlowEventDetails() throws Exception {
        int databaseSizeBeforeUpdate = flowEventDetailsRepository.findAll().size();

        // Create the FlowEventDetails
        FlowEventDetailsDTO flowEventDetailsDTO = flowEventDetailsMapper.toDto(flowEventDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFlowEventDetailsMockMvc.perform(put("/api/flow-event-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowEventDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FlowEventDetails in the database
        List<FlowEventDetails> flowEventDetailsList = flowEventDetailsRepository.findAll();
        assertThat(flowEventDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFlowEventDetails() throws Exception {
        // Initialize the database
        flowEventDetailsRepository.saveAndFlush(flowEventDetails);

        int databaseSizeBeforeDelete = flowEventDetailsRepository.findAll().size();

        // Delete the flowEventDetails
        restFlowEventDetailsMockMvc.perform(delete("/api/flow-event-details/{id}", flowEventDetails.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FlowEventDetails> flowEventDetailsList = flowEventDetailsRepository.findAll();
        assertThat(flowEventDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
