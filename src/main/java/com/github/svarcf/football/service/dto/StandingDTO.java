package com.github.svarcf.football.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.github.svarcf.football.domain.Standing} entity.
 */
public class StandingDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer position;

    @NotNull
    private Integer won;

    @NotNull
    private Integer draw;

    @NotNull
    private Integer lost;

    @NotNull
    private Integer points;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getWon() {
        return won;
    }

    public void setWon(Integer won) {
        this.won = won;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getLost() {
        return lost;
    }

    public void setLost(Integer lost) {
        this.lost = lost;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StandingDTO standingDTO = (StandingDTO) o;
        if (standingDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), standingDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StandingDTO{" +
            "id=" + getId() +
            ", position=" + getPosition() +
            ", won=" + getWon() +
            ", draw=" + getDraw() +
            ", lost=" + getLost() +
            ", points=" + getPoints() +
            "}";
    }
}
