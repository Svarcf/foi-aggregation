package com.github.svarcf.football.repository;
import com.github.svarcf.football.domain.Standing;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Standing entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StandingRepository extends JpaRepository<Standing, Long> {

}
