package com.suvam.consumer.resttemplate;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class RestTemplateClient {

    private final RestTemplate restTemplate;

    private static final String PROVIDER_URL = "http://localhost:8082";

    public String getInstanceInfo(){
        return restTemplate.getForObject(PROVIDER_URL + "/instance-info", String.class);
    }
}
