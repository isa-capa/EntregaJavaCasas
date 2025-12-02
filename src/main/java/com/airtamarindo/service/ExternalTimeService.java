package com.airtamarindo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
public class ExternalTimeService {

    private static final String URL = "http://worldclockapi.com/api/json/utc/now";

    public LocalDateTime obtenerFechaActual() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            @SuppressWarnings("unchecked")
            Map<String, Object> response = restTemplate.getForObject(URL, Map.class);

            if (response != null && response.containsKey("currentDateTime")) {
                String isoDateTime = (String) response.get("currentDateTime");
                return LocalDateTime.parse(isoDateTime, DateTimeFormatter.ISO_DATE_TIME);
            }
        } catch (Exception e) {
            // Si falla, caemos al sistema local
        }
        return LocalDateTime.now();
    }
}
