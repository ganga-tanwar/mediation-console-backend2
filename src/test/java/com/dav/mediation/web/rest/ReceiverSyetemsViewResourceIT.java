package com.dav.mediation.web.rest;

import com.dav.mediation.MediationApp;
import com.dav.mediation.domain.ReceiverSyetemsView;
import com.dav.mediation.repository.ReceiverSyetemsViewRepository;
import com.dav.mediation.service.ReceiverSyetemsViewService;
import com.dav.mediation.service.dto.ReceiverSyetemsViewDTO;
import com.dav.mediation.service.mapper.ReceiverSyetemsViewMapper;

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
 * Integration tests for the {@link ReceiverSyetemsViewResource} REST controller.
 */
@SpringBootTest(classes = MediationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ReceiverSyetemsViewResourceIT {

    private static final String DEFAULT_RECEIVER_IS = "AAAAAAAAAA";
    private static final String UPDATED_RECEIVER_IS = "BBBBBBBBBB";

    @Autowired
    private ReceiverSyetemsViewRepository receiverSyetemsViewRepository;

    @Autowired
    private ReceiverSyetemsViewMapper receiverSyetemsViewMapper;

    @Autowired
    private ReceiverSyetemsViewService receiverSyetemsViewService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restReceiverSyetemsViewMockMvc;

    private ReceiverSyetemsView receiverSyetemsView;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ReceiverSyetemsView createEntity(EntityManager em) {
        ReceiverSyetemsView receiverSyetemsView = new ReceiverSyetemsView()
            .receiverIs(DEFAULT_RECEIVER_IS);
        return receiverSyetemsView;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ReceiverSyetemsView createUpdatedEntity(EntityManager em) {
        ReceiverSyetemsView receiverSyetemsView = new ReceiverSyetemsView()
            .receiverIs(UPDATED_RECEIVER_IS);
        return receiverSyetemsView;
    }

    @BeforeEach
    public void initTest() {
        receiverSyetemsView = createEntity(em);
    }

    @Test
    @Transactional
    public void createReceiverSyetemsView() throws Exception {
        int databaseSizeBeforeCreate = receiverSyetemsViewRepository.findAll().size();
        // Create the ReceiverSyetemsView
        ReceiverSyetemsViewDTO receiverSyetemsViewDTO = receiverSyetemsViewMapper.toDto(receiverSyetemsView);
        restReceiverSyetemsViewMockMvc.perform(post("/api/receiver-syetems-views")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(receiverSyetemsViewDTO)))
            .andExpect(status().isCreated());

        // Validate the ReceiverSyetemsView in the database
        List<ReceiverSyetemsView> receiverSyetemsViewList = receiverSyetemsViewRepository.findAll();
        assertThat(receiverSyetemsViewList).hasSize(databaseSizeBeforeCreate + 1);
        ReceiverSyetemsView testReceiverSyetemsView = receiverSyetemsViewList.get(receiverSyetemsViewList.size() - 1);
        assertThat(testReceiverSyetemsView.getReceiverIs()).isEqualTo(DEFAULT_RECEIVER_IS);
    }

    @Test
    @Transactional
    public void createReceiverSyetemsViewWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = receiverSyetemsViewRepository.findAll().size();

        // Create the ReceiverSyetemsView with an existing ID
        receiverSyetemsView.setId(1L);
        ReceiverSyetemsViewDTO receiverSyetemsViewDTO = receiverSyetemsViewMapper.toDto(receiverSyetemsView);

        // An entity with an existing ID cannot be created, so this API call must fail
        restReceiverSyetemsViewMockMvc.perform(post("/api/receiver-syetems-views")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(receiverSyetemsViewDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ReceiverSyetemsView in the database
        List<ReceiverSyetemsView> receiverSyetemsViewList = receiverSyetemsViewRepository.findAll();
        assertThat(receiverSyetemsViewList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkReceiverIsIsRequired() throws Exception {
        int databaseSizeBeforeTest = receiverSyetemsViewRepository.findAll().size();
        // set the field null
        receiverSyetemsView.setReceiverIs(null);

        // Create the ReceiverSyetemsView, which fails.
        ReceiverSyetemsViewDTO receiverSyetemsViewDTO = receiverSyetemsViewMapper.toDto(receiverSyetemsView);


        restReceiverSyetemsViewMockMvc.perform(post("/api/receiver-syetems-views")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(receiverSyetemsViewDTO)))
            .andExpect(status().isBadRequest());

        List<ReceiverSyetemsView> receiverSyetemsViewList = receiverSyetemsViewRepository.findAll();
        assertThat(receiverSyetemsViewList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllReceiverSyetemsViews() throws Exception {
        // Initialize the database
        receiverSyetemsViewRepository.saveAndFlush(receiverSyetemsView);

        // Get all the receiverSyetemsViewList
        restReceiverSyetemsViewMockMvc.perform(get("/api/receiver-syetems-views?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(receiverSyetemsView.getId().intValue())))
            .andExpect(jsonPath("$.[*].receiverIs").value(hasItem(DEFAULT_RECEIVER_IS)));
    }
    
    @Test
    @Transactional
    public void getReceiverSyetemsView() throws Exception {
        // Initialize the database
        receiverSyetemsViewRepository.saveAndFlush(receiverSyetemsView);

        // Get the receiverSyetemsView
        restReceiverSyetemsViewMockMvc.perform(get("/api/receiver-syetems-views/{id}", receiverSyetemsView.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(receiverSyetemsView.getId().intValue()))
            .andExpect(jsonPath("$.receiverIs").value(DEFAULT_RECEIVER_IS));
    }
    @Test
    @Transactional
    public void getNonExistingReceiverSyetemsView() throws Exception {
        // Get the receiverSyetemsView
        restReceiverSyetemsViewMockMvc.perform(get("/api/receiver-syetems-views/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateReceiverSyetemsView() throws Exception {
        // Initialize the database
        receiverSyetemsViewRepository.saveAndFlush(receiverSyetemsView);

        int databaseSizeBeforeUpdate = receiverSyetemsViewRepository.findAll().size();

        // Update the receiverSyetemsView
        ReceiverSyetemsView updatedReceiverSyetemsView = receiverSyetemsViewRepository.findById(receiverSyetemsView.getId()).get();
        // Disconnect from session so that the updates on updatedReceiverSyetemsView are not directly saved in db
        em.detach(updatedReceiverSyetemsView);
        updatedReceiverSyetemsView
            .receiverIs(UPDATED_RECEIVER_IS);
        ReceiverSyetemsViewDTO receiverSyetemsViewDTO = receiverSyetemsViewMapper.toDto(updatedReceiverSyetemsView);

        restReceiverSyetemsViewMockMvc.perform(put("/api/receiver-syetems-views")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(receiverSyetemsViewDTO)))
            .andExpect(status().isOk());

        // Validate the ReceiverSyetemsView in the database
        List<ReceiverSyetemsView> receiverSyetemsViewList = receiverSyetemsViewRepository.findAll();
        assertThat(receiverSyetemsViewList).hasSize(databaseSizeBeforeUpdate);
        ReceiverSyetemsView testReceiverSyetemsView = receiverSyetemsViewList.get(receiverSyetemsViewList.size() - 1);
        assertThat(testReceiverSyetemsView.getReceiverIs()).isEqualTo(UPDATED_RECEIVER_IS);
    }

    @Test
    @Transactional
    public void updateNonExistingReceiverSyetemsView() throws Exception {
        int databaseSizeBeforeUpdate = receiverSyetemsViewRepository.findAll().size();

        // Create the ReceiverSyetemsView
        ReceiverSyetemsViewDTO receiverSyetemsViewDTO = receiverSyetemsViewMapper.toDto(receiverSyetemsView);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReceiverSyetemsViewMockMvc.perform(put("/api/receiver-syetems-views")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(receiverSyetemsViewDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ReceiverSyetemsView in the database
        List<ReceiverSyetemsView> receiverSyetemsViewList = receiverSyetemsViewRepository.findAll();
        assertThat(receiverSyetemsViewList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteReceiverSyetemsView() throws Exception {
        // Initialize the database
        receiverSyetemsViewRepository.saveAndFlush(receiverSyetemsView);

        int databaseSizeBeforeDelete = receiverSyetemsViewRepository.findAll().size();

        // Delete the receiverSyetemsView
        restReceiverSyetemsViewMockMvc.perform(delete("/api/receiver-syetems-views/{id}", receiverSyetemsView.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ReceiverSyetemsView> receiverSyetemsViewList = receiverSyetemsViewRepository.findAll();
        assertThat(receiverSyetemsViewList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
