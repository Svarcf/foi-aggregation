package com.github.svarcf.football.repository;
import com.github.svarcf.football.domain.Fixture;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Fixture entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FixtureRepository extends JpaRepository<Fixture, Long> {

}
