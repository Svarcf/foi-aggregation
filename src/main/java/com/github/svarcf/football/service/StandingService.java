package com.github.svarcf.football.service;

import com.github.svarcf.football.domain.Standing;
import com.github.svarcf.football.repository.StandingRepository;
import com.github.svarcf.football.service.dto.StandingDTO;
import com.github.svarcf.football.service.mapper.StandingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Standing}.
 */
@Service
@Transactional
public class StandingService {

    private final Logger log = LoggerFactory.getLogger(StandingService.class);

    private final StandingRepository standingRepository;

    private final StandingMapper standingMapper;

    public StandingService(StandingRepository standingRepository, StandingMapper standingMapper) {
        this.standingRepository = standingRepository;
        this.standingMapper = standingMapper;
    }

    /**
     * Save a standing.
     *
     * @param standingDTO the entity to save.
     * @return the persisted entity.
     */
    public StandingDTO save(StandingDTO standingDTO) {
        log.debug("Request to save Standing : {}", standingDTO);
        Standing standing = standingMapper.toEntity(standingDTO);
        standing = standingRepository.save(standing);
        return standingMapper.toDto(standing);
    }

    /**
     * Get all the standings.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<StandingDTO> findAll() {
        log.debug("Request to get all Standings");
        return standingRepository.findAll().stream()
            .map(standingMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one standing by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<StandingDTO> findOne(Long id) {
        log.debug("Request to get Standing : {}", id);
        return standingRepository.findById(id)
            .map(standingMapper::toDto);
    }

    /**
     * Delete the standing by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Standing : {}", id);
        standingRepository.deleteById(id);
    }
}
