package com.github.svarcf.football.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Fixture.
 */
@Entity
@Table(name = "fixture")
public class Fixture implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "event_date", nullable = false)
    private LocalDate eventDate;

    @Column(name = "round")
    private String round;

    @Column(name = "status_short")
    private String statusShort;

    @Column(name = "venue")
    private String venue;

    @Column(name = "score")
    private String score;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "home_team_id", nullable = false)
    private Team homeTeam;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "away_team_id", nullable = false)
    private Team awayTeam;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public Fixture eventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
        return this;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getRound() {
        return round;
    }

    public Fixture round(String round) {
        this.round = round;
        return this;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getStatusShort() {
        return statusShort;
    }

    public Fixture statusShort(String statusShort) {
        this.statusShort = statusShort;
        return this;
    }

    public void setStatusShort(String statusShort) {
        this.statusShort = statusShort;
    }

    public String getVenue() {
        return venue;
    }

    public Fixture venue(String venue) {
        this.venue = venue;
        return this;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getScore() {
        return score;
    }

    public Fixture score(String score) {
        this.score = score;
        return this;
    }

    public void setScore(String score) {
        this.score = score;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Fixture)) {
            return false;
        }
        return id != null && id.equals(((Fixture) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Fixture{" +
            "id=" + getId() +
            ", eventDate='" + getEventDate() + "'" +
            ", round='" + getRound() + "'" +
            ", statusShort='" + getStatusShort() + "'" +
            ", venue='" + getVenue() + "'" +
            ", score='" + getScore() + "'" +
            "}";
    }
}
