package com.github.svarcf.football.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.github.svarcf.football.domain.Player} entity.
 */
public class PlayerDTO implements Serializable {

    private Long id;

    private TeamDTO team;

    @NotNull
    private String name;

    @NotNull
    private String nationality;

    private String position;

    private LocalDate dateOfBirth;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TeamDTO getTeam() {
        return team;
    }

    public void setTeam(TeamDTO team) {
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PlayerDTO playerDTO = (PlayerDTO) o;
        if (playerDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), playerDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PlayerDTO{" +
            "id=" + getId() +
            ", team=" + getTeam() +
            ", name='" + getName() + "'" +
            ", position='" + getPosition() + "'" +
            ", dateOfBirth='" + getDateOfBirth() + "'" +
            ", nationality='" + getNationality() + "'" +
            "}";
    }
}
