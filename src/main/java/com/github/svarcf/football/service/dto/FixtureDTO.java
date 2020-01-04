package com.github.svarcf.football.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.github.svarcf.football.domain.Fixture} entity.
 */
public class FixtureDTO implements Serializable {

    private Long id;

    @NotNull
    private LocalDate eventDate;

    private String round;

    private String statusShort;

    private String venue;

    private String score;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getStatusShort() {
        return statusShort;
    }

    public void setStatusShort(String statusShort) {
        this.statusShort = statusShort;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FixtureDTO fixtureDTO = (FixtureDTO) o;
        if (fixtureDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fixtureDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FixtureDTO{" +
            "id=" + getId() +
            ", eventDate='" + getEventDate() + "'" +
            ", round='" + getRound() + "'" +
            ", statusShort='" + getStatusShort() + "'" +
            ", venue='" + getVenue() + "'" +
            ", score='" + getScore() + "'" +
            "}";
    }
}
