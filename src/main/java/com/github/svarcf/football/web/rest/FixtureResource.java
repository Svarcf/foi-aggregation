package com.github.svarcf.football.web.rest;

import com.github.svarcf.football.service.FixtureService;
import com.github.svarcf.football.web.rest.errors.BadRequestAlertException;
import com.github.svarcf.football.service.dto.FixtureDTO;

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
 * REST controller for managing {@link com.github.svarcf.football.domain.Fixture}.
 */
@RestController
@RequestMapping("/api")
public class FixtureResource {

    private final Logger log = LoggerFactory.getLogger(FixtureResource.class);

    private static final String ENTITY_NAME = "footballAggregationFixture";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FixtureService fixtureService;

    public FixtureResource(FixtureService fixtureService) {
        this.fixtureService = fixtureService;
    }

    /**
     * {@code POST  /fixtures} : Create a new fixture.
     *
     * @param fixtureDTO the fixtureDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fixtureDTO, or with status {@code 400 (Bad Request)} if the fixture has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/fixtures")
    public ResponseEntity<FixtureDTO> createFixture(@Valid @RequestBody FixtureDTO fixtureDTO) throws URISyntaxException {
        log.debug("REST request to save Fixture : {}", fixtureDTO);
        if (fixtureDTO.getId() != null) {
            throw new BadRequestAlertException("A new fixture cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FixtureDTO result = fixtureService.save(fixtureDTO);
        return ResponseEntity.created(new URI("/api/fixtures/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /fixtures} : Updates an existing fixture.
     *
     * @param fixtureDTO the fixtureDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fixtureDTO,
     * or with status {@code 400 (Bad Request)} if the fixtureDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fixtureDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/fixtures")
    public ResponseEntity<FixtureDTO> updateFixture(@Valid @RequestBody FixtureDTO fixtureDTO) throws URISyntaxException {
        log.debug("REST request to update Fixture : {}", fixtureDTO);
        if (fixtureDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FixtureDTO result = fixtureService.save(fixtureDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fixtureDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /fixtures} : get all the fixtures.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fixtures in body.
     */
    @GetMapping("/fixtures")
    public List<FixtureDTO> getAllFixtures() {
        log.debug("REST request to get all Fixtures");
        return fixtureService.findAll();
    }

    /**
     * {@code GET  /fixtures/:id} : get the "id" fixture.
     *
     * @param id the id of the fixtureDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fixtureDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/fixtures/{id}")
    public ResponseEntity<FixtureDTO> getFixture(@PathVariable Long id) {
        log.debug("REST request to get Fixture : {}", id);
        Optional<FixtureDTO> fixtureDTO = fixtureService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fixtureDTO);
    }

    /**
     * {@code DELETE  /fixtures/:id} : delete the "id" fixture.
     *
     * @param id the id of the fixtureDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/fixtures/{id}")
    public ResponseEntity<Void> deleteFixture(@PathVariable Long id) {
        log.debug("REST request to delete Fixture : {}", id);
        fixtureService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
