package com.dav.mediation.web.rest;

import com.dav.mediation.MediationApp;
import com.dav.mediation.domain.FlowTransactionsView;
import com.dav.mediation.repository.FlowTransactionsViewRepository;
import com.dav.mediation.service.FlowTransactionsViewService;
import com.dav.mediation.service.dto.FlowTransactionsViewDTO;
import com.dav.mediation.service.mapper.FlowTransactionsViewMapper;
import com.dav.mediation.service.dto.FlowTransactionsViewCriteria;
import com.dav.mediation.service.FlowTransactionsViewQueryService;

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
 * Integration tests for the {@link FlowTransactionsViewResource} REST controller.
 */
@SpringBootTest(classes = MediationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FlowTransactionsViewResourceIT {

    private static final UUID DEFAULT_FLOW_ID = UUID.randomUUID();
    private static final UUID UPDATED_FLOW_ID = UUID.randomUUID();

    private static final String DEFAULT_FLOW_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FLOW_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FLOW_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_FLOW_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_SENDER_IS = "AAAAAAAAAA";
    private static final String UPDATED_SENDER_IS = "BBBBBBBBBB";

    private static final String DEFAULT_SENDER_PROTOCOL = "AAAAAAAAAA";
    private static final String UPDATED_SENDER_PROTOCOL = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE_INSTANCE = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_INSTANCE = "BBBBBBBBBB";

    private static final String DEFAULT_RECEIVER_IS = "AAAAAAAAAA";
    private static final String UPDATED_RECEIVER_IS = "BBBBBBBBBB";

    private static final String DEFAULT_RECEIVER_PROTOCOL = "AAAAAAAAAA";
    private static final String UPDATED_RECEIVER_PROTOCOL = "BBBBBBBBBB";

    private static final String DEFAULT_TARGET_INSTANCE = "AAAAAAAAAA";
    private static final String UPDATED_TARGET_INSTANCE = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_PAYLOAD = "AAAAAAAAAA";
    private static final String UPDATED_FILE_PAYLOAD = "BBBBBBBBBB";

    private static final Instant DEFAULT_FLOW_DATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FLOW_DATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    @Autowired
    private FlowTransactionsViewRepository flowTransactionsViewRepository;

    @Autowired
    private FlowTransactionsViewMapper flowTransactionsViewMapper;

    @Autowired
    private FlowTransactionsViewService flowTransactionsViewService;

    @Autowired
    private FlowTransactionsViewQueryService flowTransactionsViewQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFlowTransactionsViewMockMvc;

    private FlowTransactionsView flowTransactionsView;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FlowTransactionsView createEntity(EntityManager em) {
        FlowTransactionsView flowTransactionsView = new FlowTransactionsView()
            .flowId(DEFAULT_FLOW_ID)
            .flowName(DEFAULT_FLOW_NAME)
            .flowType(DEFAULT_FLOW_TYPE)
            .senderIs(DEFAULT_SENDER_IS)
            .senderProtocol(DEFAULT_SENDER_PROTOCOL)
            .sourceInstance(DEFAULT_SOURCE_INSTANCE)
            .receiverIs(DEFAULT_RECEIVER_IS)
            .receiverProtocol(DEFAULT_RECEIVER_PROTOCOL)
            .targetInstance(DEFAULT_TARGET_INSTANCE)
            .filePayload(DEFAULT_FILE_PAYLOAD)
            .flowDateTime(DEFAULT_FLOW_DATE_TIME)
            .status(DEFAULT_STATUS);
        return flowTransactionsView;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FlowTransactionsView createUpdatedEntity(EntityManager em) {
        FlowTransactionsView flowTransactionsView = new FlowTransactionsView()
            .flowId(UPDATED_FLOW_ID)
            .flowName(UPDATED_FLOW_NAME)
            .flowType(UPDATED_FLOW_TYPE)
            .senderIs(UPDATED_SENDER_IS)
            .senderProtocol(UPDATED_SENDER_PROTOCOL)
            .sourceInstance(UPDATED_SOURCE_INSTANCE)
            .receiverIs(UPDATED_RECEIVER_IS)
            .receiverProtocol(UPDATED_RECEIVER_PROTOCOL)
            .targetInstance(UPDATED_TARGET_INSTANCE)
            .filePayload(UPDATED_FILE_PAYLOAD)
            .flowDateTime(UPDATED_FLOW_DATE_TIME)
            .status(UPDATED_STATUS);
        return flowTransactionsView;
    }

    @BeforeEach
    public void initTest() {
        flowTransactionsView = createEntity(em);
    }

    @Test
    @Transactional
    public void createFlowTransactionsView() throws Exception {
        int databaseSizeBeforeCreate = flowTransactionsViewRepository.findAll().size();
        // Create the FlowTransactionsView
        FlowTransactionsViewDTO flowTransactionsViewDTO = flowTransactionsViewMapper.toDto(flowTransactionsView);
        restFlowTransactionsViewMockMvc.perform(post("/api/flow-transactions-views")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowTransactionsViewDTO)))
            .andExpect(status().isCreated());

        // Validate the FlowTransactionsView in the database
        List<FlowTransactionsView> flowTransactionsViewList = flowTransactionsViewRepository.findAll();
        assertThat(flowTransactionsViewList).hasSize(databaseSizeBeforeCreate + 1);
        FlowTransactionsView testFlowTransactionsView = flowTransactionsViewList.get(flowTransactionsViewList.size() - 1);
        assertThat(testFlowTransactionsView.getFlowId()).isEqualTo(DEFAULT_FLOW_ID);
        assertThat(testFlowTransactionsView.getFlowName()).isEqualTo(DEFAULT_FLOW_NAME);
        assertThat(testFlowTransactionsView.getFlowType()).isEqualTo(DEFAULT_FLOW_TYPE);
        assertThat(testFlowTransactionsView.getSenderIs()).isEqualTo(DEFAULT_SENDER_IS);
        assertThat(testFlowTransactionsView.getSenderProtocol()).isEqualTo(DEFAULT_SENDER_PROTOCOL);
        assertThat(testFlowTransactionsView.getSourceInstance()).isEqualTo(DEFAULT_SOURCE_INSTANCE);
        assertThat(testFlowTransactionsView.getReceiverIs()).isEqualTo(DEFAULT_RECEIVER_IS);
        assertThat(testFlowTransactionsView.getReceiverProtocol()).isEqualTo(DEFAULT_RECEIVER_PROTOCOL);
        assertThat(testFlowTransactionsView.getTargetInstance()).isEqualTo(DEFAULT_TARGET_INSTANCE);
        assertThat(testFlowTransactionsView.getFilePayload()).isEqualTo(DEFAULT_FILE_PAYLOAD);
        assertThat(testFlowTransactionsView.getFlowDateTime()).isEqualTo(DEFAULT_FLOW_DATE_TIME);
        assertThat(testFlowTransactionsView.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createFlowTransactionsViewWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = flowTransactionsViewRepository.findAll().size();

        // Create the FlowTransactionsView with an existing ID
        flowTransactionsView.setId(1L);
        FlowTransactionsViewDTO flowTransactionsViewDTO = flowTransactionsViewMapper.toDto(flowTransactionsView);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFlowTransactionsViewMockMvc.perform(post("/api/flow-transactions-views")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowTransactionsViewDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FlowTransactionsView in the database
        List<FlowTransactionsView> flowTransactionsViewList = flowTransactionsViewRepository.findAll();
        assertThat(flowTransactionsViewList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkFlowIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = flowTransactionsViewRepository.findAll().size();
        // set the field null
        flowTransactionsView.setFlowId(null);

        // Create the FlowTransactionsView, which fails.
        FlowTransactionsViewDTO flowTransactionsViewDTO = flowTransactionsViewMapper.toDto(flowTransactionsView);


        restFlowTransactionsViewMockMvc.perform(post("/api/flow-transactions-views")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowTransactionsViewDTO)))
            .andExpect(status().isBadRequest());

        List<FlowTransactionsView> flowTransactionsViewList = flowTransactionsViewRepository.findAll();
        assertThat(flowTransactionsViewList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFlowNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = flowTransactionsViewRepository.findAll().size();
        // set the field null
        flowTransactionsView.setFlowName(null);

        // Create the FlowTransactionsView, which fails.
        FlowTransactionsViewDTO flowTransactionsViewDTO = flowTransactionsViewMapper.toDto(flowTransactionsView);


        restFlowTransactionsViewMockMvc.perform(post("/api/flow-transactions-views")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowTransactionsViewDTO)))
            .andExpect(status().isBadRequest());

        List<FlowTransactionsView> flowTransactionsViewList = flowTransactionsViewRepository.findAll();
        assertThat(flowTransactionsViewList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFlowTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = flowTransactionsViewRepository.findAll().size();
        // set the field null
        flowTransactionsView.setFlowType(null);

        // Create the FlowTransactionsView, which fails.
        FlowTransactionsViewDTO flowTransactionsViewDTO = flowTransactionsViewMapper.toDto(flowTransactionsView);


        restFlowTransactionsViewMockMvc.perform(post("/api/flow-transactions-views")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowTransactionsViewDTO)))
            .andExpect(status().isBadRequest());

        List<FlowTransactionsView> flowTransactionsViewList = flowTransactionsViewRepository.findAll();
        assertThat(flowTransactionsViewList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSenderIsIsRequired() throws Exception {
        int databaseSizeBeforeTest = flowTransactionsViewRepository.findAll().size();
        // set the field null
        flowTransactionsView.setSenderIs(null);

        // Create the FlowTransactionsView, which fails.
        FlowTransactionsViewDTO flowTransactionsViewDTO = flowTransactionsViewMapper.toDto(flowTransactionsView);


        restFlowTransactionsViewMockMvc.perform(post("/api/flow-transactions-views")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowTransactionsViewDTO)))
            .andExpect(status().isBadRequest());

        List<FlowTransactionsView> flowTransactionsViewList = flowTransactionsViewRepository.findAll();
        assertThat(flowTransactionsViewList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSenderProtocolIsRequired() throws Exception {
        int databaseSizeBeforeTest = flowTransactionsViewRepository.findAll().size();
        // set the field null
        flowTransactionsView.setSenderProtocol(null);

        // Create the FlowTransactionsView, which fails.
        FlowTransactionsViewDTO flowTransactionsViewDTO = flowTransactionsViewMapper.toDto(flowTransactionsView);


        restFlowTransactionsViewMockMvc.perform(post("/api/flow-transactions-views")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowTransactionsViewDTO)))
            .andExpect(status().isBadRequest());

        List<FlowTransactionsView> flowTransactionsViewList = flowTransactionsViewRepository.findAll();
        assertThat(flowTransactionsViewList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSourceInstanceIsRequired() throws Exception {
        int databaseSizeBeforeTest = flowTransactionsViewRepository.findAll().size();
        // set the field null
        flowTransactionsView.setSourceInstance(null);

        // Create the FlowTransactionsView, which fails.
        FlowTransactionsViewDTO flowTransactionsViewDTO = flowTransactionsViewMapper.toDto(flowTransactionsView);


        restFlowTransactionsViewMockMvc.perform(post("/api/flow-transactions-views")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowTransactionsViewDTO)))
            .andExpect(status().isBadRequest());

        List<FlowTransactionsView> flowTransactionsViewList = flowTransactionsViewRepository.findAll();
        assertThat(flowTransactionsViewList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkReceiverIsIsRequired() throws Exception {
        int databaseSizeBeforeTest = flowTransactionsViewRepository.findAll().size();
        // set the field null
        flowTransactionsView.setReceiverIs(null);

        // Create the FlowTransactionsView, which fails.
        FlowTransactionsViewDTO flowTransactionsViewDTO = flowTransactionsViewMapper.toDto(flowTransactionsView);


        restFlowTransactionsViewMockMvc.perform(post("/api/flow-transactions-views")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowTransactionsViewDTO)))
            .andExpect(status().isBadRequest());

        List<FlowTransactionsView> flowTransactionsViewList = flowTransactionsViewRepository.findAll();
        assertThat(flowTransactionsViewList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkReceiverProtocolIsRequired() throws Exception {
        int databaseSizeBeforeTest = flowTransactionsViewRepository.findAll().size();
        // set the field null
        flowTransactionsView.setReceiverProtocol(null);

        // Create the FlowTransactionsView, which fails.
        FlowTransactionsViewDTO flowTransactionsViewDTO = flowTransactionsViewMapper.toDto(flowTransactionsView);


        restFlowTransactionsViewMockMvc.perform(post("/api/flow-transactions-views")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowTransactionsViewDTO)))
            .andExpect(status().isBadRequest());

        List<FlowTransactionsView> flowTransactionsViewList = flowTransactionsViewRepository.findAll();
        assertThat(flowTransactionsViewList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTargetInstanceIsRequired() throws Exception {
        int databaseSizeBeforeTest = flowTransactionsViewRepository.findAll().size();
        // set the field null
        flowTransactionsView.setTargetInstance(null);

        // Create the FlowTransactionsView, which fails.
        FlowTransactionsViewDTO flowTransactionsViewDTO = flowTransactionsViewMapper.toDto(flowTransactionsView);


        restFlowTransactionsViewMockMvc.perform(post("/api/flow-transactions-views")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowTransactionsViewDTO)))
            .andExpect(status().isBadRequest());

        List<FlowTransactionsView> flowTransactionsViewList = flowTransactionsViewRepository.findAll();
        assertThat(flowTransactionsViewList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFlowDateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = flowTransactionsViewRepository.findAll().size();
        // set the field null
        flowTransactionsView.setFlowDateTime(null);

        // Create the FlowTransactionsView, which fails.
        FlowTransactionsViewDTO flowTransactionsViewDTO = flowTransactionsViewMapper.toDto(flowTransactionsView);


        restFlowTransactionsViewMockMvc.perform(post("/api/flow-transactions-views")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowTransactionsViewDTO)))
            .andExpect(status().isBadRequest());

        List<FlowTransactionsView> flowTransactionsViewList = flowTransactionsViewRepository.findAll();
        assertThat(flowTransactionsViewList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = flowTransactionsViewRepository.findAll().size();
        // set the field null
        flowTransactionsView.setStatus(null);

        // Create the FlowTransactionsView, which fails.
        FlowTransactionsViewDTO flowTransactionsViewDTO = flowTransactionsViewMapper.toDto(flowTransactionsView);


        restFlowTransactionsViewMockMvc.perform(post("/api/flow-transactions-views")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowTransactionsViewDTO)))
            .andExpect(status().isBadRequest());

        List<FlowTransactionsView> flowTransactionsViewList = flowTransactionsViewRepository.findAll();
        assertThat(flowTransactionsViewList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViews() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList
        restFlowTransactionsViewMockMvc.perform(get("/api/flow-transactions-views?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(flowTransactionsView.getId().intValue())))
            .andExpect(jsonPath("$.[*].flowId").value(hasItem(DEFAULT_FLOW_ID.toString())))
            .andExpect(jsonPath("$.[*].flowName").value(hasItem(DEFAULT_FLOW_NAME)))
            .andExpect(jsonPath("$.[*].flowType").value(hasItem(DEFAULT_FLOW_TYPE)))
            .andExpect(jsonPath("$.[*].senderIs").value(hasItem(DEFAULT_SENDER_IS)))
            .andExpect(jsonPath("$.[*].senderProtocol").value(hasItem(DEFAULT_SENDER_PROTOCOL)))
            .andExpect(jsonPath("$.[*].sourceInstance").value(hasItem(DEFAULT_SOURCE_INSTANCE)))
            .andExpect(jsonPath("$.[*].receiverIs").value(hasItem(DEFAULT_RECEIVER_IS)))
            .andExpect(jsonPath("$.[*].receiverProtocol").value(hasItem(DEFAULT_RECEIVER_PROTOCOL)))
            .andExpect(jsonPath("$.[*].targetInstance").value(hasItem(DEFAULT_TARGET_INSTANCE)))
            .andExpect(jsonPath("$.[*].filePayload").value(hasItem(DEFAULT_FILE_PAYLOAD)))
            .andExpect(jsonPath("$.[*].flowDateTime").value(hasItem(DEFAULT_FLOW_DATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    
    @Test
    @Transactional
    public void getFlowTransactionsView() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get the flowTransactionsView
        restFlowTransactionsViewMockMvc.perform(get("/api/flow-transactions-views/{id}", flowTransactionsView.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(flowTransactionsView.getId().intValue()))
            .andExpect(jsonPath("$.flowId").value(DEFAULT_FLOW_ID.toString()))
            .andExpect(jsonPath("$.flowName").value(DEFAULT_FLOW_NAME))
            .andExpect(jsonPath("$.flowType").value(DEFAULT_FLOW_TYPE))
            .andExpect(jsonPath("$.senderIs").value(DEFAULT_SENDER_IS))
            .andExpect(jsonPath("$.senderProtocol").value(DEFAULT_SENDER_PROTOCOL))
            .andExpect(jsonPath("$.sourceInstance").value(DEFAULT_SOURCE_INSTANCE))
            .andExpect(jsonPath("$.receiverIs").value(DEFAULT_RECEIVER_IS))
            .andExpect(jsonPath("$.receiverProtocol").value(DEFAULT_RECEIVER_PROTOCOL))
            .andExpect(jsonPath("$.targetInstance").value(DEFAULT_TARGET_INSTANCE))
            .andExpect(jsonPath("$.filePayload").value(DEFAULT_FILE_PAYLOAD))
            .andExpect(jsonPath("$.flowDateTime").value(DEFAULT_FLOW_DATE_TIME.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }


    @Test
    @Transactional
    public void getFlowTransactionsViewsByIdFiltering() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        Long id = flowTransactionsView.getId();

        defaultFlowTransactionsViewShouldBeFound("id.equals=" + id);
        defaultFlowTransactionsViewShouldNotBeFound("id.notEquals=" + id);

        defaultFlowTransactionsViewShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultFlowTransactionsViewShouldNotBeFound("id.greaterThan=" + id);

        defaultFlowTransactionsViewShouldBeFound("id.lessThanOrEqual=" + id);
        defaultFlowTransactionsViewShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllFlowTransactionsViewsByFlowIdIsEqualToSomething() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where flowId equals to DEFAULT_FLOW_ID
        defaultFlowTransactionsViewShouldBeFound("flowId.equals=" + DEFAULT_FLOW_ID);

        // Get all the flowTransactionsViewList where flowId equals to UPDATED_FLOW_ID
        defaultFlowTransactionsViewShouldNotBeFound("flowId.equals=" + UPDATED_FLOW_ID);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsByFlowIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where flowId not equals to DEFAULT_FLOW_ID
        defaultFlowTransactionsViewShouldNotBeFound("flowId.notEquals=" + DEFAULT_FLOW_ID);

        // Get all the flowTransactionsViewList where flowId not equals to UPDATED_FLOW_ID
        defaultFlowTransactionsViewShouldBeFound("flowId.notEquals=" + UPDATED_FLOW_ID);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsByFlowIdIsInShouldWork() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where flowId in DEFAULT_FLOW_ID or UPDATED_FLOW_ID
        defaultFlowTransactionsViewShouldBeFound("flowId.in=" + DEFAULT_FLOW_ID + "," + UPDATED_FLOW_ID);

        // Get all the flowTransactionsViewList where flowId equals to UPDATED_FLOW_ID
        defaultFlowTransactionsViewShouldNotBeFound("flowId.in=" + UPDATED_FLOW_ID);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsByFlowIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where flowId is not null
        defaultFlowTransactionsViewShouldBeFound("flowId.specified=true");

        // Get all the flowTransactionsViewList where flowId is null
        defaultFlowTransactionsViewShouldNotBeFound("flowId.specified=false");
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsByFlowNameIsEqualToSomething() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where flowName equals to DEFAULT_FLOW_NAME
        defaultFlowTransactionsViewShouldBeFound("flowName.equals=" + DEFAULT_FLOW_NAME);

        // Get all the flowTransactionsViewList where flowName equals to UPDATED_FLOW_NAME
        defaultFlowTransactionsViewShouldNotBeFound("flowName.equals=" + UPDATED_FLOW_NAME);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsByFlowNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where flowName not equals to DEFAULT_FLOW_NAME
        defaultFlowTransactionsViewShouldNotBeFound("flowName.notEquals=" + DEFAULT_FLOW_NAME);

        // Get all the flowTransactionsViewList where flowName not equals to UPDATED_FLOW_NAME
        defaultFlowTransactionsViewShouldBeFound("flowName.notEquals=" + UPDATED_FLOW_NAME);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsByFlowNameIsInShouldWork() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where flowName in DEFAULT_FLOW_NAME or UPDATED_FLOW_NAME
        defaultFlowTransactionsViewShouldBeFound("flowName.in=" + DEFAULT_FLOW_NAME + "," + UPDATED_FLOW_NAME);

        // Get all the flowTransactionsViewList where flowName equals to UPDATED_FLOW_NAME
        defaultFlowTransactionsViewShouldNotBeFound("flowName.in=" + UPDATED_FLOW_NAME);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsByFlowNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where flowName is not null
        defaultFlowTransactionsViewShouldBeFound("flowName.specified=true");

        // Get all the flowTransactionsViewList where flowName is null
        defaultFlowTransactionsViewShouldNotBeFound("flowName.specified=false");
    }
                @Test
    @Transactional
    public void getAllFlowTransactionsViewsByFlowNameContainsSomething() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where flowName contains DEFAULT_FLOW_NAME
        defaultFlowTransactionsViewShouldBeFound("flowName.contains=" + DEFAULT_FLOW_NAME);

        // Get all the flowTransactionsViewList where flowName contains UPDATED_FLOW_NAME
        defaultFlowTransactionsViewShouldNotBeFound("flowName.contains=" + UPDATED_FLOW_NAME);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsByFlowNameNotContainsSomething() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where flowName does not contain DEFAULT_FLOW_NAME
        defaultFlowTransactionsViewShouldNotBeFound("flowName.doesNotContain=" + DEFAULT_FLOW_NAME);

        // Get all the flowTransactionsViewList where flowName does not contain UPDATED_FLOW_NAME
        defaultFlowTransactionsViewShouldBeFound("flowName.doesNotContain=" + UPDATED_FLOW_NAME);
    }


    @Test
    @Transactional
    public void getAllFlowTransactionsViewsByFlowTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where flowType equals to DEFAULT_FLOW_TYPE
        defaultFlowTransactionsViewShouldBeFound("flowType.equals=" + DEFAULT_FLOW_TYPE);

        // Get all the flowTransactionsViewList where flowType equals to UPDATED_FLOW_TYPE
        defaultFlowTransactionsViewShouldNotBeFound("flowType.equals=" + UPDATED_FLOW_TYPE);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsByFlowTypeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where flowType not equals to DEFAULT_FLOW_TYPE
        defaultFlowTransactionsViewShouldNotBeFound("flowType.notEquals=" + DEFAULT_FLOW_TYPE);

        // Get all the flowTransactionsViewList where flowType not equals to UPDATED_FLOW_TYPE
        defaultFlowTransactionsViewShouldBeFound("flowType.notEquals=" + UPDATED_FLOW_TYPE);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsByFlowTypeIsInShouldWork() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where flowType in DEFAULT_FLOW_TYPE or UPDATED_FLOW_TYPE
        defaultFlowTransactionsViewShouldBeFound("flowType.in=" + DEFAULT_FLOW_TYPE + "," + UPDATED_FLOW_TYPE);

        // Get all the flowTransactionsViewList where flowType equals to UPDATED_FLOW_TYPE
        defaultFlowTransactionsViewShouldNotBeFound("flowType.in=" + UPDATED_FLOW_TYPE);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsByFlowTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where flowType is not null
        defaultFlowTransactionsViewShouldBeFound("flowType.specified=true");

        // Get all the flowTransactionsViewList where flowType is null
        defaultFlowTransactionsViewShouldNotBeFound("flowType.specified=false");
    }
                @Test
    @Transactional
    public void getAllFlowTransactionsViewsByFlowTypeContainsSomething() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where flowType contains DEFAULT_FLOW_TYPE
        defaultFlowTransactionsViewShouldBeFound("flowType.contains=" + DEFAULT_FLOW_TYPE);

        // Get all the flowTransactionsViewList where flowType contains UPDATED_FLOW_TYPE
        defaultFlowTransactionsViewShouldNotBeFound("flowType.contains=" + UPDATED_FLOW_TYPE);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsByFlowTypeNotContainsSomething() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where flowType does not contain DEFAULT_FLOW_TYPE
        defaultFlowTransactionsViewShouldNotBeFound("flowType.doesNotContain=" + DEFAULT_FLOW_TYPE);

        // Get all the flowTransactionsViewList where flowType does not contain UPDATED_FLOW_TYPE
        defaultFlowTransactionsViewShouldBeFound("flowType.doesNotContain=" + UPDATED_FLOW_TYPE);
    }


    @Test
    @Transactional
    public void getAllFlowTransactionsViewsBySenderIsIsEqualToSomething() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where senderIs equals to DEFAULT_SENDER_IS
        defaultFlowTransactionsViewShouldBeFound("senderIs.equals=" + DEFAULT_SENDER_IS);

        // Get all the flowTransactionsViewList where senderIs equals to UPDATED_SENDER_IS
        defaultFlowTransactionsViewShouldNotBeFound("senderIs.equals=" + UPDATED_SENDER_IS);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsBySenderIsIsNotEqualToSomething() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where senderIs not equals to DEFAULT_SENDER_IS
        defaultFlowTransactionsViewShouldNotBeFound("senderIs.notEquals=" + DEFAULT_SENDER_IS);

        // Get all the flowTransactionsViewList where senderIs not equals to UPDATED_SENDER_IS
        defaultFlowTransactionsViewShouldBeFound("senderIs.notEquals=" + UPDATED_SENDER_IS);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsBySenderIsIsInShouldWork() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where senderIs in DEFAULT_SENDER_IS or UPDATED_SENDER_IS
        defaultFlowTransactionsViewShouldBeFound("senderIs.in=" + DEFAULT_SENDER_IS + "," + UPDATED_SENDER_IS);

        // Get all the flowTransactionsViewList where senderIs equals to UPDATED_SENDER_IS
        defaultFlowTransactionsViewShouldNotBeFound("senderIs.in=" + UPDATED_SENDER_IS);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsBySenderIsIsNullOrNotNull() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where senderIs is not null
        defaultFlowTransactionsViewShouldBeFound("senderIs.specified=true");

        // Get all the flowTransactionsViewList where senderIs is null
        defaultFlowTransactionsViewShouldNotBeFound("senderIs.specified=false");
    }
                @Test
    @Transactional
    public void getAllFlowTransactionsViewsBySenderIsContainsSomething() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where senderIs contains DEFAULT_SENDER_IS
        defaultFlowTransactionsViewShouldBeFound("senderIs.contains=" + DEFAULT_SENDER_IS);

        // Get all the flowTransactionsViewList where senderIs contains UPDATED_SENDER_IS
        defaultFlowTransactionsViewShouldNotBeFound("senderIs.contains=" + UPDATED_SENDER_IS);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsBySenderIsNotContainsSomething() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where senderIs does not contain DEFAULT_SENDER_IS
        defaultFlowTransactionsViewShouldNotBeFound("senderIs.doesNotContain=" + DEFAULT_SENDER_IS);

        // Get all the flowTransactionsViewList where senderIs does not contain UPDATED_SENDER_IS
        defaultFlowTransactionsViewShouldBeFound("senderIs.doesNotContain=" + UPDATED_SENDER_IS);
    }


    @Test
    @Transactional
    public void getAllFlowTransactionsViewsBySenderProtocolIsEqualToSomething() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where senderProtocol equals to DEFAULT_SENDER_PROTOCOL
        defaultFlowTransactionsViewShouldBeFound("senderProtocol.equals=" + DEFAULT_SENDER_PROTOCOL);

        // Get all the flowTransactionsViewList where senderProtocol equals to UPDATED_SENDER_PROTOCOL
        defaultFlowTransactionsViewShouldNotBeFound("senderProtocol.equals=" + UPDATED_SENDER_PROTOCOL);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsBySenderProtocolIsNotEqualToSomething() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where senderProtocol not equals to DEFAULT_SENDER_PROTOCOL
        defaultFlowTransactionsViewShouldNotBeFound("senderProtocol.notEquals=" + DEFAULT_SENDER_PROTOCOL);

        // Get all the flowTransactionsViewList where senderProtocol not equals to UPDATED_SENDER_PROTOCOL
        defaultFlowTransactionsViewShouldBeFound("senderProtocol.notEquals=" + UPDATED_SENDER_PROTOCOL);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsBySenderProtocolIsInShouldWork() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where senderProtocol in DEFAULT_SENDER_PROTOCOL or UPDATED_SENDER_PROTOCOL
        defaultFlowTransactionsViewShouldBeFound("senderProtocol.in=" + DEFAULT_SENDER_PROTOCOL + "," + UPDATED_SENDER_PROTOCOL);

        // Get all the flowTransactionsViewList where senderProtocol equals to UPDATED_SENDER_PROTOCOL
        defaultFlowTransactionsViewShouldNotBeFound("senderProtocol.in=" + UPDATED_SENDER_PROTOCOL);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsBySenderProtocolIsNullOrNotNull() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where senderProtocol is not null
        defaultFlowTransactionsViewShouldBeFound("senderProtocol.specified=true");

        // Get all the flowTransactionsViewList where senderProtocol is null
        defaultFlowTransactionsViewShouldNotBeFound("senderProtocol.specified=false");
    }
                @Test
    @Transactional
    public void getAllFlowTransactionsViewsBySenderProtocolContainsSomething() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where senderProtocol contains DEFAULT_SENDER_PROTOCOL
        defaultFlowTransactionsViewShouldBeFound("senderProtocol.contains=" + DEFAULT_SENDER_PROTOCOL);

        // Get all the flowTransactionsViewList where senderProtocol contains UPDATED_SENDER_PROTOCOL
        defaultFlowTransactionsViewShouldNotBeFound("senderProtocol.contains=" + UPDATED_SENDER_PROTOCOL);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsBySenderProtocolNotContainsSomething() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where senderProtocol does not contain DEFAULT_SENDER_PROTOCOL
        defaultFlowTransactionsViewShouldNotBeFound("senderProtocol.doesNotContain=" + DEFAULT_SENDER_PROTOCOL);

        // Get all the flowTransactionsViewList where senderProtocol does not contain UPDATED_SENDER_PROTOCOL
        defaultFlowTransactionsViewShouldBeFound("senderProtocol.doesNotContain=" + UPDATED_SENDER_PROTOCOL);
    }


    @Test
    @Transactional
    public void getAllFlowTransactionsViewsBySourceInstanceIsEqualToSomething() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where sourceInstance equals to DEFAULT_SOURCE_INSTANCE
        defaultFlowTransactionsViewShouldBeFound("sourceInstance.equals=" + DEFAULT_SOURCE_INSTANCE);

        // Get all the flowTransactionsViewList where sourceInstance equals to UPDATED_SOURCE_INSTANCE
        defaultFlowTransactionsViewShouldNotBeFound("sourceInstance.equals=" + UPDATED_SOURCE_INSTANCE);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsBySourceInstanceIsNotEqualToSomething() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where sourceInstance not equals to DEFAULT_SOURCE_INSTANCE
        defaultFlowTransactionsViewShouldNotBeFound("sourceInstance.notEquals=" + DEFAULT_SOURCE_INSTANCE);

        // Get all the flowTransactionsViewList where sourceInstance not equals to UPDATED_SOURCE_INSTANCE
        defaultFlowTransactionsViewShouldBeFound("sourceInstance.notEquals=" + UPDATED_SOURCE_INSTANCE);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsBySourceInstanceIsInShouldWork() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where sourceInstance in DEFAULT_SOURCE_INSTANCE or UPDATED_SOURCE_INSTANCE
        defaultFlowTransactionsViewShouldBeFound("sourceInstance.in=" + DEFAULT_SOURCE_INSTANCE + "," + UPDATED_SOURCE_INSTANCE);

        // Get all the flowTransactionsViewList where sourceInstance equals to UPDATED_SOURCE_INSTANCE
        defaultFlowTransactionsViewShouldNotBeFound("sourceInstance.in=" + UPDATED_SOURCE_INSTANCE);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsBySourceInstanceIsNullOrNotNull() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where sourceInstance is not null
        defaultFlowTransactionsViewShouldBeFound("sourceInstance.specified=true");

        // Get all the flowTransactionsViewList where sourceInstance is null
        defaultFlowTransactionsViewShouldNotBeFound("sourceInstance.specified=false");
    }
                @Test
    @Transactional
    public void getAllFlowTransactionsViewsBySourceInstanceContainsSomething() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where sourceInstance contains DEFAULT_SOURCE_INSTANCE
        defaultFlowTransactionsViewShouldBeFound("sourceInstance.contains=" + DEFAULT_SOURCE_INSTANCE);

        // Get all the flowTransactionsViewList where sourceInstance contains UPDATED_SOURCE_INSTANCE
        defaultFlowTransactionsViewShouldNotBeFound("sourceInstance.contains=" + UPDATED_SOURCE_INSTANCE);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsBySourceInstanceNotContainsSomething() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where sourceInstance does not contain DEFAULT_SOURCE_INSTANCE
        defaultFlowTransactionsViewShouldNotBeFound("sourceInstance.doesNotContain=" + DEFAULT_SOURCE_INSTANCE);

        // Get all the flowTransactionsViewList where sourceInstance does not contain UPDATED_SOURCE_INSTANCE
        defaultFlowTransactionsViewShouldBeFound("sourceInstance.doesNotContain=" + UPDATED_SOURCE_INSTANCE);
    }


    @Test
    @Transactional
    public void getAllFlowTransactionsViewsByReceiverIsIsEqualToSomething() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where receiverIs equals to DEFAULT_RECEIVER_IS
        defaultFlowTransactionsViewShouldBeFound("receiverIs.equals=" + DEFAULT_RECEIVER_IS);

        // Get all the flowTransactionsViewList where receiverIs equals to UPDATED_RECEIVER_IS
        defaultFlowTransactionsViewShouldNotBeFound("receiverIs.equals=" + UPDATED_RECEIVER_IS);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsByReceiverIsIsNotEqualToSomething() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where receiverIs not equals to DEFAULT_RECEIVER_IS
        defaultFlowTransactionsViewShouldNotBeFound("receiverIs.notEquals=" + DEFAULT_RECEIVER_IS);

        // Get all the flowTransactionsViewList where receiverIs not equals to UPDATED_RECEIVER_IS
        defaultFlowTransactionsViewShouldBeFound("receiverIs.notEquals=" + UPDATED_RECEIVER_IS);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsByReceiverIsIsInShouldWork() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where receiverIs in DEFAULT_RECEIVER_IS or UPDATED_RECEIVER_IS
        defaultFlowTransactionsViewShouldBeFound("receiverIs.in=" + DEFAULT_RECEIVER_IS + "," + UPDATED_RECEIVER_IS);

        // Get all the flowTransactionsViewList where receiverIs equals to UPDATED_RECEIVER_IS
        defaultFlowTransactionsViewShouldNotBeFound("receiverIs.in=" + UPDATED_RECEIVER_IS);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsByReceiverIsIsNullOrNotNull() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where receiverIs is not null
        defaultFlowTransactionsViewShouldBeFound("receiverIs.specified=true");

        // Get all the flowTransactionsViewList where receiverIs is null
        defaultFlowTransactionsViewShouldNotBeFound("receiverIs.specified=false");
    }
                @Test
    @Transactional
    public void getAllFlowTransactionsViewsByReceiverIsContainsSomething() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where receiverIs contains DEFAULT_RECEIVER_IS
        defaultFlowTransactionsViewShouldBeFound("receiverIs.contains=" + DEFAULT_RECEIVER_IS);

        // Get all the flowTransactionsViewList where receiverIs contains UPDATED_RECEIVER_IS
        defaultFlowTransactionsViewShouldNotBeFound("receiverIs.contains=" + UPDATED_RECEIVER_IS);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsByReceiverIsNotContainsSomething() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where receiverIs does not contain DEFAULT_RECEIVER_IS
        defaultFlowTransactionsViewShouldNotBeFound("receiverIs.doesNotContain=" + DEFAULT_RECEIVER_IS);

        // Get all the flowTransactionsViewList where receiverIs does not contain UPDATED_RECEIVER_IS
        defaultFlowTransactionsViewShouldBeFound("receiverIs.doesNotContain=" + UPDATED_RECEIVER_IS);
    }


    @Test
    @Transactional
    public void getAllFlowTransactionsViewsByReceiverProtocolIsEqualToSomething() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where receiverProtocol equals to DEFAULT_RECEIVER_PROTOCOL
        defaultFlowTransactionsViewShouldBeFound("receiverProtocol.equals=" + DEFAULT_RECEIVER_PROTOCOL);

        // Get all the flowTransactionsViewList where receiverProtocol equals to UPDATED_RECEIVER_PROTOCOL
        defaultFlowTransactionsViewShouldNotBeFound("receiverProtocol.equals=" + UPDATED_RECEIVER_PROTOCOL);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsByReceiverProtocolIsNotEqualToSomething() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where receiverProtocol not equals to DEFAULT_RECEIVER_PROTOCOL
        defaultFlowTransactionsViewShouldNotBeFound("receiverProtocol.notEquals=" + DEFAULT_RECEIVER_PROTOCOL);

        // Get all the flowTransactionsViewList where receiverProtocol not equals to UPDATED_RECEIVER_PROTOCOL
        defaultFlowTransactionsViewShouldBeFound("receiverProtocol.notEquals=" + UPDATED_RECEIVER_PROTOCOL);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsByReceiverProtocolIsInShouldWork() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where receiverProtocol in DEFAULT_RECEIVER_PROTOCOL or UPDATED_RECEIVER_PROTOCOL
        defaultFlowTransactionsViewShouldBeFound("receiverProtocol.in=" + DEFAULT_RECEIVER_PROTOCOL + "," + UPDATED_RECEIVER_PROTOCOL);

        // Get all the flowTransactionsViewList where receiverProtocol equals to UPDATED_RECEIVER_PROTOCOL
        defaultFlowTransactionsViewShouldNotBeFound("receiverProtocol.in=" + UPDATED_RECEIVER_PROTOCOL);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsByReceiverProtocolIsNullOrNotNull() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where receiverProtocol is not null
        defaultFlowTransactionsViewShouldBeFound("receiverProtocol.specified=true");

        // Get all the flowTransactionsViewList where receiverProtocol is null
        defaultFlowTransactionsViewShouldNotBeFound("receiverProtocol.specified=false");
    }
                @Test
    @Transactional
    public void getAllFlowTransactionsViewsByReceiverProtocolContainsSomething() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where receiverProtocol contains DEFAULT_RECEIVER_PROTOCOL
        defaultFlowTransactionsViewShouldBeFound("receiverProtocol.contains=" + DEFAULT_RECEIVER_PROTOCOL);

        // Get all the flowTransactionsViewList where receiverProtocol contains UPDATED_RECEIVER_PROTOCOL
        defaultFlowTransactionsViewShouldNotBeFound("receiverProtocol.contains=" + UPDATED_RECEIVER_PROTOCOL);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsByReceiverProtocolNotContainsSomething() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where receiverProtocol does not contain DEFAULT_RECEIVER_PROTOCOL
        defaultFlowTransactionsViewShouldNotBeFound("receiverProtocol.doesNotContain=" + DEFAULT_RECEIVER_PROTOCOL);

        // Get all the flowTransactionsViewList where receiverProtocol does not contain UPDATED_RECEIVER_PROTOCOL
        defaultFlowTransactionsViewShouldBeFound("receiverProtocol.doesNotContain=" + UPDATED_RECEIVER_PROTOCOL);
    }


    @Test
    @Transactional
    public void getAllFlowTransactionsViewsByTargetInstanceIsEqualToSomething() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where targetInstance equals to DEFAULT_TARGET_INSTANCE
        defaultFlowTransactionsViewShouldBeFound("targetInstance.equals=" + DEFAULT_TARGET_INSTANCE);

        // Get all the flowTransactionsViewList where targetInstance equals to UPDATED_TARGET_INSTANCE
        defaultFlowTransactionsViewShouldNotBeFound("targetInstance.equals=" + UPDATED_TARGET_INSTANCE);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsByTargetInstanceIsNotEqualToSomething() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where targetInstance not equals to DEFAULT_TARGET_INSTANCE
        defaultFlowTransactionsViewShouldNotBeFound("targetInstance.notEquals=" + DEFAULT_TARGET_INSTANCE);

        // Get all the flowTransactionsViewList where targetInstance not equals to UPDATED_TARGET_INSTANCE
        defaultFlowTransactionsViewShouldBeFound("targetInstance.notEquals=" + UPDATED_TARGET_INSTANCE);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsByTargetInstanceIsInShouldWork() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where targetInstance in DEFAULT_TARGET_INSTANCE or UPDATED_TARGET_INSTANCE
        defaultFlowTransactionsViewShouldBeFound("targetInstance.in=" + DEFAULT_TARGET_INSTANCE + "," + UPDATED_TARGET_INSTANCE);

        // Get all the flowTransactionsViewList where targetInstance equals to UPDATED_TARGET_INSTANCE
        defaultFlowTransactionsViewShouldNotBeFound("targetInstance.in=" + UPDATED_TARGET_INSTANCE);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsByTargetInstanceIsNullOrNotNull() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where targetInstance is not null
        defaultFlowTransactionsViewShouldBeFound("targetInstance.specified=true");

        // Get all the flowTransactionsViewList where targetInstance is null
        defaultFlowTransactionsViewShouldNotBeFound("targetInstance.specified=false");
    }
                @Test
    @Transactional
    public void getAllFlowTransactionsViewsByTargetInstanceContainsSomething() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where targetInstance contains DEFAULT_TARGET_INSTANCE
        defaultFlowTransactionsViewShouldBeFound("targetInstance.contains=" + DEFAULT_TARGET_INSTANCE);

        // Get all the flowTransactionsViewList where targetInstance contains UPDATED_TARGET_INSTANCE
        defaultFlowTransactionsViewShouldNotBeFound("targetInstance.contains=" + UPDATED_TARGET_INSTANCE);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsByTargetInstanceNotContainsSomething() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where targetInstance does not contain DEFAULT_TARGET_INSTANCE
        defaultFlowTransactionsViewShouldNotBeFound("targetInstance.doesNotContain=" + DEFAULT_TARGET_INSTANCE);

        // Get all the flowTransactionsViewList where targetInstance does not contain UPDATED_TARGET_INSTANCE
        defaultFlowTransactionsViewShouldBeFound("targetInstance.doesNotContain=" + UPDATED_TARGET_INSTANCE);
    }


    @Test
    @Transactional
    public void getAllFlowTransactionsViewsByFilePayloadIsEqualToSomething() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where filePayload equals to DEFAULT_FILE_PAYLOAD
        defaultFlowTransactionsViewShouldBeFound("filePayload.equals=" + DEFAULT_FILE_PAYLOAD);

        // Get all the flowTransactionsViewList where filePayload equals to UPDATED_FILE_PAYLOAD
        defaultFlowTransactionsViewShouldNotBeFound("filePayload.equals=" + UPDATED_FILE_PAYLOAD);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsByFilePayloadIsNotEqualToSomething() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where filePayload not equals to DEFAULT_FILE_PAYLOAD
        defaultFlowTransactionsViewShouldNotBeFound("filePayload.notEquals=" + DEFAULT_FILE_PAYLOAD);

        // Get all the flowTransactionsViewList where filePayload not equals to UPDATED_FILE_PAYLOAD
        defaultFlowTransactionsViewShouldBeFound("filePayload.notEquals=" + UPDATED_FILE_PAYLOAD);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsByFilePayloadIsInShouldWork() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where filePayload in DEFAULT_FILE_PAYLOAD or UPDATED_FILE_PAYLOAD
        defaultFlowTransactionsViewShouldBeFound("filePayload.in=" + DEFAULT_FILE_PAYLOAD + "," + UPDATED_FILE_PAYLOAD);

        // Get all the flowTransactionsViewList where filePayload equals to UPDATED_FILE_PAYLOAD
        defaultFlowTransactionsViewShouldNotBeFound("filePayload.in=" + UPDATED_FILE_PAYLOAD);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsByFilePayloadIsNullOrNotNull() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where filePayload is not null
        defaultFlowTransactionsViewShouldBeFound("filePayload.specified=true");

        // Get all the flowTransactionsViewList where filePayload is null
        defaultFlowTransactionsViewShouldNotBeFound("filePayload.specified=false");
    }
                @Test
    @Transactional
    public void getAllFlowTransactionsViewsByFilePayloadContainsSomething() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where filePayload contains DEFAULT_FILE_PAYLOAD
        defaultFlowTransactionsViewShouldBeFound("filePayload.contains=" + DEFAULT_FILE_PAYLOAD);

        // Get all the flowTransactionsViewList where filePayload contains UPDATED_FILE_PAYLOAD
        defaultFlowTransactionsViewShouldNotBeFound("filePayload.contains=" + UPDATED_FILE_PAYLOAD);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsByFilePayloadNotContainsSomething() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where filePayload does not contain DEFAULT_FILE_PAYLOAD
        defaultFlowTransactionsViewShouldNotBeFound("filePayload.doesNotContain=" + DEFAULT_FILE_PAYLOAD);

        // Get all the flowTransactionsViewList where filePayload does not contain UPDATED_FILE_PAYLOAD
        defaultFlowTransactionsViewShouldBeFound("filePayload.doesNotContain=" + UPDATED_FILE_PAYLOAD);
    }


    @Test
    @Transactional
    public void getAllFlowTransactionsViewsByFlowDateTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where flowDateTime equals to DEFAULT_FLOW_DATE_TIME
        defaultFlowTransactionsViewShouldBeFound("flowDateTime.equals=" + DEFAULT_FLOW_DATE_TIME);

        // Get all the flowTransactionsViewList where flowDateTime equals to UPDATED_FLOW_DATE_TIME
        defaultFlowTransactionsViewShouldNotBeFound("flowDateTime.equals=" + UPDATED_FLOW_DATE_TIME);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsByFlowDateTimeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where flowDateTime not equals to DEFAULT_FLOW_DATE_TIME
        defaultFlowTransactionsViewShouldNotBeFound("flowDateTime.notEquals=" + DEFAULT_FLOW_DATE_TIME);

        // Get all the flowTransactionsViewList where flowDateTime not equals to UPDATED_FLOW_DATE_TIME
        defaultFlowTransactionsViewShouldBeFound("flowDateTime.notEquals=" + UPDATED_FLOW_DATE_TIME);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsByFlowDateTimeIsInShouldWork() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where flowDateTime in DEFAULT_FLOW_DATE_TIME or UPDATED_FLOW_DATE_TIME
        defaultFlowTransactionsViewShouldBeFound("flowDateTime.in=" + DEFAULT_FLOW_DATE_TIME + "," + UPDATED_FLOW_DATE_TIME);

        // Get all the flowTransactionsViewList where flowDateTime equals to UPDATED_FLOW_DATE_TIME
        defaultFlowTransactionsViewShouldNotBeFound("flowDateTime.in=" + UPDATED_FLOW_DATE_TIME);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsByFlowDateTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where flowDateTime is not null
        defaultFlowTransactionsViewShouldBeFound("flowDateTime.specified=true");

        // Get all the flowTransactionsViewList where flowDateTime is null
        defaultFlowTransactionsViewShouldNotBeFound("flowDateTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where status equals to DEFAULT_STATUS
        defaultFlowTransactionsViewShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the flowTransactionsViewList where status equals to UPDATED_STATUS
        defaultFlowTransactionsViewShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsByStatusIsNotEqualToSomething() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where status not equals to DEFAULT_STATUS
        defaultFlowTransactionsViewShouldNotBeFound("status.notEquals=" + DEFAULT_STATUS);

        // Get all the flowTransactionsViewList where status not equals to UPDATED_STATUS
        defaultFlowTransactionsViewShouldBeFound("status.notEquals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultFlowTransactionsViewShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the flowTransactionsViewList where status equals to UPDATED_STATUS
        defaultFlowTransactionsViewShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where status is not null
        defaultFlowTransactionsViewShouldBeFound("status.specified=true");

        // Get all the flowTransactionsViewList where status is null
        defaultFlowTransactionsViewShouldNotBeFound("status.specified=false");
    }
                @Test
    @Transactional
    public void getAllFlowTransactionsViewsByStatusContainsSomething() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where status contains DEFAULT_STATUS
        defaultFlowTransactionsViewShouldBeFound("status.contains=" + DEFAULT_STATUS);

        // Get all the flowTransactionsViewList where status contains UPDATED_STATUS
        defaultFlowTransactionsViewShouldNotBeFound("status.contains=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllFlowTransactionsViewsByStatusNotContainsSomething() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        // Get all the flowTransactionsViewList where status does not contain DEFAULT_STATUS
        defaultFlowTransactionsViewShouldNotBeFound("status.doesNotContain=" + DEFAULT_STATUS);

        // Get all the flowTransactionsViewList where status does not contain UPDATED_STATUS
        defaultFlowTransactionsViewShouldBeFound("status.doesNotContain=" + UPDATED_STATUS);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultFlowTransactionsViewShouldBeFound(String filter) throws Exception {
        restFlowTransactionsViewMockMvc.perform(get("/api/flow-transactions-views?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(flowTransactionsView.getId().intValue())))
            .andExpect(jsonPath("$.[*].flowId").value(hasItem(DEFAULT_FLOW_ID.toString())))
            .andExpect(jsonPath("$.[*].flowName").value(hasItem(DEFAULT_FLOW_NAME)))
            .andExpect(jsonPath("$.[*].flowType").value(hasItem(DEFAULT_FLOW_TYPE)))
            .andExpect(jsonPath("$.[*].senderIs").value(hasItem(DEFAULT_SENDER_IS)))
            .andExpect(jsonPath("$.[*].senderProtocol").value(hasItem(DEFAULT_SENDER_PROTOCOL)))
            .andExpect(jsonPath("$.[*].sourceInstance").value(hasItem(DEFAULT_SOURCE_INSTANCE)))
            .andExpect(jsonPath("$.[*].receiverIs").value(hasItem(DEFAULT_RECEIVER_IS)))
            .andExpect(jsonPath("$.[*].receiverProtocol").value(hasItem(DEFAULT_RECEIVER_PROTOCOL)))
            .andExpect(jsonPath("$.[*].targetInstance").value(hasItem(DEFAULT_TARGET_INSTANCE)))
            .andExpect(jsonPath("$.[*].filePayload").value(hasItem(DEFAULT_FILE_PAYLOAD)))
            .andExpect(jsonPath("$.[*].flowDateTime").value(hasItem(DEFAULT_FLOW_DATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));

        // Check, that the count call also returns 1
        restFlowTransactionsViewMockMvc.perform(get("/api/flow-transactions-views/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultFlowTransactionsViewShouldNotBeFound(String filter) throws Exception {
        restFlowTransactionsViewMockMvc.perform(get("/api/flow-transactions-views?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restFlowTransactionsViewMockMvc.perform(get("/api/flow-transactions-views/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingFlowTransactionsView() throws Exception {
        // Get the flowTransactionsView
        restFlowTransactionsViewMockMvc.perform(get("/api/flow-transactions-views/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFlowTransactionsView() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        int databaseSizeBeforeUpdate = flowTransactionsViewRepository.findAll().size();

        // Update the flowTransactionsView
        FlowTransactionsView updatedFlowTransactionsView = flowTransactionsViewRepository.findById(flowTransactionsView.getId()).get();
        // Disconnect from session so that the updates on updatedFlowTransactionsView are not directly saved in db
        em.detach(updatedFlowTransactionsView);
        updatedFlowTransactionsView
            .flowId(UPDATED_FLOW_ID)
            .flowName(UPDATED_FLOW_NAME)
            .flowType(UPDATED_FLOW_TYPE)
            .senderIs(UPDATED_SENDER_IS)
            .senderProtocol(UPDATED_SENDER_PROTOCOL)
            .sourceInstance(UPDATED_SOURCE_INSTANCE)
            .receiverIs(UPDATED_RECEIVER_IS)
            .receiverProtocol(UPDATED_RECEIVER_PROTOCOL)
            .targetInstance(UPDATED_TARGET_INSTANCE)
            .filePayload(UPDATED_FILE_PAYLOAD)
            .flowDateTime(UPDATED_FLOW_DATE_TIME)
            .status(UPDATED_STATUS);
        FlowTransactionsViewDTO flowTransactionsViewDTO = flowTransactionsViewMapper.toDto(updatedFlowTransactionsView);

        restFlowTransactionsViewMockMvc.perform(put("/api/flow-transactions-views")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowTransactionsViewDTO)))
            .andExpect(status().isOk());

        // Validate the FlowTransactionsView in the database
        List<FlowTransactionsView> flowTransactionsViewList = flowTransactionsViewRepository.findAll();
        assertThat(flowTransactionsViewList).hasSize(databaseSizeBeforeUpdate);
        FlowTransactionsView testFlowTransactionsView = flowTransactionsViewList.get(flowTransactionsViewList.size() - 1);
        assertThat(testFlowTransactionsView.getFlowId()).isEqualTo(UPDATED_FLOW_ID);
        assertThat(testFlowTransactionsView.getFlowName()).isEqualTo(UPDATED_FLOW_NAME);
        assertThat(testFlowTransactionsView.getFlowType()).isEqualTo(UPDATED_FLOW_TYPE);
        assertThat(testFlowTransactionsView.getSenderIs()).isEqualTo(UPDATED_SENDER_IS);
        assertThat(testFlowTransactionsView.getSenderProtocol()).isEqualTo(UPDATED_SENDER_PROTOCOL);
        assertThat(testFlowTransactionsView.getSourceInstance()).isEqualTo(UPDATED_SOURCE_INSTANCE);
        assertThat(testFlowTransactionsView.getReceiverIs()).isEqualTo(UPDATED_RECEIVER_IS);
        assertThat(testFlowTransactionsView.getReceiverProtocol()).isEqualTo(UPDATED_RECEIVER_PROTOCOL);
        assertThat(testFlowTransactionsView.getTargetInstance()).isEqualTo(UPDATED_TARGET_INSTANCE);
        assertThat(testFlowTransactionsView.getFilePayload()).isEqualTo(UPDATED_FILE_PAYLOAD);
        assertThat(testFlowTransactionsView.getFlowDateTime()).isEqualTo(UPDATED_FLOW_DATE_TIME);
        assertThat(testFlowTransactionsView.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingFlowTransactionsView() throws Exception {
        int databaseSizeBeforeUpdate = flowTransactionsViewRepository.findAll().size();

        // Create the FlowTransactionsView
        FlowTransactionsViewDTO flowTransactionsViewDTO = flowTransactionsViewMapper.toDto(flowTransactionsView);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFlowTransactionsViewMockMvc.perform(put("/api/flow-transactions-views")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowTransactionsViewDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FlowTransactionsView in the database
        List<FlowTransactionsView> flowTransactionsViewList = flowTransactionsViewRepository.findAll();
        assertThat(flowTransactionsViewList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFlowTransactionsView() throws Exception {
        // Initialize the database
        flowTransactionsViewRepository.saveAndFlush(flowTransactionsView);

        int databaseSizeBeforeDelete = flowTransactionsViewRepository.findAll().size();

        // Delete the flowTransactionsView
        restFlowTransactionsViewMockMvc.perform(delete("/api/flow-transactions-views/{id}", flowTransactionsView.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FlowTransactionsView> flowTransactionsViewList = flowTransactionsViewRepository.findAll();
        assertThat(flowTransactionsViewList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
