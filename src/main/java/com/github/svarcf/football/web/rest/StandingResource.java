package com.github.svarcf.football.web.rest;

import com.github.svarcf.football.service.StandingService;
import com.github.svarcf.football.web.rest.errors.BadRequestAlertException;
import com.github.svarcf.football.service.dto.StandingDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.github.svarcf.football.domain.Standing}.
 */
@RestController
@RequestMapping("/api")
public class StandingResource {

    private final Logger log = LoggerFactory.getLogger(StandingResource.class);

    private static final String ENTITY_NAME = "footballAggregationStanding";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StandingService standingService;

    public StandingResource(StandingService standingService) {
        this.standingService = standingService;
    }

    /**
     * {@code POST  /standings} : Create a new standing.
     *
     * @param standingDTO the standingDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new standingDTO, or with status {@code 400 (Bad Request)} if the standing has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/standings")
    public ResponseEntity<StandingDTO> createStanding(@Valid @RequestBody StandingDTO standingDTO) throws URISyntaxException {
        log.debug("REST request to save Standing : {}", standingDTO);
        if (standingDTO.getId() != null) {
            throw new BadRequestAlertException("A new standing cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StandingDTO result = standingService.save(standingDTO);
        return ResponseEntity.created(new URI("/api/standings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /standings} : Updates an existing standing.
     *
     * @param standingDTO the standingDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated standingDTO,
     * or with status {@code 400 (Bad Request)} if the standingDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the standingDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/standings")
    public ResponseEntity<StandingDTO> updateStanding(@Valid @RequestBody StandingDTO standingDTO) throws URISyntaxException {
        log.debug("REST request to update Standing : {}", standingDTO);
        if (standingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StandingDTO result = standingService.save(standingDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, standingDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /standings} : get all the standings.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of standings in body.
     */
    @GetMapping("/standings")
    public List<StandingDTO> getAllStandings() {
        log.debug("REST request to get all Standings");
        return standingService.findAll();
    }

    /**
     * {@code GET  /standings/:id} : get the "id" standing.
     *
     * @param id the id of the standingDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the standingDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/standings/{id}")
    public ResponseEntity<StandingDTO> getStanding(@PathVariable Long id) {
        log.debug("REST request to get Standing : {}", id);
        Optional<StandingDTO> standingDTO = standingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(standingDTO);
    }

    /**
     * {@code DELETE  /standings/:id} : delete the "id" standing.
     *
     * @param id the id of the standingDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/standings/{id}")
    public ResponseEntity<Void> deleteStanding(@PathVariable Long id) {
        log.debug("REST request to delete Standing : {}", id);
        standingService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
