package com.github.svarcf.football.web.rest;

import com.github.svarcf.football.FootballAggregationApp;
import com.github.svarcf.football.config.SecurityBeanOverrideConfiguration;
import com.github.svarcf.football.domain.Player;
import com.github.svarcf.football.domain.Team;
import com.github.svarcf.football.repository.PlayerRepository;
import com.github.svarcf.football.service.PlayerService;
import com.github.svarcf.football.service.dto.PlayerDTO;
import com.github.svarcf.football.service.mapper.PlayerMapper;
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
 * Integration tests for the {@link PlayerResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, FootballAggregationApp.class})
public class PlayerResourceIT {
//
//    private static final Integer DEFAULT_TEAM = 1;
//    private static final Integer UPDATED_TEAM = 2;
//
//    private static final String DEFAULT_NAME = "AAAAAAAAAA";
//    private static final String UPDATED_NAME = "BBBBBBBBBB";
//
//    private static final String DEFAULT_POSITION = "AAAAAAAAAA";
//    private static final String UPDATED_POSITION = "BBBBBBBBBB";
//
//    private static final LocalDate DEFAULT_DATE_OF_BIRTH = LocalDate.ofEpochDay(0L);
//    private static final LocalDate UPDATED_DATE_OF_BIRTH = LocalDate.now(ZoneId.systemDefault());
//
//    private static final String DEFAULT_NATIONALITY = "AAAAAAAAAA";
//    private static final String UPDATED_NATIONALITY = "BBBBBBBBBB";
//
//    @Autowired
//    private PlayerRepository playerRepository;
//
//    @Autowired
//    private PlayerMapper playerMapper;
//
//    @Autowired
//    private PlayerService playerService;
//
//    @Autowired
//    private MappingJackson2HttpMessageConverter jacksonMessageConverter;
//
//    @Autowired
//    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;
//
//    @Autowired
//    private ExceptionTranslator exceptionTranslator;
//
//    @Autowired
//    private EntityManager em;
//
//    @Autowired
//    private Validator validator;
//
//    private MockMvc restPlayerMockMvc;
//
//    private Player player;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//        final PlayerResource playerResource = new PlayerResource(playerService);
//        this.restPlayerMockMvc = MockMvcBuilders.standaloneSetup(playerResource)
//            .setCustomArgumentResolvers(pageableArgumentResolver)
//            .setControllerAdvice(exceptionTranslator)
//            .setConversionService(createFormattingConversionService())
//            .setMessageConverters(jacksonMessageConverter)
//            .setValidator(validator).build();
//    }
//
//    /**
//     * Create an entity for this test.
//     *
//     * This is a static method, as tests for other entities might also need it,
//     * if they test an entity which requires the current entity.
//     */
//    public static Player createEntity(EntityManager em) {
//        Team team = new Team();
//        team.setId(DEFAULT_TEAM);
//        Player player = new Player()
//            .team(team)
//            .name(DEFAULT_NAME)
//            .position(DEFAULT_POSITION)
//            .dateOfBirth(DEFAULT_DATE_OF_BIRTH)
//            .nationality(DEFAULT_NATIONALITY);
//        return player;
//    }
//    /**
//     * Create an updated entity for this test.
//     *
//     * This is a static method, as tests for other entities might also need it,
//     * if they test an entity which requires the current entity.
//     */
//    public static Player createUpdatedEntity(EntityManager em) {
//        Player player = new Player()
//            .team(UPDATED_TEAM)
//            .name(UPDATED_NAME)
//            .position(UPDATED_POSITION)
//            .dateOfBirth(UPDATED_DATE_OF_BIRTH)
//            .nationality(UPDATED_NATIONALITY);
//        return player;
//    }
//
//    @BeforeEach
//    public void initTest() {
//        player = createEntity(em);
//    }
//
//    @Test
//    @Transactional
//    public void createPlayer() throws Exception {
//        int databaseSizeBeforeCreate = playerRepository.findAll().size();
//
//        // Create the Player
//        PlayerDTO playerDTO = playerMapper.toDto(player);
//        restPlayerMockMvc.perform(post("/api/players")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(playerDTO)))
//            .andExpect(status().isCreated());
//
//        // Validate the Player in the database
//        List<Player> playerList = playerRepository.findAll();
//        assertThat(playerList).hasSize(databaseSizeBeforeCreate + 1);
//        Player testPlayer = playerList.get(playerList.size() - 1);
//        assertThat(testPlayer.getTeam().getId()).isEqualTo(DEFAULT_TEAM);
//        assertThat(testPlayer.getName()).isEqualTo(DEFAULT_NAME);
//        assertThat(testPlayer.getPosition()).isEqualTo(DEFAULT_POSITION);
//        assertThat(testPlayer.getDateOfBirth()).isEqualTo(DEFAULT_DATE_OF_BIRTH);
//        assertThat(testPlayer.getNationality()).isEqualTo(DEFAULT_NATIONALITY);
//    }
//
//    @Test
//    @Transactional
//    public void createPlayerWithExistingId() throws Exception {
//        int databaseSizeBeforeCreate = playerRepository.findAll().size();
//
//        // Create the Player with an existing ID
//        player.setId(1L);
//        PlayerDTO playerDTO = playerMapper.toDto(player);
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restPlayerMockMvc.perform(post("/api/players")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(playerDTO)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the Player in the database
//        List<Player> playerList = playerRepository.findAll();
//        assertThat(playerList).hasSize(databaseSizeBeforeCreate);
//    }
//
//
//    @Test
//    @Transactional
//    public void checkNameIsRequired() throws Exception {
//        int databaseSizeBeforeTest = playerRepository.findAll().size();
//        // set the field null
//        player.setName(null);
//
//        // Create the Player, which fails.
//        PlayerDTO playerDTO = playerMapper.toDto(player);
//
//        restPlayerMockMvc.perform(post("/api/players")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(playerDTO)))
//            .andExpect(status().isBadRequest());
//
//        List<Player> playerList = playerRepository.findAll();
//        assertThat(playerList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void checkPositionIsRequired() throws Exception {
//        int databaseSizeBeforeTest = playerRepository.findAll().size();
//        // set the field null
//        player.setPosition(null);
//
//        // Create the Player, which fails.
//        PlayerDTO playerDTO = playerMapper.toDto(player);
//
//        restPlayerMockMvc.perform(post("/api/players")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(playerDTO)))
//            .andExpect(status().isBadRequest());
//
//        List<Player> playerList = playerRepository.findAll();
//        assertThat(playerList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void checkDateOfBirthIsRequired() throws Exception {
//        int databaseSizeBeforeTest = playerRepository.findAll().size();
//        // set the field null
//        player.setDateOfBirth(null);
//
//        // Create the Player, which fails.
//        PlayerDTO playerDTO = playerMapper.toDto(player);
//
//        restPlayerMockMvc.perform(post("/api/players")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(playerDTO)))
//            .andExpect(status().isBadRequest());
//
//        List<Player> playerList = playerRepository.findAll();
//        assertThat(playerList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void checkNationalityIsRequired() throws Exception {
//        int databaseSizeBeforeTest = playerRepository.findAll().size();
//        // set the field null
//        player.setNationality(null);
//
//        // Create the Player, which fails.
//        PlayerDTO playerDTO = playerMapper.toDto(player);
//
//        restPlayerMockMvc.perform(post("/api/players")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(playerDTO)))
//            .andExpect(status().isBadRequest());
//
//        List<Player> playerList = playerRepository.findAll();
//        assertThat(playerList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void getAllPlayers() throws Exception {
//        // Initialize the database
//        playerRepository.saveAndFlush(player);
//
//        // Get all the playerList
//        restPlayerMockMvc.perform(get("/api/players?sort=id,desc"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(player.getId().intValue())))
//            .andExpect(jsonPath("$.[*].team").value(hasItem(DEFAULT_TEAM)))
//            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
//            .andExpect(jsonPath("$.[*].position").value(hasItem(DEFAULT_POSITION)))
//            .andExpect(jsonPath("$.[*].dateOfBirth").value(hasItem(DEFAULT_DATE_OF_BIRTH.toString())))
//            .andExpect(jsonPath("$.[*].nationality").value(hasItem(DEFAULT_NATIONALITY)));
//    }
//
//    @Test
//    @Transactional
//    public void getPlayer() throws Exception {
//        // Initialize the database
//        playerRepository.saveAndFlush(player);
//
//        // Get the player
//        restPlayerMockMvc.perform(get("/api/players/{id}", player.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.id").value(player.getId().intValue()))
//            .andExpect(jsonPath("$.team").value(DEFAULT_TEAM))
//            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
//            .andExpect(jsonPath("$.position").value(DEFAULT_POSITION))
//            .andExpect(jsonPath("$.dateOfBirth").value(DEFAULT_DATE_OF_BIRTH.toString()))
//            .andExpect(jsonPath("$.nationality").value(DEFAULT_NATIONALITY));
//    }
//
//    @Test
//    @Transactional
//    public void getNonExistingPlayer() throws Exception {
//        // Get the player
//        restPlayerMockMvc.perform(get("/api/players/{id}", Long.MAX_VALUE))
//            .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @Transactional
//    public void updatePlayer() throws Exception {
//        // Initialize the database
//        playerRepository.saveAndFlush(player);
//
//        int databaseSizeBeforeUpdate = playerRepository.findAll().size();
//
//        // Update the player
//        Player updatedPlayer = playerRepository.findById(player.getId()).get();
//        // Disconnect from session so that the updates on updatedPlayer are not directly saved in db
//        em.detach(updatedPlayer);
//        updatedPlayer
//            .team(UPDATED_TEAM)
//            .name(UPDATED_NAME)
//            .position(UPDATED_POSITION)
//            .dateOfBirth(UPDATED_DATE_OF_BIRTH)
//            .nationality(UPDATED_NATIONALITY);
//        PlayerDTO playerDTO = playerMapper.toDto(updatedPlayer);
//
//        restPlayerMockMvc.perform(put("/api/players")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(playerDTO)))
//            .andExpect(status().isOk());
//
//        // Validate the Player in the database
//        List<Player> playerList = playerRepository.findAll();
//        assertThat(playerList).hasSize(databaseSizeBeforeUpdate);
//        Player testPlayer = playerList.get(playerList.size() - 1);
//        assertThat(testPlayer.getTeam()).isEqualTo(UPDATED_TEAM);
//        assertThat(testPlayer.getName()).isEqualTo(UPDATED_NAME);
//        assertThat(testPlayer.getPosition()).isEqualTo(UPDATED_POSITION);
//        assertThat(testPlayer.getDateOfBirth()).isEqualTo(UPDATED_DATE_OF_BIRTH);
//        assertThat(testPlayer.getNationality()).isEqualTo(UPDATED_NATIONALITY);
//    }
//
//    @Test
//    @Transactional
//    public void updateNonExistingPlayer() throws Exception {
//        int databaseSizeBeforeUpdate = playerRepository.findAll().size();
//
//        // Create the Player
//        PlayerDTO playerDTO = playerMapper.toDto(player);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restPlayerMockMvc.perform(put("/api/players")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(playerDTO)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the Player in the database
//        List<Player> playerList = playerRepository.findAll();
//        assertThat(playerList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    public void deletePlayer() throws Exception {
//        // Initialize the database
//        playerRepository.saveAndFlush(player);
//
//        int databaseSizeBeforeDelete = playerRepository.findAll().size();
//
//        // Delete the player
//        restPlayerMockMvc.perform(delete("/api/players/{id}", player.getId())
//            .accept(TestUtil.APPLICATION_JSON_UTF8))
//            .andExpect(status().isNoContent());
//
//        // Validate the database contains one less item
//        List<Player> playerList = playerRepository.findAll();
//        assertThat(playerList).hasSize(databaseSizeBeforeDelete - 1);
//    }
//
//    @Test
//    @Transactional
//    public void equalsVerifier() throws Exception {
//        TestUtil.equalsVerifier(Player.class);
//        Player player1 = new Player();
//        player1.setId(1L);
//        Player player2 = new Player();
//        player2.setId(player1.getId());
//        assertThat(player1).isEqualTo(player2);
//        player2.setId(2L);
//        assertThat(player1).isNotEqualTo(player2);
//        player1.setId(null);
//        assertThat(player1).isNotEqualTo(player2);
//    }
//
//    @Test
//    @Transactional
//    public void dtoEqualsVerifier() throws Exception {
//        TestUtil.equalsVerifier(PlayerDTO.class);
//        PlayerDTO playerDTO1 = new PlayerDTO();
//        playerDTO1.setId(1L);
//        PlayerDTO playerDTO2 = new PlayerDTO();
//        assertThat(playerDTO1).isNotEqualTo(playerDTO2);
//        playerDTO2.setId(playerDTO1.getId());
//        assertThat(playerDTO1).isEqualTo(playerDTO2);
//        playerDTO2.setId(2L);
//        assertThat(playerDTO1).isNotEqualTo(playerDTO2);
//        playerDTO1.setId(null);
//        assertThat(playerDTO1).isNotEqualTo(playerDTO2);
//    }
//
//    @Test
//    @Transactional
//    public void testEntityFromId() {
//        assertThat(playerMapper.fromId(42L).getId()).isEqualTo(42);
//        assertThat(playerMapper.fromId(null)).isNull();
//    }
}
