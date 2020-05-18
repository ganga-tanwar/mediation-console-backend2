package com.dav.mediation.web.rest;

import com.dav.mediation.MediationApp;
import com.dav.mediation.domain.FlowDetails;
import com.dav.mediation.repository.FlowDetailsRepository;
import com.dav.mediation.service.FlowDetailsService;
import com.dav.mediation.service.dto.FlowDetailsDTO;
import com.dav.mediation.service.mapper.FlowDetailsMapper;
import com.dav.mediation.service.dto.FlowDetailsCriteria;
import com.dav.mediation.service.FlowDetailsQueryService;

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
 * Integration tests for the {@link FlowDetailsResource} REST controller.
 */
@SpringBootTest(classes = MediationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FlowDetailsResourceIT {

    private static final UUID DEFAULT_FLOW_ID = UUID.randomUUID();
    private static final UUID UPDATED_FLOW_ID = UUID.randomUUID();

    private static final String DEFAULT_FLOW_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FLOW_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MEDIATION_SYSTEM = "AAAAAAAAAA";
    private static final String UPDATED_MEDIATION_SYSTEM = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE = "BBBBBBBBBB";

    private static final String DEFAULT_DESTINATION = "AAAAAAAAAA";
    private static final String UPDATED_DESTINATION = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FILE_NAME = "BBBBBBBBBB";

    private static final Instant DEFAULT_TRANSACTION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TRANSACTION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final UUID DEFAULT_TRANSACTION_ID = UUID.randomUUID();
    private static final UUID UPDATED_TRANSACTION_ID = UUID.randomUUID();

    private static final String DEFAULT_FORMAT = "AAAAAAAAAA";
    private static final String UPDATED_FORMAT = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE_ENDPOINT_INTERFACE = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_ENDPOINT_INTERFACE = "BBBBBBBBBB";

    private static final String DEFAULT_DESTINATION_ENDPOINT_INTERFACE = "AAAAAAAAAA";
    private static final String UPDATED_DESTINATION_ENDPOINT_INTERFACE = "BBBBBBBBBB";

    private static final String DEFAULT_ACKNOWLEDGMENT_EXPECTED = "AAAAAAAAAA";
    private static final String UPDATED_ACKNOWLEDGMENT_EXPECTED = "BBBBBBBBBB";

    private static final String DEFAULT_ACKNOWLEDGMENT_RECEIVED = "AAAAAAAAAA";
    private static final String UPDATED_ACKNOWLEDGMENT_RECEIVED = "BBBBBBBBBB";

    @Autowired
    private FlowDetailsRepository flowDetailsRepository;

    @Autowired
    private FlowDetailsMapper flowDetailsMapper;

    @Autowired
    private FlowDetailsService flowDetailsService;

    @Autowired
    private FlowDetailsQueryService flowDetailsQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFlowDetailsMockMvc;

    private FlowDetails flowDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FlowDetails createEntity(EntityManager em) {
        FlowDetails flowDetails = new FlowDetails()
            .flowId(DEFAULT_FLOW_ID)
            .flowName(DEFAULT_FLOW_NAME)
            .mediationSystem(DEFAULT_MEDIATION_SYSTEM)
            .source(DEFAULT_SOURCE)
            .destination(DEFAULT_DESTINATION)
            .fileName(DEFAULT_FILE_NAME)
            .transactionDate(DEFAULT_TRANSACTION_DATE)
            .transactionId(DEFAULT_TRANSACTION_ID)
            .format(DEFAULT_FORMAT)
            .sourceEndpointInterface(DEFAULT_SOURCE_ENDPOINT_INTERFACE)
            .destinationEndpointInterface(DEFAULT_DESTINATION_ENDPOINT_INTERFACE)
            .acknowledgmentExpected(DEFAULT_ACKNOWLEDGMENT_EXPECTED)
            .acknowledgmentReceived(DEFAULT_ACKNOWLEDGMENT_RECEIVED);
        return flowDetails;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FlowDetails createUpdatedEntity(EntityManager em) {
        FlowDetails flowDetails = new FlowDetails()
            .flowId(UPDATED_FLOW_ID)
            .flowName(UPDATED_FLOW_NAME)
            .mediationSystem(UPDATED_MEDIATION_SYSTEM)
            .source(UPDATED_SOURCE)
            .destination(UPDATED_DESTINATION)
            .fileName(UPDATED_FILE_NAME)
            .transactionDate(UPDATED_TRANSACTION_DATE)
            .transactionId(UPDATED_TRANSACTION_ID)
            .format(UPDATED_FORMAT)
            .sourceEndpointInterface(UPDATED_SOURCE_ENDPOINT_INTERFACE)
            .destinationEndpointInterface(UPDATED_DESTINATION_ENDPOINT_INTERFACE)
            .acknowledgmentExpected(UPDATED_ACKNOWLEDGMENT_EXPECTED)
            .acknowledgmentReceived(UPDATED_ACKNOWLEDGMENT_RECEIVED);
        return flowDetails;
    }

    @BeforeEach
    public void initTest() {
        flowDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createFlowDetails() throws Exception {
        int databaseSizeBeforeCreate = flowDetailsRepository.findAll().size();
        // Create the FlowDetails
        FlowDetailsDTO flowDetailsDTO = flowDetailsMapper.toDto(flowDetails);
        restFlowDetailsMockMvc.perform(post("/api/flow-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowDetailsDTO)))
            .andExpect(status().isCreated());

        // Validate the FlowDetails in the database
        List<FlowDetails> flowDetailsList = flowDetailsRepository.findAll();
        assertThat(flowDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        FlowDetails testFlowDetails = flowDetailsList.get(flowDetailsList.size() - 1);
        assertThat(testFlowDetails.getFlowId()).isEqualTo(DEFAULT_FLOW_ID);
        assertThat(testFlowDetails.getFlowName()).isEqualTo(DEFAULT_FLOW_NAME);
        assertThat(testFlowDetails.getMediationSystem()).isEqualTo(DEFAULT_MEDIATION_SYSTEM);
        assertThat(testFlowDetails.getSource()).isEqualTo(DEFAULT_SOURCE);
        assertThat(testFlowDetails.getDestination()).isEqualTo(DEFAULT_DESTINATION);
        assertThat(testFlowDetails.getFileName()).isEqualTo(DEFAULT_FILE_NAME);
        assertThat(testFlowDetails.getTransactionDate()).isEqualTo(DEFAULT_TRANSACTION_DATE);
        assertThat(testFlowDetails.getTransactionId()).isEqualTo(DEFAULT_TRANSACTION_ID);
        assertThat(testFlowDetails.getFormat()).isEqualTo(DEFAULT_FORMAT);
        assertThat(testFlowDetails.getSourceEndpointInterface()).isEqualTo(DEFAULT_SOURCE_ENDPOINT_INTERFACE);
        assertThat(testFlowDetails.getDestinationEndpointInterface()).isEqualTo(DEFAULT_DESTINATION_ENDPOINT_INTERFACE);
        assertThat(testFlowDetails.getAcknowledgmentExpected()).isEqualTo(DEFAULT_ACKNOWLEDGMENT_EXPECTED);
        assertThat(testFlowDetails.getAcknowledgmentReceived()).isEqualTo(DEFAULT_ACKNOWLEDGMENT_RECEIVED);
    }

    @Test
    @Transactional
    public void createFlowDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = flowDetailsRepository.findAll().size();

        // Create the FlowDetails with an existing ID
        flowDetails.setId(1L);
        FlowDetailsDTO flowDetailsDTO = flowDetailsMapper.toDto(flowDetails);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFlowDetailsMockMvc.perform(post("/api/flow-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FlowDetails in the database
        List<FlowDetails> flowDetailsList = flowDetailsRepository.findAll();
        assertThat(flowDetailsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkFlowIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = flowDetailsRepository.findAll().size();
        // set the field null
        flowDetails.setFlowId(null);

        // Create the FlowDetails, which fails.
        FlowDetailsDTO flowDetailsDTO = flowDetailsMapper.toDto(flowDetails);


        restFlowDetailsMockMvc.perform(post("/api/flow-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowDetailsDTO)))
            .andExpect(status().isBadRequest());

        List<FlowDetails> flowDetailsList = flowDetailsRepository.findAll();
        assertThat(flowDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFlowNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = flowDetailsRepository.findAll().size();
        // set the field null
        flowDetails.setFlowName(null);

        // Create the FlowDetails, which fails.
        FlowDetailsDTO flowDetailsDTO = flowDetailsMapper.toDto(flowDetails);


        restFlowDetailsMockMvc.perform(post("/api/flow-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowDetailsDTO)))
            .andExpect(status().isBadRequest());

        List<FlowDetails> flowDetailsList = flowDetailsRepository.findAll();
        assertThat(flowDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMediationSystemIsRequired() throws Exception {
        int databaseSizeBeforeTest = flowDetailsRepository.findAll().size();
        // set the field null
        flowDetails.setMediationSystem(null);

        // Create the FlowDetails, which fails.
        FlowDetailsDTO flowDetailsDTO = flowDetailsMapper.toDto(flowDetails);


        restFlowDetailsMockMvc.perform(post("/api/flow-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowDetailsDTO)))
            .andExpect(status().isBadRequest());

        List<FlowDetails> flowDetailsList = flowDetailsRepository.findAll();
        assertThat(flowDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSourceIsRequired() throws Exception {
        int databaseSizeBeforeTest = flowDetailsRepository.findAll().size();
        // set the field null
        flowDetails.setSource(null);

        // Create the FlowDetails, which fails.
        FlowDetailsDTO flowDetailsDTO = flowDetailsMapper.toDto(flowDetails);


        restFlowDetailsMockMvc.perform(post("/api/flow-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowDetailsDTO)))
            .andExpect(status().isBadRequest());

        List<FlowDetails> flowDetailsList = flowDetailsRepository.findAll();
        assertThat(flowDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDestinationIsRequired() throws Exception {
        int databaseSizeBeforeTest = flowDetailsRepository.findAll().size();
        // set the field null
        flowDetails.setDestination(null);

        // Create the FlowDetails, which fails.
        FlowDetailsDTO flowDetailsDTO = flowDetailsMapper.toDto(flowDetails);


        restFlowDetailsMockMvc.perform(post("/api/flow-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowDetailsDTO)))
            .andExpect(status().isBadRequest());

        List<FlowDetails> flowDetailsList = flowDetailsRepository.findAll();
        assertThat(flowDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTransactionDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = flowDetailsRepository.findAll().size();
        // set the field null
        flowDetails.setTransactionDate(null);

        // Create the FlowDetails, which fails.
        FlowDetailsDTO flowDetailsDTO = flowDetailsMapper.toDto(flowDetails);


        restFlowDetailsMockMvc.perform(post("/api/flow-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowDetailsDTO)))
            .andExpect(status().isBadRequest());

        List<FlowDetails> flowDetailsList = flowDetailsRepository.findAll();
        assertThat(flowDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTransactionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = flowDetailsRepository.findAll().size();
        // set the field null
        flowDetails.setTransactionId(null);

        // Create the FlowDetails, which fails.
        FlowDetailsDTO flowDetailsDTO = flowDetailsMapper.toDto(flowDetails);


        restFlowDetailsMockMvc.perform(post("/api/flow-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowDetailsDTO)))
            .andExpect(status().isBadRequest());

        List<FlowDetails> flowDetailsList = flowDetailsRepository.findAll();
        assertThat(flowDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSourceEndpointInterfaceIsRequired() throws Exception {
        int databaseSizeBeforeTest = flowDetailsRepository.findAll().size();
        // set the field null
        flowDetails.setSourceEndpointInterface(null);

        // Create the FlowDetails, which fails.
        FlowDetailsDTO flowDetailsDTO = flowDetailsMapper.toDto(flowDetails);


        restFlowDetailsMockMvc.perform(post("/api/flow-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowDetailsDTO)))
            .andExpect(status().isBadRequest());

        List<FlowDetails> flowDetailsList = flowDetailsRepository.findAll();
        assertThat(flowDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDestinationEndpointInterfaceIsRequired() throws Exception {
        int databaseSizeBeforeTest = flowDetailsRepository.findAll().size();
        // set the field null
        flowDetails.setDestinationEndpointInterface(null);

        // Create the FlowDetails, which fails.
        FlowDetailsDTO flowDetailsDTO = flowDetailsMapper.toDto(flowDetails);


        restFlowDetailsMockMvc.perform(post("/api/flow-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowDetailsDTO)))
            .andExpect(status().isBadRequest());

        List<FlowDetails> flowDetailsList = flowDetailsRepository.findAll();
        assertThat(flowDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAcknowledgmentExpectedIsRequired() throws Exception {
        int databaseSizeBeforeTest = flowDetailsRepository.findAll().size();
        // set the field null
        flowDetails.setAcknowledgmentExpected(null);

        // Create the FlowDetails, which fails.
        FlowDetailsDTO flowDetailsDTO = flowDetailsMapper.toDto(flowDetails);


        restFlowDetailsMockMvc.perform(post("/api/flow-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowDetailsDTO)))
            .andExpect(status().isBadRequest());

        List<FlowDetails> flowDetailsList = flowDetailsRepository.findAll();
        assertThat(flowDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFlowDetails() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList
        restFlowDetailsMockMvc.perform(get("/api/flow-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(flowDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].flowId").value(hasItem(DEFAULT_FLOW_ID.toString())))
            .andExpect(jsonPath("$.[*].flowName").value(hasItem(DEFAULT_FLOW_NAME)))
            .andExpect(jsonPath("$.[*].mediationSystem").value(hasItem(DEFAULT_MEDIATION_SYSTEM)))
            .andExpect(jsonPath("$.[*].source").value(hasItem(DEFAULT_SOURCE)))
            .andExpect(jsonPath("$.[*].destination").value(hasItem(DEFAULT_DESTINATION)))
            .andExpect(jsonPath("$.[*].fileName").value(hasItem(DEFAULT_FILE_NAME)))
            .andExpect(jsonPath("$.[*].transactionDate").value(hasItem(DEFAULT_TRANSACTION_DATE.toString())))
            .andExpect(jsonPath("$.[*].transactionId").value(hasItem(DEFAULT_TRANSACTION_ID.toString())))
            .andExpect(jsonPath("$.[*].format").value(hasItem(DEFAULT_FORMAT)))
            .andExpect(jsonPath("$.[*].sourceEndpointInterface").value(hasItem(DEFAULT_SOURCE_ENDPOINT_INTERFACE)))
            .andExpect(jsonPath("$.[*].destinationEndpointInterface").value(hasItem(DEFAULT_DESTINATION_ENDPOINT_INTERFACE)))
            .andExpect(jsonPath("$.[*].acknowledgmentExpected").value(hasItem(DEFAULT_ACKNOWLEDGMENT_EXPECTED)))
            .andExpect(jsonPath("$.[*].acknowledgmentReceived").value(hasItem(DEFAULT_ACKNOWLEDGMENT_RECEIVED)));
    }
    
    @Test
    @Transactional
    public void getFlowDetails() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get the flowDetails
        restFlowDetailsMockMvc.perform(get("/api/flow-details/{id}", flowDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(flowDetails.getId().intValue()))
            .andExpect(jsonPath("$.flowId").value(DEFAULT_FLOW_ID.toString()))
            .andExpect(jsonPath("$.flowName").value(DEFAULT_FLOW_NAME))
            .andExpect(jsonPath("$.mediationSystem").value(DEFAULT_MEDIATION_SYSTEM))
            .andExpect(jsonPath("$.source").value(DEFAULT_SOURCE))
            .andExpect(jsonPath("$.destination").value(DEFAULT_DESTINATION))
            .andExpect(jsonPath("$.fileName").value(DEFAULT_FILE_NAME))
            .andExpect(jsonPath("$.transactionDate").value(DEFAULT_TRANSACTION_DATE.toString()))
            .andExpect(jsonPath("$.transactionId").value(DEFAULT_TRANSACTION_ID.toString()))
            .andExpect(jsonPath("$.format").value(DEFAULT_FORMAT))
            .andExpect(jsonPath("$.sourceEndpointInterface").value(DEFAULT_SOURCE_ENDPOINT_INTERFACE))
            .andExpect(jsonPath("$.destinationEndpointInterface").value(DEFAULT_DESTINATION_ENDPOINT_INTERFACE))
            .andExpect(jsonPath("$.acknowledgmentExpected").value(DEFAULT_ACKNOWLEDGMENT_EXPECTED))
            .andExpect(jsonPath("$.acknowledgmentReceived").value(DEFAULT_ACKNOWLEDGMENT_RECEIVED));
    }


    @Test
    @Transactional
    public void getFlowDetailsByIdFiltering() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        Long id = flowDetails.getId();

        defaultFlowDetailsShouldBeFound("id.equals=" + id);
        defaultFlowDetailsShouldNotBeFound("id.notEquals=" + id);

        defaultFlowDetailsShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultFlowDetailsShouldNotBeFound("id.greaterThan=" + id);

        defaultFlowDetailsShouldBeFound("id.lessThanOrEqual=" + id);
        defaultFlowDetailsShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllFlowDetailsByFlowIdIsEqualToSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where flowId equals to DEFAULT_FLOW_ID
        defaultFlowDetailsShouldBeFound("flowId.equals=" + DEFAULT_FLOW_ID);

        // Get all the flowDetailsList where flowId equals to UPDATED_FLOW_ID
        defaultFlowDetailsShouldNotBeFound("flowId.equals=" + UPDATED_FLOW_ID);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsByFlowIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where flowId not equals to DEFAULT_FLOW_ID
        defaultFlowDetailsShouldNotBeFound("flowId.notEquals=" + DEFAULT_FLOW_ID);

        // Get all the flowDetailsList where flowId not equals to UPDATED_FLOW_ID
        defaultFlowDetailsShouldBeFound("flowId.notEquals=" + UPDATED_FLOW_ID);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsByFlowIdIsInShouldWork() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where flowId in DEFAULT_FLOW_ID or UPDATED_FLOW_ID
        defaultFlowDetailsShouldBeFound("flowId.in=" + DEFAULT_FLOW_ID + "," + UPDATED_FLOW_ID);

        // Get all the flowDetailsList where flowId equals to UPDATED_FLOW_ID
        defaultFlowDetailsShouldNotBeFound("flowId.in=" + UPDATED_FLOW_ID);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsByFlowIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where flowId is not null
        defaultFlowDetailsShouldBeFound("flowId.specified=true");

        // Get all the flowDetailsList where flowId is null
        defaultFlowDetailsShouldNotBeFound("flowId.specified=false");
    }

    @Test
    @Transactional
    public void getAllFlowDetailsByFlowNameIsEqualToSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where flowName equals to DEFAULT_FLOW_NAME
        defaultFlowDetailsShouldBeFound("flowName.equals=" + DEFAULT_FLOW_NAME);

        // Get all the flowDetailsList where flowName equals to UPDATED_FLOW_NAME
        defaultFlowDetailsShouldNotBeFound("flowName.equals=" + UPDATED_FLOW_NAME);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsByFlowNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where flowName not equals to DEFAULT_FLOW_NAME
        defaultFlowDetailsShouldNotBeFound("flowName.notEquals=" + DEFAULT_FLOW_NAME);

        // Get all the flowDetailsList where flowName not equals to UPDATED_FLOW_NAME
        defaultFlowDetailsShouldBeFound("flowName.notEquals=" + UPDATED_FLOW_NAME);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsByFlowNameIsInShouldWork() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where flowName in DEFAULT_FLOW_NAME or UPDATED_FLOW_NAME
        defaultFlowDetailsShouldBeFound("flowName.in=" + DEFAULT_FLOW_NAME + "," + UPDATED_FLOW_NAME);

        // Get all the flowDetailsList where flowName equals to UPDATED_FLOW_NAME
        defaultFlowDetailsShouldNotBeFound("flowName.in=" + UPDATED_FLOW_NAME);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsByFlowNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where flowName is not null
        defaultFlowDetailsShouldBeFound("flowName.specified=true");

        // Get all the flowDetailsList where flowName is null
        defaultFlowDetailsShouldNotBeFound("flowName.specified=false");
    }
                @Test
    @Transactional
    public void getAllFlowDetailsByFlowNameContainsSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where flowName contains DEFAULT_FLOW_NAME
        defaultFlowDetailsShouldBeFound("flowName.contains=" + DEFAULT_FLOW_NAME);

        // Get all the flowDetailsList where flowName contains UPDATED_FLOW_NAME
        defaultFlowDetailsShouldNotBeFound("flowName.contains=" + UPDATED_FLOW_NAME);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsByFlowNameNotContainsSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where flowName does not contain DEFAULT_FLOW_NAME
        defaultFlowDetailsShouldNotBeFound("flowName.doesNotContain=" + DEFAULT_FLOW_NAME);

        // Get all the flowDetailsList where flowName does not contain UPDATED_FLOW_NAME
        defaultFlowDetailsShouldBeFound("flowName.doesNotContain=" + UPDATED_FLOW_NAME);
    }


    @Test
    @Transactional
    public void getAllFlowDetailsByMediationSystemIsEqualToSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where mediationSystem equals to DEFAULT_MEDIATION_SYSTEM
        defaultFlowDetailsShouldBeFound("mediationSystem.equals=" + DEFAULT_MEDIATION_SYSTEM);

        // Get all the flowDetailsList where mediationSystem equals to UPDATED_MEDIATION_SYSTEM
        defaultFlowDetailsShouldNotBeFound("mediationSystem.equals=" + UPDATED_MEDIATION_SYSTEM);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsByMediationSystemIsNotEqualToSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where mediationSystem not equals to DEFAULT_MEDIATION_SYSTEM
        defaultFlowDetailsShouldNotBeFound("mediationSystem.notEquals=" + DEFAULT_MEDIATION_SYSTEM);

        // Get all the flowDetailsList where mediationSystem not equals to UPDATED_MEDIATION_SYSTEM
        defaultFlowDetailsShouldBeFound("mediationSystem.notEquals=" + UPDATED_MEDIATION_SYSTEM);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsByMediationSystemIsInShouldWork() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where mediationSystem in DEFAULT_MEDIATION_SYSTEM or UPDATED_MEDIATION_SYSTEM
        defaultFlowDetailsShouldBeFound("mediationSystem.in=" + DEFAULT_MEDIATION_SYSTEM + "," + UPDATED_MEDIATION_SYSTEM);

        // Get all the flowDetailsList where mediationSystem equals to UPDATED_MEDIATION_SYSTEM
        defaultFlowDetailsShouldNotBeFound("mediationSystem.in=" + UPDATED_MEDIATION_SYSTEM);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsByMediationSystemIsNullOrNotNull() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where mediationSystem is not null
        defaultFlowDetailsShouldBeFound("mediationSystem.specified=true");

        // Get all the flowDetailsList where mediationSystem is null
        defaultFlowDetailsShouldNotBeFound("mediationSystem.specified=false");
    }
                @Test
    @Transactional
    public void getAllFlowDetailsByMediationSystemContainsSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where mediationSystem contains DEFAULT_MEDIATION_SYSTEM
        defaultFlowDetailsShouldBeFound("mediationSystem.contains=" + DEFAULT_MEDIATION_SYSTEM);

        // Get all the flowDetailsList where mediationSystem contains UPDATED_MEDIATION_SYSTEM
        defaultFlowDetailsShouldNotBeFound("mediationSystem.contains=" + UPDATED_MEDIATION_SYSTEM);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsByMediationSystemNotContainsSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where mediationSystem does not contain DEFAULT_MEDIATION_SYSTEM
        defaultFlowDetailsShouldNotBeFound("mediationSystem.doesNotContain=" + DEFAULT_MEDIATION_SYSTEM);

        // Get all the flowDetailsList where mediationSystem does not contain UPDATED_MEDIATION_SYSTEM
        defaultFlowDetailsShouldBeFound("mediationSystem.doesNotContain=" + UPDATED_MEDIATION_SYSTEM);
    }


    @Test
    @Transactional
    public void getAllFlowDetailsBySourceIsEqualToSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where source equals to DEFAULT_SOURCE
        defaultFlowDetailsShouldBeFound("source.equals=" + DEFAULT_SOURCE);

        // Get all the flowDetailsList where source equals to UPDATED_SOURCE
        defaultFlowDetailsShouldNotBeFound("source.equals=" + UPDATED_SOURCE);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsBySourceIsNotEqualToSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where source not equals to DEFAULT_SOURCE
        defaultFlowDetailsShouldNotBeFound("source.notEquals=" + DEFAULT_SOURCE);

        // Get all the flowDetailsList where source not equals to UPDATED_SOURCE
        defaultFlowDetailsShouldBeFound("source.notEquals=" + UPDATED_SOURCE);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsBySourceIsInShouldWork() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where source in DEFAULT_SOURCE or UPDATED_SOURCE
        defaultFlowDetailsShouldBeFound("source.in=" + DEFAULT_SOURCE + "," + UPDATED_SOURCE);

        // Get all the flowDetailsList where source equals to UPDATED_SOURCE
        defaultFlowDetailsShouldNotBeFound("source.in=" + UPDATED_SOURCE);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsBySourceIsNullOrNotNull() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where source is not null
        defaultFlowDetailsShouldBeFound("source.specified=true");

        // Get all the flowDetailsList where source is null
        defaultFlowDetailsShouldNotBeFound("source.specified=false");
    }
                @Test
    @Transactional
    public void getAllFlowDetailsBySourceContainsSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where source contains DEFAULT_SOURCE
        defaultFlowDetailsShouldBeFound("source.contains=" + DEFAULT_SOURCE);

        // Get all the flowDetailsList where source contains UPDATED_SOURCE
        defaultFlowDetailsShouldNotBeFound("source.contains=" + UPDATED_SOURCE);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsBySourceNotContainsSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where source does not contain DEFAULT_SOURCE
        defaultFlowDetailsShouldNotBeFound("source.doesNotContain=" + DEFAULT_SOURCE);

        // Get all the flowDetailsList where source does not contain UPDATED_SOURCE
        defaultFlowDetailsShouldBeFound("source.doesNotContain=" + UPDATED_SOURCE);
    }


    @Test
    @Transactional
    public void getAllFlowDetailsByDestinationIsEqualToSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where destination equals to DEFAULT_DESTINATION
        defaultFlowDetailsShouldBeFound("destination.equals=" + DEFAULT_DESTINATION);

        // Get all the flowDetailsList where destination equals to UPDATED_DESTINATION
        defaultFlowDetailsShouldNotBeFound("destination.equals=" + UPDATED_DESTINATION);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsByDestinationIsNotEqualToSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where destination not equals to DEFAULT_DESTINATION
        defaultFlowDetailsShouldNotBeFound("destination.notEquals=" + DEFAULT_DESTINATION);

        // Get all the flowDetailsList where destination not equals to UPDATED_DESTINATION
        defaultFlowDetailsShouldBeFound("destination.notEquals=" + UPDATED_DESTINATION);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsByDestinationIsInShouldWork() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where destination in DEFAULT_DESTINATION or UPDATED_DESTINATION
        defaultFlowDetailsShouldBeFound("destination.in=" + DEFAULT_DESTINATION + "," + UPDATED_DESTINATION);

        // Get all the flowDetailsList where destination equals to UPDATED_DESTINATION
        defaultFlowDetailsShouldNotBeFound("destination.in=" + UPDATED_DESTINATION);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsByDestinationIsNullOrNotNull() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where destination is not null
        defaultFlowDetailsShouldBeFound("destination.specified=true");

        // Get all the flowDetailsList where destination is null
        defaultFlowDetailsShouldNotBeFound("destination.specified=false");
    }
                @Test
    @Transactional
    public void getAllFlowDetailsByDestinationContainsSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where destination contains DEFAULT_DESTINATION
        defaultFlowDetailsShouldBeFound("destination.contains=" + DEFAULT_DESTINATION);

        // Get all the flowDetailsList where destination contains UPDATED_DESTINATION
        defaultFlowDetailsShouldNotBeFound("destination.contains=" + UPDATED_DESTINATION);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsByDestinationNotContainsSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where destination does not contain DEFAULT_DESTINATION
        defaultFlowDetailsShouldNotBeFound("destination.doesNotContain=" + DEFAULT_DESTINATION);

        // Get all the flowDetailsList where destination does not contain UPDATED_DESTINATION
        defaultFlowDetailsShouldBeFound("destination.doesNotContain=" + UPDATED_DESTINATION);
    }


    @Test
    @Transactional
    public void getAllFlowDetailsByFileNameIsEqualToSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where fileName equals to DEFAULT_FILE_NAME
        defaultFlowDetailsShouldBeFound("fileName.equals=" + DEFAULT_FILE_NAME);

        // Get all the flowDetailsList where fileName equals to UPDATED_FILE_NAME
        defaultFlowDetailsShouldNotBeFound("fileName.equals=" + UPDATED_FILE_NAME);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsByFileNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where fileName not equals to DEFAULT_FILE_NAME
        defaultFlowDetailsShouldNotBeFound("fileName.notEquals=" + DEFAULT_FILE_NAME);

        // Get all the flowDetailsList where fileName not equals to UPDATED_FILE_NAME
        defaultFlowDetailsShouldBeFound("fileName.notEquals=" + UPDATED_FILE_NAME);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsByFileNameIsInShouldWork() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where fileName in DEFAULT_FILE_NAME or UPDATED_FILE_NAME
        defaultFlowDetailsShouldBeFound("fileName.in=" + DEFAULT_FILE_NAME + "," + UPDATED_FILE_NAME);

        // Get all the flowDetailsList where fileName equals to UPDATED_FILE_NAME
        defaultFlowDetailsShouldNotBeFound("fileName.in=" + UPDATED_FILE_NAME);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsByFileNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where fileName is not null
        defaultFlowDetailsShouldBeFound("fileName.specified=true");

        // Get all the flowDetailsList where fileName is null
        defaultFlowDetailsShouldNotBeFound("fileName.specified=false");
    }
                @Test
    @Transactional
    public void getAllFlowDetailsByFileNameContainsSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where fileName contains DEFAULT_FILE_NAME
        defaultFlowDetailsShouldBeFound("fileName.contains=" + DEFAULT_FILE_NAME);

        // Get all the flowDetailsList where fileName contains UPDATED_FILE_NAME
        defaultFlowDetailsShouldNotBeFound("fileName.contains=" + UPDATED_FILE_NAME);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsByFileNameNotContainsSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where fileName does not contain DEFAULT_FILE_NAME
        defaultFlowDetailsShouldNotBeFound("fileName.doesNotContain=" + DEFAULT_FILE_NAME);

        // Get all the flowDetailsList where fileName does not contain UPDATED_FILE_NAME
        defaultFlowDetailsShouldBeFound("fileName.doesNotContain=" + UPDATED_FILE_NAME);
    }


    @Test
    @Transactional
    public void getAllFlowDetailsByTransactionDateIsEqualToSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where transactionDate equals to DEFAULT_TRANSACTION_DATE
        defaultFlowDetailsShouldBeFound("transactionDate.equals=" + DEFAULT_TRANSACTION_DATE);

        // Get all the flowDetailsList where transactionDate equals to UPDATED_TRANSACTION_DATE
        defaultFlowDetailsShouldNotBeFound("transactionDate.equals=" + UPDATED_TRANSACTION_DATE);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsByTransactionDateIsNotEqualToSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where transactionDate not equals to DEFAULT_TRANSACTION_DATE
        defaultFlowDetailsShouldNotBeFound("transactionDate.notEquals=" + DEFAULT_TRANSACTION_DATE);

        // Get all the flowDetailsList where transactionDate not equals to UPDATED_TRANSACTION_DATE
        defaultFlowDetailsShouldBeFound("transactionDate.notEquals=" + UPDATED_TRANSACTION_DATE);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsByTransactionDateIsInShouldWork() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where transactionDate in DEFAULT_TRANSACTION_DATE or UPDATED_TRANSACTION_DATE
        defaultFlowDetailsShouldBeFound("transactionDate.in=" + DEFAULT_TRANSACTION_DATE + "," + UPDATED_TRANSACTION_DATE);

        // Get all the flowDetailsList where transactionDate equals to UPDATED_TRANSACTION_DATE
        defaultFlowDetailsShouldNotBeFound("transactionDate.in=" + UPDATED_TRANSACTION_DATE);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsByTransactionDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where transactionDate is not null
        defaultFlowDetailsShouldBeFound("transactionDate.specified=true");

        // Get all the flowDetailsList where transactionDate is null
        defaultFlowDetailsShouldNotBeFound("transactionDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllFlowDetailsByTransactionIdIsEqualToSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where transactionId equals to DEFAULT_TRANSACTION_ID
        defaultFlowDetailsShouldBeFound("transactionId.equals=" + DEFAULT_TRANSACTION_ID);

        // Get all the flowDetailsList where transactionId equals to UPDATED_TRANSACTION_ID
        defaultFlowDetailsShouldNotBeFound("transactionId.equals=" + UPDATED_TRANSACTION_ID);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsByTransactionIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where transactionId not equals to DEFAULT_TRANSACTION_ID
        defaultFlowDetailsShouldNotBeFound("transactionId.notEquals=" + DEFAULT_TRANSACTION_ID);

        // Get all the flowDetailsList where transactionId not equals to UPDATED_TRANSACTION_ID
        defaultFlowDetailsShouldBeFound("transactionId.notEquals=" + UPDATED_TRANSACTION_ID);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsByTransactionIdIsInShouldWork() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where transactionId in DEFAULT_TRANSACTION_ID or UPDATED_TRANSACTION_ID
        defaultFlowDetailsShouldBeFound("transactionId.in=" + DEFAULT_TRANSACTION_ID + "," + UPDATED_TRANSACTION_ID);

        // Get all the flowDetailsList where transactionId equals to UPDATED_TRANSACTION_ID
        defaultFlowDetailsShouldNotBeFound("transactionId.in=" + UPDATED_TRANSACTION_ID);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsByTransactionIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where transactionId is not null
        defaultFlowDetailsShouldBeFound("transactionId.specified=true");

        // Get all the flowDetailsList where transactionId is null
        defaultFlowDetailsShouldNotBeFound("transactionId.specified=false");
    }

    @Test
    @Transactional
    public void getAllFlowDetailsByFormatIsEqualToSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where format equals to DEFAULT_FORMAT
        defaultFlowDetailsShouldBeFound("format.equals=" + DEFAULT_FORMAT);

        // Get all the flowDetailsList where format equals to UPDATED_FORMAT
        defaultFlowDetailsShouldNotBeFound("format.equals=" + UPDATED_FORMAT);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsByFormatIsNotEqualToSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where format not equals to DEFAULT_FORMAT
        defaultFlowDetailsShouldNotBeFound("format.notEquals=" + DEFAULT_FORMAT);

        // Get all the flowDetailsList where format not equals to UPDATED_FORMAT
        defaultFlowDetailsShouldBeFound("format.notEquals=" + UPDATED_FORMAT);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsByFormatIsInShouldWork() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where format in DEFAULT_FORMAT or UPDATED_FORMAT
        defaultFlowDetailsShouldBeFound("format.in=" + DEFAULT_FORMAT + "," + UPDATED_FORMAT);

        // Get all the flowDetailsList where format equals to UPDATED_FORMAT
        defaultFlowDetailsShouldNotBeFound("format.in=" + UPDATED_FORMAT);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsByFormatIsNullOrNotNull() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where format is not null
        defaultFlowDetailsShouldBeFound("format.specified=true");

        // Get all the flowDetailsList where format is null
        defaultFlowDetailsShouldNotBeFound("format.specified=false");
    }
                @Test
    @Transactional
    public void getAllFlowDetailsByFormatContainsSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where format contains DEFAULT_FORMAT
        defaultFlowDetailsShouldBeFound("format.contains=" + DEFAULT_FORMAT);

        // Get all the flowDetailsList where format contains UPDATED_FORMAT
        defaultFlowDetailsShouldNotBeFound("format.contains=" + UPDATED_FORMAT);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsByFormatNotContainsSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where format does not contain DEFAULT_FORMAT
        defaultFlowDetailsShouldNotBeFound("format.doesNotContain=" + DEFAULT_FORMAT);

        // Get all the flowDetailsList where format does not contain UPDATED_FORMAT
        defaultFlowDetailsShouldBeFound("format.doesNotContain=" + UPDATED_FORMAT);
    }


    @Test
    @Transactional
    public void getAllFlowDetailsBySourceEndpointInterfaceIsEqualToSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where sourceEndpointInterface equals to DEFAULT_SOURCE_ENDPOINT_INTERFACE
        defaultFlowDetailsShouldBeFound("sourceEndpointInterface.equals=" + DEFAULT_SOURCE_ENDPOINT_INTERFACE);

        // Get all the flowDetailsList where sourceEndpointInterface equals to UPDATED_SOURCE_ENDPOINT_INTERFACE
        defaultFlowDetailsShouldNotBeFound("sourceEndpointInterface.equals=" + UPDATED_SOURCE_ENDPOINT_INTERFACE);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsBySourceEndpointInterfaceIsNotEqualToSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where sourceEndpointInterface not equals to DEFAULT_SOURCE_ENDPOINT_INTERFACE
        defaultFlowDetailsShouldNotBeFound("sourceEndpointInterface.notEquals=" + DEFAULT_SOURCE_ENDPOINT_INTERFACE);

        // Get all the flowDetailsList where sourceEndpointInterface not equals to UPDATED_SOURCE_ENDPOINT_INTERFACE
        defaultFlowDetailsShouldBeFound("sourceEndpointInterface.notEquals=" + UPDATED_SOURCE_ENDPOINT_INTERFACE);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsBySourceEndpointInterfaceIsInShouldWork() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where sourceEndpointInterface in DEFAULT_SOURCE_ENDPOINT_INTERFACE or UPDATED_SOURCE_ENDPOINT_INTERFACE
        defaultFlowDetailsShouldBeFound("sourceEndpointInterface.in=" + DEFAULT_SOURCE_ENDPOINT_INTERFACE + "," + UPDATED_SOURCE_ENDPOINT_INTERFACE);

        // Get all the flowDetailsList where sourceEndpointInterface equals to UPDATED_SOURCE_ENDPOINT_INTERFACE
        defaultFlowDetailsShouldNotBeFound("sourceEndpointInterface.in=" + UPDATED_SOURCE_ENDPOINT_INTERFACE);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsBySourceEndpointInterfaceIsNullOrNotNull() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where sourceEndpointInterface is not null
        defaultFlowDetailsShouldBeFound("sourceEndpointInterface.specified=true");

        // Get all the flowDetailsList where sourceEndpointInterface is null
        defaultFlowDetailsShouldNotBeFound("sourceEndpointInterface.specified=false");
    }
                @Test
    @Transactional
    public void getAllFlowDetailsBySourceEndpointInterfaceContainsSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where sourceEndpointInterface contains DEFAULT_SOURCE_ENDPOINT_INTERFACE
        defaultFlowDetailsShouldBeFound("sourceEndpointInterface.contains=" + DEFAULT_SOURCE_ENDPOINT_INTERFACE);

        // Get all the flowDetailsList where sourceEndpointInterface contains UPDATED_SOURCE_ENDPOINT_INTERFACE
        defaultFlowDetailsShouldNotBeFound("sourceEndpointInterface.contains=" + UPDATED_SOURCE_ENDPOINT_INTERFACE);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsBySourceEndpointInterfaceNotContainsSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where sourceEndpointInterface does not contain DEFAULT_SOURCE_ENDPOINT_INTERFACE
        defaultFlowDetailsShouldNotBeFound("sourceEndpointInterface.doesNotContain=" + DEFAULT_SOURCE_ENDPOINT_INTERFACE);

        // Get all the flowDetailsList where sourceEndpointInterface does not contain UPDATED_SOURCE_ENDPOINT_INTERFACE
        defaultFlowDetailsShouldBeFound("sourceEndpointInterface.doesNotContain=" + UPDATED_SOURCE_ENDPOINT_INTERFACE);
    }


    @Test
    @Transactional
    public void getAllFlowDetailsByDestinationEndpointInterfaceIsEqualToSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where destinationEndpointInterface equals to DEFAULT_DESTINATION_ENDPOINT_INTERFACE
        defaultFlowDetailsShouldBeFound("destinationEndpointInterface.equals=" + DEFAULT_DESTINATION_ENDPOINT_INTERFACE);

        // Get all the flowDetailsList where destinationEndpointInterface equals to UPDATED_DESTINATION_ENDPOINT_INTERFACE
        defaultFlowDetailsShouldNotBeFound("destinationEndpointInterface.equals=" + UPDATED_DESTINATION_ENDPOINT_INTERFACE);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsByDestinationEndpointInterfaceIsNotEqualToSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where destinationEndpointInterface not equals to DEFAULT_DESTINATION_ENDPOINT_INTERFACE
        defaultFlowDetailsShouldNotBeFound("destinationEndpointInterface.notEquals=" + DEFAULT_DESTINATION_ENDPOINT_INTERFACE);

        // Get all the flowDetailsList where destinationEndpointInterface not equals to UPDATED_DESTINATION_ENDPOINT_INTERFACE
        defaultFlowDetailsShouldBeFound("destinationEndpointInterface.notEquals=" + UPDATED_DESTINATION_ENDPOINT_INTERFACE);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsByDestinationEndpointInterfaceIsInShouldWork() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where destinationEndpointInterface in DEFAULT_DESTINATION_ENDPOINT_INTERFACE or UPDATED_DESTINATION_ENDPOINT_INTERFACE
        defaultFlowDetailsShouldBeFound("destinationEndpointInterface.in=" + DEFAULT_DESTINATION_ENDPOINT_INTERFACE + "," + UPDATED_DESTINATION_ENDPOINT_INTERFACE);

        // Get all the flowDetailsList where destinationEndpointInterface equals to UPDATED_DESTINATION_ENDPOINT_INTERFACE
        defaultFlowDetailsShouldNotBeFound("destinationEndpointInterface.in=" + UPDATED_DESTINATION_ENDPOINT_INTERFACE);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsByDestinationEndpointInterfaceIsNullOrNotNull() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where destinationEndpointInterface is not null
        defaultFlowDetailsShouldBeFound("destinationEndpointInterface.specified=true");

        // Get all the flowDetailsList where destinationEndpointInterface is null
        defaultFlowDetailsShouldNotBeFound("destinationEndpointInterface.specified=false");
    }
                @Test
    @Transactional
    public void getAllFlowDetailsByDestinationEndpointInterfaceContainsSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where destinationEndpointInterface contains DEFAULT_DESTINATION_ENDPOINT_INTERFACE
        defaultFlowDetailsShouldBeFound("destinationEndpointInterface.contains=" + DEFAULT_DESTINATION_ENDPOINT_INTERFACE);

        // Get all the flowDetailsList where destinationEndpointInterface contains UPDATED_DESTINATION_ENDPOINT_INTERFACE
        defaultFlowDetailsShouldNotBeFound("destinationEndpointInterface.contains=" + UPDATED_DESTINATION_ENDPOINT_INTERFACE);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsByDestinationEndpointInterfaceNotContainsSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where destinationEndpointInterface does not contain DEFAULT_DESTINATION_ENDPOINT_INTERFACE
        defaultFlowDetailsShouldNotBeFound("destinationEndpointInterface.doesNotContain=" + DEFAULT_DESTINATION_ENDPOINT_INTERFACE);

        // Get all the flowDetailsList where destinationEndpointInterface does not contain UPDATED_DESTINATION_ENDPOINT_INTERFACE
        defaultFlowDetailsShouldBeFound("destinationEndpointInterface.doesNotContain=" + UPDATED_DESTINATION_ENDPOINT_INTERFACE);
    }


    @Test
    @Transactional
    public void getAllFlowDetailsByAcknowledgmentExpectedIsEqualToSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where acknowledgmentExpected equals to DEFAULT_ACKNOWLEDGMENT_EXPECTED
        defaultFlowDetailsShouldBeFound("acknowledgmentExpected.equals=" + DEFAULT_ACKNOWLEDGMENT_EXPECTED);

        // Get all the flowDetailsList where acknowledgmentExpected equals to UPDATED_ACKNOWLEDGMENT_EXPECTED
        defaultFlowDetailsShouldNotBeFound("acknowledgmentExpected.equals=" + UPDATED_ACKNOWLEDGMENT_EXPECTED);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsByAcknowledgmentExpectedIsNotEqualToSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where acknowledgmentExpected not equals to DEFAULT_ACKNOWLEDGMENT_EXPECTED
        defaultFlowDetailsShouldNotBeFound("acknowledgmentExpected.notEquals=" + DEFAULT_ACKNOWLEDGMENT_EXPECTED);

        // Get all the flowDetailsList where acknowledgmentExpected not equals to UPDATED_ACKNOWLEDGMENT_EXPECTED
        defaultFlowDetailsShouldBeFound("acknowledgmentExpected.notEquals=" + UPDATED_ACKNOWLEDGMENT_EXPECTED);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsByAcknowledgmentExpectedIsInShouldWork() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where acknowledgmentExpected in DEFAULT_ACKNOWLEDGMENT_EXPECTED or UPDATED_ACKNOWLEDGMENT_EXPECTED
        defaultFlowDetailsShouldBeFound("acknowledgmentExpected.in=" + DEFAULT_ACKNOWLEDGMENT_EXPECTED + "," + UPDATED_ACKNOWLEDGMENT_EXPECTED);

        // Get all the flowDetailsList where acknowledgmentExpected equals to UPDATED_ACKNOWLEDGMENT_EXPECTED
        defaultFlowDetailsShouldNotBeFound("acknowledgmentExpected.in=" + UPDATED_ACKNOWLEDGMENT_EXPECTED);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsByAcknowledgmentExpectedIsNullOrNotNull() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where acknowledgmentExpected is not null
        defaultFlowDetailsShouldBeFound("acknowledgmentExpected.specified=true");

        // Get all the flowDetailsList where acknowledgmentExpected is null
        defaultFlowDetailsShouldNotBeFound("acknowledgmentExpected.specified=false");
    }
                @Test
    @Transactional
    public void getAllFlowDetailsByAcknowledgmentExpectedContainsSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where acknowledgmentExpected contains DEFAULT_ACKNOWLEDGMENT_EXPECTED
        defaultFlowDetailsShouldBeFound("acknowledgmentExpected.contains=" + DEFAULT_ACKNOWLEDGMENT_EXPECTED);

        // Get all the flowDetailsList where acknowledgmentExpected contains UPDATED_ACKNOWLEDGMENT_EXPECTED
        defaultFlowDetailsShouldNotBeFound("acknowledgmentExpected.contains=" + UPDATED_ACKNOWLEDGMENT_EXPECTED);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsByAcknowledgmentExpectedNotContainsSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where acknowledgmentExpected does not contain DEFAULT_ACKNOWLEDGMENT_EXPECTED
        defaultFlowDetailsShouldNotBeFound("acknowledgmentExpected.doesNotContain=" + DEFAULT_ACKNOWLEDGMENT_EXPECTED);

        // Get all the flowDetailsList where acknowledgmentExpected does not contain UPDATED_ACKNOWLEDGMENT_EXPECTED
        defaultFlowDetailsShouldBeFound("acknowledgmentExpected.doesNotContain=" + UPDATED_ACKNOWLEDGMENT_EXPECTED);
    }


    @Test
    @Transactional
    public void getAllFlowDetailsByAcknowledgmentReceivedIsEqualToSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where acknowledgmentReceived equals to DEFAULT_ACKNOWLEDGMENT_RECEIVED
        defaultFlowDetailsShouldBeFound("acknowledgmentReceived.equals=" + DEFAULT_ACKNOWLEDGMENT_RECEIVED);

        // Get all the flowDetailsList where acknowledgmentReceived equals to UPDATED_ACKNOWLEDGMENT_RECEIVED
        defaultFlowDetailsShouldNotBeFound("acknowledgmentReceived.equals=" + UPDATED_ACKNOWLEDGMENT_RECEIVED);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsByAcknowledgmentReceivedIsNotEqualToSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where acknowledgmentReceived not equals to DEFAULT_ACKNOWLEDGMENT_RECEIVED
        defaultFlowDetailsShouldNotBeFound("acknowledgmentReceived.notEquals=" + DEFAULT_ACKNOWLEDGMENT_RECEIVED);

        // Get all the flowDetailsList where acknowledgmentReceived not equals to UPDATED_ACKNOWLEDGMENT_RECEIVED
        defaultFlowDetailsShouldBeFound("acknowledgmentReceived.notEquals=" + UPDATED_ACKNOWLEDGMENT_RECEIVED);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsByAcknowledgmentReceivedIsInShouldWork() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where acknowledgmentReceived in DEFAULT_ACKNOWLEDGMENT_RECEIVED or UPDATED_ACKNOWLEDGMENT_RECEIVED
        defaultFlowDetailsShouldBeFound("acknowledgmentReceived.in=" + DEFAULT_ACKNOWLEDGMENT_RECEIVED + "," + UPDATED_ACKNOWLEDGMENT_RECEIVED);

        // Get all the flowDetailsList where acknowledgmentReceived equals to UPDATED_ACKNOWLEDGMENT_RECEIVED
        defaultFlowDetailsShouldNotBeFound("acknowledgmentReceived.in=" + UPDATED_ACKNOWLEDGMENT_RECEIVED);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsByAcknowledgmentReceivedIsNullOrNotNull() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where acknowledgmentReceived is not null
        defaultFlowDetailsShouldBeFound("acknowledgmentReceived.specified=true");

        // Get all the flowDetailsList where acknowledgmentReceived is null
        defaultFlowDetailsShouldNotBeFound("acknowledgmentReceived.specified=false");
    }
                @Test
    @Transactional
    public void getAllFlowDetailsByAcknowledgmentReceivedContainsSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where acknowledgmentReceived contains DEFAULT_ACKNOWLEDGMENT_RECEIVED
        defaultFlowDetailsShouldBeFound("acknowledgmentReceived.contains=" + DEFAULT_ACKNOWLEDGMENT_RECEIVED);

        // Get all the flowDetailsList where acknowledgmentReceived contains UPDATED_ACKNOWLEDGMENT_RECEIVED
        defaultFlowDetailsShouldNotBeFound("acknowledgmentReceived.contains=" + UPDATED_ACKNOWLEDGMENT_RECEIVED);
    }

    @Test
    @Transactional
    public void getAllFlowDetailsByAcknowledgmentReceivedNotContainsSomething() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        // Get all the flowDetailsList where acknowledgmentReceived does not contain DEFAULT_ACKNOWLEDGMENT_RECEIVED
        defaultFlowDetailsShouldNotBeFound("acknowledgmentReceived.doesNotContain=" + DEFAULT_ACKNOWLEDGMENT_RECEIVED);

        // Get all the flowDetailsList where acknowledgmentReceived does not contain UPDATED_ACKNOWLEDGMENT_RECEIVED
        defaultFlowDetailsShouldBeFound("acknowledgmentReceived.doesNotContain=" + UPDATED_ACKNOWLEDGMENT_RECEIVED);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultFlowDetailsShouldBeFound(String filter) throws Exception {
        restFlowDetailsMockMvc.perform(get("/api/flow-details?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(flowDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].flowId").value(hasItem(DEFAULT_FLOW_ID.toString())))
            .andExpect(jsonPath("$.[*].flowName").value(hasItem(DEFAULT_FLOW_NAME)))
            .andExpect(jsonPath("$.[*].mediationSystem").value(hasItem(DEFAULT_MEDIATION_SYSTEM)))
            .andExpect(jsonPath("$.[*].source").value(hasItem(DEFAULT_SOURCE)))
            .andExpect(jsonPath("$.[*].destination").value(hasItem(DEFAULT_DESTINATION)))
            .andExpect(jsonPath("$.[*].fileName").value(hasItem(DEFAULT_FILE_NAME)))
            .andExpect(jsonPath("$.[*].transactionDate").value(hasItem(DEFAULT_TRANSACTION_DATE.toString())))
            .andExpect(jsonPath("$.[*].transactionId").value(hasItem(DEFAULT_TRANSACTION_ID.toString())))
            .andExpect(jsonPath("$.[*].format").value(hasItem(DEFAULT_FORMAT)))
            .andExpect(jsonPath("$.[*].sourceEndpointInterface").value(hasItem(DEFAULT_SOURCE_ENDPOINT_INTERFACE)))
            .andExpect(jsonPath("$.[*].destinationEndpointInterface").value(hasItem(DEFAULT_DESTINATION_ENDPOINT_INTERFACE)))
            .andExpect(jsonPath("$.[*].acknowledgmentExpected").value(hasItem(DEFAULT_ACKNOWLEDGMENT_EXPECTED)))
            .andExpect(jsonPath("$.[*].acknowledgmentReceived").value(hasItem(DEFAULT_ACKNOWLEDGMENT_RECEIVED)));

        // Check, that the count call also returns 1
        restFlowDetailsMockMvc.perform(get("/api/flow-details/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultFlowDetailsShouldNotBeFound(String filter) throws Exception {
        restFlowDetailsMockMvc.perform(get("/api/flow-details?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restFlowDetailsMockMvc.perform(get("/api/flow-details/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingFlowDetails() throws Exception {
        // Get the flowDetails
        restFlowDetailsMockMvc.perform(get("/api/flow-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFlowDetails() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        int databaseSizeBeforeUpdate = flowDetailsRepository.findAll().size();

        // Update the flowDetails
        FlowDetails updatedFlowDetails = flowDetailsRepository.findById(flowDetails.getId()).get();
        // Disconnect from session so that the updates on updatedFlowDetails are not directly saved in db
        em.detach(updatedFlowDetails);
        updatedFlowDetails
            .flowId(UPDATED_FLOW_ID)
            .flowName(UPDATED_FLOW_NAME)
            .mediationSystem(UPDATED_MEDIATION_SYSTEM)
            .source(UPDATED_SOURCE)
            .destination(UPDATED_DESTINATION)
            .fileName(UPDATED_FILE_NAME)
            .transactionDate(UPDATED_TRANSACTION_DATE)
            .transactionId(UPDATED_TRANSACTION_ID)
            .format(UPDATED_FORMAT)
            .sourceEndpointInterface(UPDATED_SOURCE_ENDPOINT_INTERFACE)
            .destinationEndpointInterface(UPDATED_DESTINATION_ENDPOINT_INTERFACE)
            .acknowledgmentExpected(UPDATED_ACKNOWLEDGMENT_EXPECTED)
            .acknowledgmentReceived(UPDATED_ACKNOWLEDGMENT_RECEIVED);
        FlowDetailsDTO flowDetailsDTO = flowDetailsMapper.toDto(updatedFlowDetails);

        restFlowDetailsMockMvc.perform(put("/api/flow-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowDetailsDTO)))
            .andExpect(status().isOk());

        // Validate the FlowDetails in the database
        List<FlowDetails> flowDetailsList = flowDetailsRepository.findAll();
        assertThat(flowDetailsList).hasSize(databaseSizeBeforeUpdate);
        FlowDetails testFlowDetails = flowDetailsList.get(flowDetailsList.size() - 1);
        assertThat(testFlowDetails.getFlowId()).isEqualTo(UPDATED_FLOW_ID);
        assertThat(testFlowDetails.getFlowName()).isEqualTo(UPDATED_FLOW_NAME);
        assertThat(testFlowDetails.getMediationSystem()).isEqualTo(UPDATED_MEDIATION_SYSTEM);
        assertThat(testFlowDetails.getSource()).isEqualTo(UPDATED_SOURCE);
        assertThat(testFlowDetails.getDestination()).isEqualTo(UPDATED_DESTINATION);
        assertThat(testFlowDetails.getFileName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testFlowDetails.getTransactionDate()).isEqualTo(UPDATED_TRANSACTION_DATE);
        assertThat(testFlowDetails.getTransactionId()).isEqualTo(UPDATED_TRANSACTION_ID);
        assertThat(testFlowDetails.getFormat()).isEqualTo(UPDATED_FORMAT);
        assertThat(testFlowDetails.getSourceEndpointInterface()).isEqualTo(UPDATED_SOURCE_ENDPOINT_INTERFACE);
        assertThat(testFlowDetails.getDestinationEndpointInterface()).isEqualTo(UPDATED_DESTINATION_ENDPOINT_INTERFACE);
        assertThat(testFlowDetails.getAcknowledgmentExpected()).isEqualTo(UPDATED_ACKNOWLEDGMENT_EXPECTED);
        assertThat(testFlowDetails.getAcknowledgmentReceived()).isEqualTo(UPDATED_ACKNOWLEDGMENT_RECEIVED);
    }

    @Test
    @Transactional
    public void updateNonExistingFlowDetails() throws Exception {
        int databaseSizeBeforeUpdate = flowDetailsRepository.findAll().size();

        // Create the FlowDetails
        FlowDetailsDTO flowDetailsDTO = flowDetailsMapper.toDto(flowDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFlowDetailsMockMvc.perform(put("/api/flow-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FlowDetails in the database
        List<FlowDetails> flowDetailsList = flowDetailsRepository.findAll();
        assertThat(flowDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFlowDetails() throws Exception {
        // Initialize the database
        flowDetailsRepository.saveAndFlush(flowDetails);

        int databaseSizeBeforeDelete = flowDetailsRepository.findAll().size();

        // Delete the flowDetails
        restFlowDetailsMockMvc.perform(delete("/api/flow-details/{id}", flowDetails.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FlowDetails> flowDetailsList = flowDetailsRepository.findAll();
        assertThat(flowDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
