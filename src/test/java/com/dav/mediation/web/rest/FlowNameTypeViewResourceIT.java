package com.dav.mediation.web.rest;

import com.dav.mediation.MediationApp;
import com.dav.mediation.domain.FlowNameTypeView;
import com.dav.mediation.repository.FlowNameTypeViewRepository;
import com.dav.mediation.service.FlowNameTypeViewService;
import com.dav.mediation.service.dto.FlowNameTypeViewDTO;
import com.dav.mediation.service.mapper.FlowNameTypeViewMapper;

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
 * Integration tests for the {@link FlowNameTypeViewResource} REST controller.
 */
@SpringBootTest(classes = MediationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FlowNameTypeViewResourceIT {

    private static final String DEFAULT_FLOW_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FLOW_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FLOW_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_FLOW_TYPE = "BBBBBBBBBB";

    @Autowired
    private FlowNameTypeViewRepository flowNameTypeViewRepository;

    @Autowired
    private FlowNameTypeViewMapper flowNameTypeViewMapper;

    @Autowired
    private FlowNameTypeViewService flowNameTypeViewService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFlowNameTypeViewMockMvc;

    private FlowNameTypeView flowNameTypeView;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FlowNameTypeView createEntity(EntityManager em) {
        FlowNameTypeView flowNameTypeView = new FlowNameTypeView()
            .flowName(DEFAULT_FLOW_NAME)
            .flowType(DEFAULT_FLOW_TYPE);
        return flowNameTypeView;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FlowNameTypeView createUpdatedEntity(EntityManager em) {
        FlowNameTypeView flowNameTypeView = new FlowNameTypeView()
            .flowName(UPDATED_FLOW_NAME)
            .flowType(UPDATED_FLOW_TYPE);
        return flowNameTypeView;
    }

    @BeforeEach
    public void initTest() {
        flowNameTypeView = createEntity(em);
    }

    @Test
    @Transactional
    public void createFlowNameTypeView() throws Exception {
        int databaseSizeBeforeCreate = flowNameTypeViewRepository.findAll().size();
        // Create the FlowNameTypeView
        FlowNameTypeViewDTO flowNameTypeViewDTO = flowNameTypeViewMapper.toDto(flowNameTypeView);
        restFlowNameTypeViewMockMvc.perform(post("/api/flow-name-type-views")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowNameTypeViewDTO)))
            .andExpect(status().isCreated());

        // Validate the FlowNameTypeView in the database
        List<FlowNameTypeView> flowNameTypeViewList = flowNameTypeViewRepository.findAll();
        assertThat(flowNameTypeViewList).hasSize(databaseSizeBeforeCreate + 1);
        FlowNameTypeView testFlowNameTypeView = flowNameTypeViewList.get(flowNameTypeViewList.size() - 1);
        assertThat(testFlowNameTypeView.getFlowName()).isEqualTo(DEFAULT_FLOW_NAME);
        assertThat(testFlowNameTypeView.getFlowType()).isEqualTo(DEFAULT_FLOW_TYPE);
    }

    @Test
    @Transactional
    public void createFlowNameTypeViewWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = flowNameTypeViewRepository.findAll().size();

        // Create the FlowNameTypeView with an existing ID
        flowNameTypeView.setId(1L);
        FlowNameTypeViewDTO flowNameTypeViewDTO = flowNameTypeViewMapper.toDto(flowNameTypeView);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFlowNameTypeViewMockMvc.perform(post("/api/flow-name-type-views")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowNameTypeViewDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FlowNameTypeView in the database
        List<FlowNameTypeView> flowNameTypeViewList = flowNameTypeViewRepository.findAll();
        assertThat(flowNameTypeViewList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkFlowNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = flowNameTypeViewRepository.findAll().size();
        // set the field null
        flowNameTypeView.setFlowName(null);

        // Create the FlowNameTypeView, which fails.
        FlowNameTypeViewDTO flowNameTypeViewDTO = flowNameTypeViewMapper.toDto(flowNameTypeView);


        restFlowNameTypeViewMockMvc.perform(post("/api/flow-name-type-views")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowNameTypeViewDTO)))
            .andExpect(status().isBadRequest());

        List<FlowNameTypeView> flowNameTypeViewList = flowNameTypeViewRepository.findAll();
        assertThat(flowNameTypeViewList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFlowTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = flowNameTypeViewRepository.findAll().size();
        // set the field null
        flowNameTypeView.setFlowType(null);

        // Create the FlowNameTypeView, which fails.
        FlowNameTypeViewDTO flowNameTypeViewDTO = flowNameTypeViewMapper.toDto(flowNameTypeView);


        restFlowNameTypeViewMockMvc.perform(post("/api/flow-name-type-views")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowNameTypeViewDTO)))
            .andExpect(status().isBadRequest());

        List<FlowNameTypeView> flowNameTypeViewList = flowNameTypeViewRepository.findAll();
        assertThat(flowNameTypeViewList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFlowNameTypeViews() throws Exception {
        // Initialize the database
        flowNameTypeViewRepository.saveAndFlush(flowNameTypeView);

        // Get all the flowNameTypeViewList
        restFlowNameTypeViewMockMvc.perform(get("/api/flow-name-type-views?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(flowNameTypeView.getId().intValue())))
            .andExpect(jsonPath("$.[*].flowName").value(hasItem(DEFAULT_FLOW_NAME)))
            .andExpect(jsonPath("$.[*].flowType").value(hasItem(DEFAULT_FLOW_TYPE)));
    }
    
    @Test
    @Transactional
    public void getFlowNameTypeView() throws Exception {
        // Initialize the database
        flowNameTypeViewRepository.saveAndFlush(flowNameTypeView);

        // Get the flowNameTypeView
        restFlowNameTypeViewMockMvc.perform(get("/api/flow-name-type-views/{id}", flowNameTypeView.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(flowNameTypeView.getId().intValue()))
            .andExpect(jsonPath("$.flowName").value(DEFAULT_FLOW_NAME))
            .andExpect(jsonPath("$.flowType").value(DEFAULT_FLOW_TYPE));
    }
    @Test
    @Transactional
    public void getNonExistingFlowNameTypeView() throws Exception {
        // Get the flowNameTypeView
        restFlowNameTypeViewMockMvc.perform(get("/api/flow-name-type-views/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFlowNameTypeView() throws Exception {
        // Initialize the database
        flowNameTypeViewRepository.saveAndFlush(flowNameTypeView);

        int databaseSizeBeforeUpdate = flowNameTypeViewRepository.findAll().size();

        // Update the flowNameTypeView
        FlowNameTypeView updatedFlowNameTypeView = flowNameTypeViewRepository.findById(flowNameTypeView.getId()).get();
        // Disconnect from session so that the updates on updatedFlowNameTypeView are not directly saved in db
        em.detach(updatedFlowNameTypeView);
        updatedFlowNameTypeView
            .flowName(UPDATED_FLOW_NAME)
            .flowType(UPDATED_FLOW_TYPE);
        FlowNameTypeViewDTO flowNameTypeViewDTO = flowNameTypeViewMapper.toDto(updatedFlowNameTypeView);

        restFlowNameTypeViewMockMvc.perform(put("/api/flow-name-type-views")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowNameTypeViewDTO)))
            .andExpect(status().isOk());

        // Validate the FlowNameTypeView in the database
        List<FlowNameTypeView> flowNameTypeViewList = flowNameTypeViewRepository.findAll();
        assertThat(flowNameTypeViewList).hasSize(databaseSizeBeforeUpdate);
        FlowNameTypeView testFlowNameTypeView = flowNameTypeViewList.get(flowNameTypeViewList.size() - 1);
        assertThat(testFlowNameTypeView.getFlowName()).isEqualTo(UPDATED_FLOW_NAME);
        assertThat(testFlowNameTypeView.getFlowType()).isEqualTo(UPDATED_FLOW_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingFlowNameTypeView() throws Exception {
        int databaseSizeBeforeUpdate = flowNameTypeViewRepository.findAll().size();

        // Create the FlowNameTypeView
        FlowNameTypeViewDTO flowNameTypeViewDTO = flowNameTypeViewMapper.toDto(flowNameTypeView);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFlowNameTypeViewMockMvc.perform(put("/api/flow-name-type-views")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flowNameTypeViewDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FlowNameTypeView in the database
        List<FlowNameTypeView> flowNameTypeViewList = flowNameTypeViewRepository.findAll();
        assertThat(flowNameTypeViewList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFlowNameTypeView() throws Exception {
        // Initialize the database
        flowNameTypeViewRepository.saveAndFlush(flowNameTypeView);

        int databaseSizeBeforeDelete = flowNameTypeViewRepository.findAll().size();

        // Delete the flowNameTypeView
        restFlowNameTypeViewMockMvc.perform(delete("/api/flow-name-type-views/{id}", flowNameTypeView.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FlowNameTypeView> flowNameTypeViewList = flowNameTypeViewRepository.findAll();
        assertThat(flowNameTypeViewList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
