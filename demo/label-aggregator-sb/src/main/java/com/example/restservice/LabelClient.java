package com.example.restservice;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.Data;

@Service
public class LabelClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public LabelResult getSync(String key) {
        return this.restTemplate.getForEntity("http://localhost:8090/api/" + key, LabelResult.class).getBody();
    }

    @Data
    public static class LabelResult {
        private String key;
        private String label;
    }

}
