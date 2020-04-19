package com.github.svarcf.football.service;

import com.github.svarcf.football.service.dto.external.SoccerAPIData;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

public abstract class FoiAbstractRestRequest {

    protected ResponseEntity<SoccerAPIData> getRequest(String url, String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-Auth-Token", token);
        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<SoccerAPIData> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            request,
            SoccerAPIData.class
        );

        return response;
    }
}
