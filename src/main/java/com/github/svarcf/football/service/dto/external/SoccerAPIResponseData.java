package com.github.svarcf.football.service.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SoccerAPIResponseData {

    private SoccerAPIData api;

    public SoccerAPIResponseData() {
    }

    public SoccerAPIData getApi() {
        return api;
    }

    public void setApi(SoccerAPIData api) {
        this.api = api;
    }
}
