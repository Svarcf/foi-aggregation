package com.github.svarcf.football.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Team.
 */
@Entity
@Table(name = "team")
public class Team implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "logo")
    private String logo;

    @NotNull
    @Column(name = "venue_name", nullable = false)
    private String venueName;

    @OneToMany(mappedBy = "team")
    private Set<Player> players = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Team name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public Team logo(String logo) {
        this.logo = logo;
        return this;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getVenueName() {
        return venueName;
    }

    public Team venueName(String venueName) {
        this.venueName = venueName;
        return this;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public Team players(Set<Player> players) {
        this.players = players;
        return this;
    }

    public Team addPlayers(Player player) {
        this.players.add(player);
        player.setTeam(this);
        return this;
    }

    public Team removePlayers(Player player) {
        this.players.remove(player);
        player.setTeam(null);
        return this;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Team)) {
            return false;
        }
        return id != null && id.equals(((Team) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Team{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", logo='" + getLogo() + "'" +
            ", venueName='" + getVenueName() + "'" +
            "}";
    }
}
