package com.github.svarcf.football.web.rest;

import com.github.svarcf.football.FootballAggregationApp;
import com.github.svarcf.football.config.SecurityBeanOverrideConfiguration;
import com.github.svarcf.football.domain.Fixture;
import com.github.svarcf.football.repository.FixtureRepository;
import com.github.svarcf.football.service.FixtureService;
import com.github.svarcf.football.service.dto.FixtureDTO;
import com.github.svarcf.football.service.mapper.FixtureMapper;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.github.svarcf.football.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link FixtureResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, FootballAggregationApp.class})
public class FixtureResourceIT {

    private static final LocalDate DEFAULT_EVENT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_EVENT_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_ROUND = "AAAAAAAAAA";
    private static final String UPDATED_ROUND = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS_SHORT = "AAAAAAAAAA";
    private static final String UPDATED_STATUS_SHORT = "BBBBBBBBBB";

    private static final String DEFAULT_VENUE = "AAAAAAAAAA";
    private static final String UPDATED_VENUE = "BBBBBBBBBB";

    private static final String DEFAULT_SCORE = "AAAAAAAAAA";
    private static final String UPDATED_SCORE = "BBBBBBBBBB";

    @Autowired
    private FixtureRepository fixtureRepository;

    @Autowired
    private FixtureMapper fixtureMapper;

    @Autowired
    private FixtureService fixtureService;

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

    private MockMvc restFixtureMockMvc;

    private Fixture fixture;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FixtureResource fixtureResource = new FixtureResource(fixtureService);
        this.restFixtureMockMvc = MockMvcBuilders.standaloneSetup(fixtureResource)
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
    public static Fixture createEntity(EntityManager em) {
        Fixture fixture = new Fixture()
            .eventDate(DEFAULT_EVENT_DATE)
            .round(DEFAULT_ROUND)
            .statusShort(DEFAULT_STATUS_SHORT)
            .venue(DEFAULT_VENUE)
            .score(DEFAULT_SCORE);
        return fixture;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Fixture createUpdatedEntity(EntityManager em) {
        Fixture fixture = new Fixture()
            .eventDate(UPDATED_EVENT_DATE)
            .round(UPDATED_ROUND)
            .statusShort(UPDATED_STATUS_SHORT)
            .venue(UPDATED_VENUE)
            .score(UPDATED_SCORE);
        return fixture;
    }

    @BeforeEach
    public void initTest() {
        fixture = createEntity(em);
    }

    @Test
    @Transactional
    public void createFixture() throws Exception {
        int databaseSizeBeforeCreate = fixtureRepository.findAll().size();

        // Create the Fixture
        FixtureDTO fixtureDTO = fixtureMapper.toDto(fixture);
        restFixtureMockMvc.perform(post("/api/fixtures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fixtureDTO)))
            .andExpect(status().isCreated());

        // Validate the Fixture in the database
        List<Fixture> fixtureList = fixtureRepository.findAll();
        assertThat(fixtureList).hasSize(databaseSizeBeforeCreate + 1);
        Fixture testFixture = fixtureList.get(fixtureList.size() - 1);
        assertThat(testFixture.getEventDate()).isEqualTo(DEFAULT_EVENT_DATE);
        assertThat(testFixture.getRound()).isEqualTo(DEFAULT_ROUND);
        assertThat(testFixture.getStatusShort()).isEqualTo(DEFAULT_STATUS_SHORT);
        assertThat(testFixture.getVenue()).isEqualTo(DEFAULT_VENUE);
        assertThat(testFixture.getScore()).isEqualTo(DEFAULT_SCORE);
    }

    @Test
    @Transactional
    public void createFixtureWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fixtureRepository.findAll().size();

        // Create the Fixture with an existing ID
        fixture.setId(1L);
        FixtureDTO fixtureDTO = fixtureMapper.toDto(fixture);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFixtureMockMvc.perform(post("/api/fixtures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fixtureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Fixture in the database
        List<Fixture> fixtureList = fixtureRepository.findAll();
        assertThat(fixtureList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkEventDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = fixtureRepository.findAll().size();
        // set the field null
        fixture.setEventDate(null);

        // Create the Fixture, which fails.
        FixtureDTO fixtureDTO = fixtureMapper.toDto(fixture);

        restFixtureMockMvc.perform(post("/api/fixtures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fixtureDTO)))
            .andExpect(status().isBadRequest());

        List<Fixture> fixtureList = fixtureRepository.findAll();
        assertThat(fixtureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFixtures() throws Exception {
        // Initialize the database
        fixtureRepository.saveAndFlush(fixture);

        // Get all the fixtureList
        restFixtureMockMvc.perform(get("/api/fixtures?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fixture.getId().intValue())))
            .andExpect(jsonPath("$.[*].eventDate").value(hasItem(DEFAULT_EVENT_DATE.toString())))
            .andExpect(jsonPath("$.[*].round").value(hasItem(DEFAULT_ROUND)))
            .andExpect(jsonPath("$.[*].statusShort").value(hasItem(DEFAULT_STATUS_SHORT)))
            .andExpect(jsonPath("$.[*].venue").value(hasItem(DEFAULT_VENUE)))
            .andExpect(jsonPath("$.[*].score").value(hasItem(DEFAULT_SCORE)));
    }
    
    @Test
    @Transactional
    public void getFixture() throws Exception {
        // Initialize the database
        fixtureRepository.saveAndFlush(fixture);

        // Get the fixture
        restFixtureMockMvc.perform(get("/api/fixtures/{id}", fixture.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(fixture.getId().intValue()))
            .andExpect(jsonPath("$.eventDate").value(DEFAULT_EVENT_DATE.toString()))
            .andExpect(jsonPath("$.round").value(DEFAULT_ROUND))
            .andExpect(jsonPath("$.statusShort").value(DEFAULT_STATUS_SHORT))
            .andExpect(jsonPath("$.venue").value(DEFAULT_VENUE))
            .andExpect(jsonPath("$.score").value(DEFAULT_SCORE));
    }

    @Test
    @Transactional
    public void getNonExistingFixture() throws Exception {
        // Get the fixture
        restFixtureMockMvc.perform(get("/api/fixtures/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFixture() throws Exception {
        // Initialize the database
        fixtureRepository.saveAndFlush(fixture);

        int databaseSizeBeforeUpdate = fixtureRepository.findAll().size();

        // Update the fixture
        Fixture updatedFixture = fixtureRepository.findById(fixture.getId()).get();
        // Disconnect from session so that the updates on updatedFixture are not directly saved in db
        em.detach(updatedFixture);
        updatedFixture
            .eventDate(UPDATED_EVENT_DATE)
            .round(UPDATED_ROUND)
            .statusShort(UPDATED_STATUS_SHORT)
            .venue(UPDATED_VENUE)
            .score(UPDATED_SCORE);
        FixtureDTO fixtureDTO = fixtureMapper.toDto(updatedFixture);

        restFixtureMockMvc.perform(put("/api/fixtures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fixtureDTO)))
            .andExpect(status().isOk());

        // Validate the Fixture in the database
        List<Fixture> fixtureList = fixtureRepository.findAll();
        assertThat(fixtureList).hasSize(databaseSizeBeforeUpdate);
        Fixture testFixture = fixtureList.get(fixtureList.size() - 1);
        assertThat(testFixture.getEventDate()).isEqualTo(UPDATED_EVENT_DATE);
        assertThat(testFixture.getRound()).isEqualTo(UPDATED_ROUND);
        assertThat(testFixture.getStatusShort()).isEqualTo(UPDATED_STATUS_SHORT);
        assertThat(testFixture.getVenue()).isEqualTo(UPDATED_VENUE);
        assertThat(testFixture.getScore()).isEqualTo(UPDATED_SCORE);
    }

    @Test
    @Transactional
    public void updateNonExistingFixture() throws Exception {
        int databaseSizeBeforeUpdate = fixtureRepository.findAll().size();

        // Create the Fixture
        FixtureDTO fixtureDTO = fixtureMapper.toDto(fixture);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFixtureMockMvc.perform(put("/api/fixtures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fixtureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Fixture in the database
        List<Fixture> fixtureList = fixtureRepository.findAll();
        assertThat(fixtureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFixture() throws Exception {
        // Initialize the database
        fixtureRepository.saveAndFlush(fixture);

        int databaseSizeBeforeDelete = fixtureRepository.findAll().size();

        // Delete the fixture
        restFixtureMockMvc.perform(delete("/api/fixtures/{id}", fixture.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Fixture> fixtureList = fixtureRepository.findAll();
        assertThat(fixtureList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Fixture.class);
        Fixture fixture1 = new Fixture();
        fixture1.setId(1L);
        Fixture fixture2 = new Fixture();
        fixture2.setId(fixture1.getId());
        assertThat(fixture1).isEqualTo(fixture2);
        fixture2.setId(2L);
        assertThat(fixture1).isNotEqualTo(fixture2);
        fixture1.setId(null);
        assertThat(fixture1).isNotEqualTo(fixture2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FixtureDTO.class);
        FixtureDTO fixtureDTO1 = new FixtureDTO();
        fixtureDTO1.setId(1L);
        FixtureDTO fixtureDTO2 = new FixtureDTO();
        assertThat(fixtureDTO1).isNotEqualTo(fixtureDTO2);
        fixtureDTO2.setId(fixtureDTO1.getId());
        assertThat(fixtureDTO1).isEqualTo(fixtureDTO2);
        fixtureDTO2.setId(2L);
        assertThat(fixtureDTO1).isNotEqualTo(fixtureDTO2);
        fixtureDTO1.setId(null);
        assertThat(fixtureDTO1).isNotEqualTo(fixtureDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(fixtureMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(fixtureMapper.fromId(null)).isNull();
    }
}
