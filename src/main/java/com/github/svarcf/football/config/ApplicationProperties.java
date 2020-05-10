package com.github.svarcf.football.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Football Aggregation.
 * <p>
 * Properties are configured in the {@code application.yml} file.
 * See {@link io.github.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private final ApplicationProperties.Endpoints endpoints = new ApplicationProperties.Endpoints();
    private String token;

    public ApplicationProperties() {
    }

    public Endpoints getEndpoints() {
        return endpoints;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class Endpoints {
        private String fixture;
        private String team;
        private String standing;
        private String player;

        public Endpoints() {
        }

        public String getFixture() {
            return fixture;
        }

        public void setFixture(String fixture) {
            this.fixture = fixture;
        }

        public String getTeam() {
            return team;
        }

        public void setTeam(String team) {
            this.team = team;
        }

        public String getStanding() {
            return standing;
        }

        public void setStanding(String standing) {
            this.standing = standing;
        }

        public String getPlayer() {
            return player;
        }

        public void setPlayer(String player) {
            this.player = player;
        }
    }

}
