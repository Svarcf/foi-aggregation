package com.github.svarcf.football.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Standing.
 */
@Entity
@Table(name = "standing")
public class Standing implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "position", nullable = false)
    private Integer position;

    @NotNull
    @Column(name = "won", nullable = false)
    private Integer won;

    @NotNull
    @Column(name = "draw", nullable = false)
    private Integer draw;

    @NotNull
    @Column(name = "lost", nullable = false)
    private Integer lost;

    @NotNull
    @Column(name = "points", nullable = false)
    private Integer points;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "competition_id", nullable = false)
    private Competition competition;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPosition() {
        return position;
    }

    public Standing position(Integer position) {
        this.position = position;
        return this;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getWon() {
        return won;
    }

    public Standing won(Integer won) {
        this.won = won;
        return this;
    }

    public void setWon(Integer won) {
        this.won = won;
    }

    public Integer getDraw() {
        return draw;
    }

    public Standing draw(Integer draw) {
        this.draw = draw;
        return this;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getLost() {
        return lost;
    }

    public Standing lost(Integer lost) {
        this.lost = lost;
        return this;
    }

    public void setLost(Integer lost) {
        this.lost = lost;
    }

    public Integer getPoints() {
        return points;
    }

    public Standing points(Integer points) {
        this.points = points;
        return this;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove


    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Standing)) {
            return false;
        }
        return id != null && id.equals(((Standing) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Standing{" +
            "id=" + getId() +
            ", position=" + getPosition() +
            ", won=" + getWon() +
            ", draw=" + getDraw() +
            ", lost=" + getLost() +
            ", points=" + getPoints() +
            "}";
    }
}
