package com.dav.mediation.web.rest;

import com.dav.mediation.MediationApp;
import com.dav.mediation.domain.FlowStatus;
import com.dav.mediation.repository.FlowStatusRepository;
import com.dav.mediation.service.FlowStatusService;
import com.dav.mediation.service.dto.FlowStatusDTO;
import com.dav.mediation.service.mapper.FlowStatusMapper;

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
 * Integration tests for the {@link FlowStatusResource} REST controller.
 */
@SpringBootTest(classes = MediationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FlowStatusResourceIT {

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

    @Autowired
    private FlowStatusRepository flowStatusRepository;

    @Autowired
    private FlowStatusMapper flowStatusMapper;

    @Autowired
    private FlowStatusService flowStatusService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFlowStatusMockMvc;

    private FlowStatus flowStatus;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FlowStatus createEntity(EntityManager em) {
        FlowStatus flowStatus = new FlowStatus()
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
            .flowDateTime(DEFAULT_FLOW_DATE_TIME);
        return flowStatus;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FlowStatus createUpdatedEntity(EntityManager em) {
        FlowStatus flowStatus = new FlowStatus()
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
            .flowDateTime(UPDATED_FLOW_DATE_TIME);
        return flowStatus;
    }

    @BeforeEach
    public void initTest() {
        flowStatus = createEntity(em);
    }

    @Test
    @Transactional
    public void createFlowStatus() throws Exception {
        int databaseSizeBeforeCreate = flowStatusRepository.findAll().size();
        // Create the FlowStatus
        FlowStatusDTO flowStatusDTO = flowStatusMapper.toDto(flowStatus);
        restFlowStatusMockMvc.perform(post("/api/flow-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowStatusDTO)))
            .andExpect(status().isCreated());

        // Validate the FlowStatus in the database
        List<FlowStatus> flowStatusList = flowStatusRepository.findAll();
        assertThat(flowStatusList).hasSize(databaseSizeBeforeCreate + 1);
        FlowStatus testFlowStatus = flowStatusList.get(flowStatusList.size() - 1);
        assertThat(testFlowStatus.getFlowId()).isEqualTo(DEFAULT_FLOW_ID);
        assertThat(testFlowStatus.getFlowName()).isEqualTo(DEFAULT_FLOW_NAME);
        assertThat(testFlowStatus.getFlowType()).isEqualTo(DEFAULT_FLOW_TYPE);
        assertThat(testFlowStatus.getSenderIs()).isEqualTo(DEFAULT_SENDER_IS);
        assertThat(testFlowStatus.getSenderProtocol()).isEqualTo(DEFAULT_SENDER_PROTOCOL);
        assertThat(testFlowStatus.getSourceInstance()).isEqualTo(DEFAULT_SOURCE_INSTANCE);
        assertThat(testFlowStatus.getReceiverIs()).isEqualTo(DEFAULT_RECEIVER_IS);
        assertThat(testFlowStatus.getReceiverProtocol()).isEqualTo(DEFAULT_RECEIVER_PROTOCOL);
        assertThat(testFlowStatus.getTargetInstance()).isEqualTo(DEFAULT_TARGET_INSTANCE);
        assertThat(testFlowStatus.getFilePayload()).isEqualTo(DEFAULT_FILE_PAYLOAD);
        assertThat(testFlowStatus.getFlowDateTime()).isEqualTo(DEFAULT_FLOW_DATE_TIME);
    }

    @Test
    @Transactional
    public void createFlowStatusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = flowStatusRepository.findAll().size();

        // Create the FlowStatus with an existing ID
        flowStatus.setId(1L);
        FlowStatusDTO flowStatusDTO = flowStatusMapper.toDto(flowStatus);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFlowStatusMockMvc.perform(post("/api/flow-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FlowStatus in the database
        List<FlowStatus> flowStatusList = flowStatusRepository.findAll();
        assertThat(flowStatusList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkFlowIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = flowStatusRepository.findAll().size();
        // set the field null
        flowStatus.setFlowId(null);

        // Create the FlowStatus, which fails.
        FlowStatusDTO flowStatusDTO = flowStatusMapper.toDto(flowStatus);


        restFlowStatusMockMvc.perform(post("/api/flow-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowStatusDTO)))
            .andExpect(status().isBadRequest());

        List<FlowStatus> flowStatusList = flowStatusRepository.findAll();
        assertThat(flowStatusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFlowNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = flowStatusRepository.findAll().size();
        // set the field null
        flowStatus.setFlowName(null);

        // Create the FlowStatus, which fails.
        FlowStatusDTO flowStatusDTO = flowStatusMapper.toDto(flowStatus);


        restFlowStatusMockMvc.perform(post("/api/flow-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowStatusDTO)))
            .andExpect(status().isBadRequest());

        List<FlowStatus> flowStatusList = flowStatusRepository.findAll();
        assertThat(flowStatusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFlowTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = flowStatusRepository.findAll().size();
        // set the field null
        flowStatus.setFlowType(null);

        // Create the FlowStatus, which fails.
        FlowStatusDTO flowStatusDTO = flowStatusMapper.toDto(flowStatus);


        restFlowStatusMockMvc.perform(post("/api/flow-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowStatusDTO)))
            .andExpect(status().isBadRequest());

        List<FlowStatus> flowStatusList = flowStatusRepository.findAll();
        assertThat(flowStatusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSenderIsIsRequired() throws Exception {
        int databaseSizeBeforeTest = flowStatusRepository.findAll().size();
        // set the field null
        flowStatus.setSenderIs(null);

        // Create the FlowStatus, which fails.
        FlowStatusDTO flowStatusDTO = flowStatusMapper.toDto(flowStatus);


        restFlowStatusMockMvc.perform(post("/api/flow-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowStatusDTO)))
            .andExpect(status().isBadRequest());

        List<FlowStatus> flowStatusList = flowStatusRepository.findAll();
        assertThat(flowStatusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSenderProtocolIsRequired() throws Exception {
        int databaseSizeBeforeTest = flowStatusRepository.findAll().size();
        // set the field null
        flowStatus.setSenderProtocol(null);

        // Create the FlowStatus, which fails.
        FlowStatusDTO flowStatusDTO = flowStatusMapper.toDto(flowStatus);


        restFlowStatusMockMvc.perform(post("/api/flow-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowStatusDTO)))
            .andExpect(status().isBadRequest());

        List<FlowStatus> flowStatusList = flowStatusRepository.findAll();
        assertThat(flowStatusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSourceInstanceIsRequired() throws Exception {
        int databaseSizeBeforeTest = flowStatusRepository.findAll().size();
        // set the field null
        flowStatus.setSourceInstance(null);

        // Create the FlowStatus, which fails.
        FlowStatusDTO flowStatusDTO = flowStatusMapper.toDto(flowStatus);


        restFlowStatusMockMvc.perform(post("/api/flow-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowStatusDTO)))
            .andExpect(status().isBadRequest());

        List<FlowStatus> flowStatusList = flowStatusRepository.findAll();
        assertThat(flowStatusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkReceiverIsIsRequired() throws Exception {
        int databaseSizeBeforeTest = flowStatusRepository.findAll().size();
        // set the field null
        flowStatus.setReceiverIs(null);

        // Create the FlowStatus, which fails.
        FlowStatusDTO flowStatusDTO = flowStatusMapper.toDto(flowStatus);


        restFlowStatusMockMvc.perform(post("/api/flow-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowStatusDTO)))
            .andExpect(status().isBadRequest());

        List<FlowStatus> flowStatusList = flowStatusRepository.findAll();
        assertThat(flowStatusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkReceiverProtocolIsRequired() throws Exception {
        int databaseSizeBeforeTest = flowStatusRepository.findAll().size();
        // set the field null
        flowStatus.setReceiverProtocol(null);

        // Create the FlowStatus, which fails.
        FlowStatusDTO flowStatusDTO = flowStatusMapper.toDto(flowStatus);


        restFlowStatusMockMvc.perform(post("/api/flow-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowStatusDTO)))
            .andExpect(status().isBadRequest());

        List<FlowStatus> flowStatusList = flowStatusRepository.findAll();
        assertThat(flowStatusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTargetInstanceIsRequired() throws Exception {
        int databaseSizeBeforeTest = flowStatusRepository.findAll().size();
        // set the field null
        flowStatus.setTargetInstance(null);

        // Create the FlowStatus, which fails.
        FlowStatusDTO flowStatusDTO = flowStatusMapper.toDto(flowStatus);


        restFlowStatusMockMvc.perform(post("/api/flow-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowStatusDTO)))
            .andExpect(status().isBadRequest());

        List<FlowStatus> flowStatusList = flowStatusRepository.findAll();
        assertThat(flowStatusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFlowDateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = flowStatusRepository.findAll().size();
        // set the field null
        flowStatus.setFlowDateTime(null);

        // Create the FlowStatus, which fails.
        FlowStatusDTO flowStatusDTO = flowStatusMapper.toDto(flowStatus);


        restFlowStatusMockMvc.perform(post("/api/flow-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowStatusDTO)))
            .andExpect(status().isBadRequest());

        List<FlowStatus> flowStatusList = flowStatusRepository.findAll();
        assertThat(flowStatusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFlowStatuses() throws Exception {
        // Initialize the database
        flowStatusRepository.saveAndFlush(flowStatus);

        // Get all the flowStatusList
        restFlowStatusMockMvc.perform(get("/api/flow-statuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(flowStatus.getId().intValue())))
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
            .andExpect(jsonPath("$.[*].flowDateTime").value(hasItem(DEFAULT_FLOW_DATE_TIME.toString())));
    }
    
    @Test
    @Transactional
    public void getFlowStatus() throws Exception {
        // Initialize the database
        flowStatusRepository.saveAndFlush(flowStatus);

        // Get the flowStatus
        restFlowStatusMockMvc.perform(get("/api/flow-statuses/{id}", flowStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(flowStatus.getId().intValue()))
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
            .andExpect(jsonPath("$.flowDateTime").value(DEFAULT_FLOW_DATE_TIME.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingFlowStatus() throws Exception {
        // Get the flowStatus
        restFlowStatusMockMvc.perform(get("/api/flow-statuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFlowStatus() throws Exception {
        // Initialize the database
        flowStatusRepository.saveAndFlush(flowStatus);

        int databaseSizeBeforeUpdate = flowStatusRepository.findAll().size();

        // Update the flowStatus
        FlowStatus updatedFlowStatus = flowStatusRepository.findById(flowStatus.getId()).get();
        // Disconnect from session so that the updates on updatedFlowStatus are not directly saved in db
        em.detach(updatedFlowStatus);
        updatedFlowStatus
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
            .flowDateTime(UPDATED_FLOW_DATE_TIME);
        FlowStatusDTO flowStatusDTO = flowStatusMapper.toDto(updatedFlowStatus);

        restFlowStatusMockMvc.perform(put("/api/flow-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowStatusDTO)))
            .andExpect(status().isOk());

        // Validate the FlowStatus in the database
        List<FlowStatus> flowStatusList = flowStatusRepository.findAll();
        assertThat(flowStatusList).hasSize(databaseSizeBeforeUpdate);
        FlowStatus testFlowStatus = flowStatusList.get(flowStatusList.size() - 1);
        assertThat(testFlowStatus.getFlowId()).isEqualTo(UPDATED_FLOW_ID);
        assertThat(testFlowStatus.getFlowName()).isEqualTo(UPDATED_FLOW_NAME);
        assertThat(testFlowStatus.getFlowType()).isEqualTo(UPDATED_FLOW_TYPE);
        assertThat(testFlowStatus.getSenderIs()).isEqualTo(UPDATED_SENDER_IS);
        assertThat(testFlowStatus.getSenderProtocol()).isEqualTo(UPDATED_SENDER_PROTOCOL);
        assertThat(testFlowStatus.getSourceInstance()).isEqualTo(UPDATED_SOURCE_INSTANCE);
        assertThat(testFlowStatus.getReceiverIs()).isEqualTo(UPDATED_RECEIVER_IS);
        assertThat(testFlowStatus.getReceiverProtocol()).isEqualTo(UPDATED_RECEIVER_PROTOCOL);
        assertThat(testFlowStatus.getTargetInstance()).isEqualTo(UPDATED_TARGET_INSTANCE);
        assertThat(testFlowStatus.getFilePayload()).isEqualTo(UPDATED_FILE_PAYLOAD);
        assertThat(testFlowStatus.getFlowDateTime()).isEqualTo(UPDATED_FLOW_DATE_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingFlowStatus() throws Exception {
        int databaseSizeBeforeUpdate = flowStatusRepository.findAll().size();

        // Create the FlowStatus
        FlowStatusDTO flowStatusDTO = flowStatusMapper.toDto(flowStatus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFlowStatusMockMvc.perform(put("/api/flow-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FlowStatus in the database
        List<FlowStatus> flowStatusList = flowStatusRepository.findAll();
        assertThat(flowStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFlowStatus() throws Exception {
        // Initialize the database
        flowStatusRepository.saveAndFlush(flowStatus);

        int databaseSizeBeforeDelete = flowStatusRepository.findAll().size();

        // Delete the flowStatus
        restFlowStatusMockMvc.perform(delete("/api/flow-statuses/{id}", flowStatus.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FlowStatus> flowStatusList = flowStatusRepository.findAll();
        assertThat(flowStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
