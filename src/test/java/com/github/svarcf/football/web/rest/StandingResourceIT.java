package com.github.svarcf.football.web.rest;

import com.github.svarcf.football.FootballAggregationApp;
import com.github.svarcf.football.config.SecurityBeanOverrideConfiguration;
import com.github.svarcf.football.domain.Standing;
import com.github.svarcf.football.repository.StandingRepository;
import com.github.svarcf.football.service.StandingService;
import com.github.svarcf.football.service.dto.StandingDTO;
import com.github.svarcf.football.service.mapper.StandingMapper;
import com.github.svarcf.football.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.github.svarcf.football.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link StandingResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, FootballAggregationApp.class})
public class StandingResourceIT {

    private static final Integer DEFAULT_POSITION = 1;
    private static final Integer UPDATED_POSITION = 2;

    private static final Integer DEFAULT_WON = 1;
    private static final Integer UPDATED_WON = 2;

    private static final Integer DEFAULT_DRAW = 1;
    private static final Integer UPDATED_DRAW = 2;

    private static final Integer DEFAULT_LOST = 1;
    private static final Integer UPDATED_LOST = 2;

    private static final Integer DEFAULT_POINTS = 1;
    private static final Integer UPDATED_POINTS = 2;

    @Autowired
    private StandingRepository standingRepository;

    @Autowired
    private StandingMapper standingMapper;

    @Autowired
    private StandingService standingService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restStandingMockMvc;

    private Standing standing;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StandingResource standingResource = new StandingResource(standingService);
        this.restStandingMockMvc = MockMvcBuilders.standaloneSetup(standingResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Standing createEntity(EntityManager em) {
        Standing standing = new Standing()
            .position(DEFAULT_POSITION)
            .won(DEFAULT_WON)
            .draw(DEFAULT_DRAW)
            .lost(DEFAULT_LOST)
            .points(DEFAULT_POINTS);
        return standing;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Standing createUpdatedEntity(EntityManager em) {
        Standing standing = new Standing()
            .position(UPDATED_POSITION)
            .won(UPDATED_WON)
            .draw(UPDATED_DRAW)
            .lost(UPDATED_LOST)
            .points(UPDATED_POINTS);
        return standing;
    }

    @BeforeEach
    public void initTest() {
        standing = createEntity(em);
    }

    @Test
    @Transactional
    public void createStanding() throws Exception {
        int databaseSizeBeforeCreate = standingRepository.findAll().size();

        // Create the Standing
        StandingDTO standingDTO = standingMapper.toDto(standing);
        restStandingMockMvc.perform(post("/api/standings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(standingDTO)))
            .andExpect(status().isCreated());

        // Validate the Standing in the database
        List<Standing> standingList = standingRepository.findAll();
        assertThat(standingList).hasSize(databaseSizeBeforeCreate + 1);
        Standing testStanding = standingList.get(standingList.size() - 1);
        assertThat(testStanding.getPosition()).isEqualTo(DEFAULT_POSITION);
        assertThat(testStanding.getWon()).isEqualTo(DEFAULT_WON);
        assertThat(testStanding.getDraw()).isEqualTo(DEFAULT_DRAW);
        assertThat(testStanding.getLost()).isEqualTo(DEFAULT_LOST);
        assertThat(testStanding.getPoints()).isEqualTo(DEFAULT_POINTS);
    }

    @Test
    @Transactional
    public void createStandingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = standingRepository.findAll().size();

        // Create the Standing with an existing ID
        standing.setId(1L);
        StandingDTO standingDTO = standingMapper.toDto(standing);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStandingMockMvc.perform(post("/api/standings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(standingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Standing in the database
        List<Standing> standingList = standingRepository.findAll();
        assertThat(standingList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkPositionIsRequired() throws Exception {
        int databaseSizeBeforeTest = standingRepository.findAll().size();
        // set the field null
        standing.setPosition(null);

        // Create the Standing, which fails.
        StandingDTO standingDTO = standingMapper.toDto(standing);

        restStandingMockMvc.perform(post("/api/standings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(standingDTO)))
            .andExpect(status().isBadRequest());

        List<Standing> standingList = standingRepository.findAll();
        assertThat(standingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWonIsRequired() throws Exception {
        int databaseSizeBeforeTest = standingRepository.findAll().size();
        // set the field null
        standing.setWon(null);

        // Create the Standing, which fails.
        StandingDTO standingDTO = standingMapper.toDto(standing);

        restStandingMockMvc.perform(post("/api/standings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(standingDTO)))
            .andExpect(status().isBadRequest());

        List<Standing> standingList = standingRepository.findAll();
        assertThat(standingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDrawIsRequired() throws Exception {
        int databaseSizeBeforeTest = standingRepository.findAll().size();
        // set the field null
        standing.setDraw(null);

        // Create the Standing, which fails.
        StandingDTO standingDTO = standingMapper.toDto(standing);

        restStandingMockMvc.perform(post("/api/standings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(standingDTO)))
            .andExpect(status().isBadRequest());

        List<Standing> standingList = standingRepository.findAll();
        assertThat(standingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLostIsRequired() throws Exception {
        int databaseSizeBeforeTest = standingRepository.findAll().size();
        // set the field null
        standing.setLost(null);

        // Create the Standing, which fails.
        StandingDTO standingDTO = standingMapper.toDto(standing);

        restStandingMockMvc.perform(post("/api/standings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(standingDTO)))
            .andExpect(status().isBadRequest());

        List<Standing> standingList = standingRepository.findAll();
        assertThat(standingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPointsIsRequired() throws Exception {
        int databaseSizeBeforeTest = standingRepository.findAll().size();
        // set the field null
        standing.setPoints(null);

        // Create the Standing, which fails.
        StandingDTO standingDTO = standingMapper.toDto(standing);

        restStandingMockMvc.perform(post("/api/standings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(standingDTO)))
            .andExpect(status().isBadRequest());

        List<Standing> standingList = standingRepository.findAll();
        assertThat(standingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllStandings() throws Exception {
        // Initialize the database
        standingRepository.saveAndFlush(standing);

        // Get all the standingList
        restStandingMockMvc.perform(get("/api/standings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(standing.getId().intValue())))
            .andExpect(jsonPath("$.[*].position").value(hasItem(DEFAULT_POSITION)))
            .andExpect(jsonPath("$.[*].won").value(hasItem(DEFAULT_WON)))
            .andExpect(jsonPath("$.[*].draw").value(hasItem(DEFAULT_DRAW)))
            .andExpect(jsonPath("$.[*].lost").value(hasItem(DEFAULT_LOST)))
            .andExpect(jsonPath("$.[*].points").value(hasItem(DEFAULT_POINTS)));
    }
    
    @Test
    @Transactional
    public void getStanding() throws Exception {
        // Initialize the database
        standingRepository.saveAndFlush(standing);

        // Get the standing
        restStandingMockMvc.perform(get("/api/standings/{id}", standing.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(standing.getId().intValue()))
            .andExpect(jsonPath("$.position").value(DEFAULT_POSITION))
            .andExpect(jsonPath("$.won").value(DEFAULT_WON))
            .andExpect(jsonPath("$.draw").value(DEFAULT_DRAW))
            .andExpect(jsonPath("$.lost").value(DEFAULT_LOST))
            .andExpect(jsonPath("$.points").value(DEFAULT_POINTS));
    }

    @Test
    @Transactional
    public void getNonExistingStanding() throws Exception {
        // Get the standing
        restStandingMockMvc.perform(get("/api/standings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStanding() throws Exception {
        // Initialize the database
        standingRepository.saveAndFlush(standing);

        int databaseSizeBeforeUpdate = standingRepository.findAll().size();

        // Update the standing
        Standing updatedStanding = standingRepository.findById(standing.getId()).get();
        // Disconnect from session so that the updates on updatedStanding are not directly saved in db
        em.detach(updatedStanding);
        updatedStanding
            .position(UPDATED_POSITION)
            .won(UPDATED_WON)
            .draw(UPDATED_DRAW)
            .lost(UPDATED_LOST)
            .points(UPDATED_POINTS);
        StandingDTO standingDTO = standingMapper.toDto(updatedStanding);

        restStandingMockMvc.perform(put("/api/standings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(standingDTO)))
            .andExpect(status().isOk());

        // Validate the Standing in the database
        List<Standing> standingList = standingRepository.findAll();
        assertThat(standingList).hasSize(databaseSizeBeforeUpdate);
        Standing testStanding = standingList.get(standingList.size() - 1);
        assertThat(testStanding.getPosition()).isEqualTo(UPDATED_POSITION);
        assertThat(testStanding.getWon()).isEqualTo(UPDATED_WON);
        assertThat(testStanding.getDraw()).isEqualTo(UPDATED_DRAW);
        assertThat(testStanding.getLost()).isEqualTo(UPDATED_LOST);
        assertThat(testStanding.getPoints()).isEqualTo(UPDATED_POINTS);
    }

    @Test
    @Transactional
    public void updateNonExistingStanding() throws Exception {
        int databaseSizeBeforeUpdate = standingRepository.findAll().size();

        // Create the Standing
        StandingDTO standingDTO = standingMapper.toDto(standing);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStandingMockMvc.perform(put("/api/standings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(standingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Standing in the database
        List<Standing> standingList = standingRepository.findAll();
        assertThat(standingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStanding() throws Exception {
        // Initialize the database
        standingRepository.saveAndFlush(standing);

        int databaseSizeBeforeDelete = standingRepository.findAll().size();

        // Delete the standing
        restStandingMockMvc.perform(delete("/api/standings/{id}", standing.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Standing> standingList = standingRepository.findAll();
        assertThat(standingList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Standing.class);
        Standing standing1 = new Standing();
        standing1.setId(1L);
        Standing standing2 = new Standing();
        standing2.setId(standing1.getId());
        assertThat(standing1).isEqualTo(standing2);
        standing2.setId(2L);
        assertThat(standing1).isNotEqualTo(standing2);
        standing1.setId(null);
        assertThat(standing1).isNotEqualTo(standing2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StandingDTO.class);
        StandingDTO standingDTO1 = new StandingDTO();
        standingDTO1.setId(1L);
        StandingDTO standingDTO2 = new StandingDTO();
        assertThat(standingDTO1).isNotEqualTo(standingDTO2);
        standingDTO2.setId(standingDTO1.getId());
        assertThat(standingDTO1).isEqualTo(standingDTO2);
        standingDTO2.setId(2L);
        assertThat(standingDTO1).isNotEqualTo(standingDTO2);
        standingDTO1.setId(null);
        assertThat(standingDTO1).isNotEqualTo(standingDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(standingMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(standingMapper.fromId(null)).isNull();
    }
}
