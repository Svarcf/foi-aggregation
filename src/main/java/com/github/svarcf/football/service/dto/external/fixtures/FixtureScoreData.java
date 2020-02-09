package com.github.svarcf.football.service.dto.external.fixtures;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FixtureScoreData {
    private String halftime;
    private String fulltime;
    private String extratime;
    private String penalty;

    public FixtureScoreData() {
    }

    public String getHalftime() {
        return halftime;
    }

    public void setHalftime(String halftime) {
        this.halftime = halftime;
    }

    public String getFulltime() {
        return fulltime;
    }

    public void setFulltime(String fulltime) {
        this.fulltime = fulltime;
    }

    public String getExtratime() {
        return extratime;
    }

    public void setExtratime(String extratime) {
        this.extratime = extratime;
    }

    public String getPenalty() {
        return penalty;
    }

    public void setPenalty(String penalty) {
        this.penalty = penalty;
    }
}
