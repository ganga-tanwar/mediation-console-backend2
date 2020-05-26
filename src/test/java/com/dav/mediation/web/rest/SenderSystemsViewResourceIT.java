package com.dav.mediation.web.rest;

import com.dav.mediation.MediationApp;
import com.dav.mediation.domain.SenderSystemsView;
import com.dav.mediation.repository.SenderSystemsViewRepository;
import com.dav.mediation.service.SenderSystemsViewService;
import com.dav.mediation.service.dto.SenderSystemsViewDTO;
import com.dav.mediation.service.mapper.SenderSystemsViewMapper;

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
 * Integration tests for the {@link SenderSystemsViewResource} REST controller.
 */
@SpringBootTest(classes = MediationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SenderSystemsViewResourceIT {

    private static final String DEFAULT_SENDER_IS = "AAAAAAAAAA";
    private static final String UPDATED_SENDER_IS = "BBBBBBBBBB";

    @Autowired
    private SenderSystemsViewRepository senderSystemsViewRepository;

    @Autowired
    private SenderSystemsViewMapper senderSystemsViewMapper;

    @Autowired
    private SenderSystemsViewService senderSystemsViewService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSenderSystemsViewMockMvc;

    private SenderSystemsView senderSystemsView;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SenderSystemsView createEntity(EntityManager em) {
        SenderSystemsView senderSystemsView = new SenderSystemsView()
            .senderIs(DEFAULT_SENDER_IS);
        return senderSystemsView;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SenderSystemsView createUpdatedEntity(EntityManager em) {
        SenderSystemsView senderSystemsView = new SenderSystemsView()
            .senderIs(UPDATED_SENDER_IS);
        return senderSystemsView;
    }

    @BeforeEach
    public void initTest() {
        senderSystemsView = createEntity(em);
    }

    @Test
    @Transactional
    public void createSenderSystemsView() throws Exception {
        int databaseSizeBeforeCreate = senderSystemsViewRepository.findAll().size();
        // Create the SenderSystemsView
        SenderSystemsViewDTO senderSystemsViewDTO = senderSystemsViewMapper.toDto(senderSystemsView);
        restSenderSystemsViewMockMvc.perform(post("/api/sender-systems-views")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(senderSystemsViewDTO)))
            .andExpect(status().isCreated());

        // Validate the SenderSystemsView in the database
        List<SenderSystemsView> senderSystemsViewList = senderSystemsViewRepository.findAll();
        assertThat(senderSystemsViewList).hasSize(databaseSizeBeforeCreate + 1);
        SenderSystemsView testSenderSystemsView = senderSystemsViewList.get(senderSystemsViewList.size() - 1);
        assertThat(testSenderSystemsView.getSenderIs()).isEqualTo(DEFAULT_SENDER_IS);
    }

    @Test
    @Transactional
    public void createSenderSystemsViewWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = senderSystemsViewRepository.findAll().size();

        // Create the SenderSystemsView with an existing ID
        senderSystemsView.setId(1L);
        SenderSystemsViewDTO senderSystemsViewDTO = senderSystemsViewMapper.toDto(senderSystemsView);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSenderSystemsViewMockMvc.perform(post("/api/sender-systems-views")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(senderSystemsViewDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SenderSystemsView in the database
        List<SenderSystemsView> senderSystemsViewList = senderSystemsViewRepository.findAll();
        assertThat(senderSystemsViewList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSenderIsIsRequired() throws Exception {
        int databaseSizeBeforeTest = senderSystemsViewRepository.findAll().size();
        // set the field null
        senderSystemsView.setSenderIs(null);

        // Create the SenderSystemsView, which fails.
        SenderSystemsViewDTO senderSystemsViewDTO = senderSystemsViewMapper.toDto(senderSystemsView);


        restSenderSystemsViewMockMvc.perform(post("/api/sender-systems-views")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(senderSystemsViewDTO)))
            .andExpect(status().isBadRequest());

        List<SenderSystemsView> senderSystemsViewList = senderSystemsViewRepository.findAll();
        assertThat(senderSystemsViewList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSenderSystemsViews() throws Exception {
        // Initialize the database
        senderSystemsViewRepository.saveAndFlush(senderSystemsView);

        // Get all the senderSystemsViewList
        restSenderSystemsViewMockMvc.perform(get("/api/sender-systems-views?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(senderSystemsView.getId().intValue())))
            .andExpect(jsonPath("$.[*].senderIs").value(hasItem(DEFAULT_SENDER_IS)));
    }
    
    @Test
    @Transactional
    public void getSenderSystemsView() throws Exception {
        // Initialize the database
        senderSystemsViewRepository.saveAndFlush(senderSystemsView);

        // Get the senderSystemsView
        restSenderSystemsViewMockMvc.perform(get("/api/sender-systems-views/{id}", senderSystemsView.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(senderSystemsView.getId().intValue()))
            .andExpect(jsonPath("$.senderIs").value(DEFAULT_SENDER_IS));
    }
    @Test
    @Transactional
    public void getNonExistingSenderSystemsView() throws Exception {
        // Get the senderSystemsView
        restSenderSystemsViewMockMvc.perform(get("/api/sender-systems-views/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSenderSystemsView() throws Exception {
        // Initialize the database
        senderSystemsViewRepository.saveAndFlush(senderSystemsView);

        int databaseSizeBeforeUpdate = senderSystemsViewRepository.findAll().size();

        // Update the senderSystemsView
        SenderSystemsView updatedSenderSystemsView = senderSystemsViewRepository.findById(senderSystemsView.getId()).get();
        // Disconnect from session so that the updates on updatedSenderSystemsView are not directly saved in db
        em.detach(updatedSenderSystemsView);
        updatedSenderSystemsView
            .senderIs(UPDATED_SENDER_IS);
        SenderSystemsViewDTO senderSystemsViewDTO = senderSystemsViewMapper.toDto(updatedSenderSystemsView);

        restSenderSystemsViewMockMvc.perform(put("/api/sender-systems-views")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(senderSystemsViewDTO)))
            .andExpect(status().isOk());

        // Validate the SenderSystemsView in the database
        List<SenderSystemsView> senderSystemsViewList = senderSystemsViewRepository.findAll();
        assertThat(senderSystemsViewList).hasSize(databaseSizeBeforeUpdate);
        SenderSystemsView testSenderSystemsView = senderSystemsViewList.get(senderSystemsViewList.size() - 1);
        assertThat(testSenderSystemsView.getSenderIs()).isEqualTo(UPDATED_SENDER_IS);
    }

    @Test
    @Transactional
    public void updateNonExistingSenderSystemsView() throws Exception {
        int databaseSizeBeforeUpdate = senderSystemsViewRepository.findAll().size();

        // Create the SenderSystemsView
        SenderSystemsViewDTO senderSystemsViewDTO = senderSystemsViewMapper.toDto(senderSystemsView);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSenderSystemsViewMockMvc.perform(put("/api/sender-systems-views")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(senderSystemsViewDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SenderSystemsView in the database
        List<SenderSystemsView> senderSystemsViewList = senderSystemsViewRepository.findAll();
        assertThat(senderSystemsViewList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSenderSystemsView() throws Exception {
        // Initialize the database
        senderSystemsViewRepository.saveAndFlush(senderSystemsView);

        int databaseSizeBeforeDelete = senderSystemsViewRepository.findAll().size();

        // Delete the senderSystemsView
        restSenderSystemsViewMockMvc.perform(delete("/api/sender-systems-views/{id}", senderSystemsView.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SenderSystemsView> senderSystemsViewList = senderSystemsViewRepository.findAll();
        assertThat(senderSystemsViewList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
