package com.aluracursos.screenmatch.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OmdbConfig {
    @Value("${omdb.api.key}")
    private String apiKey;

    public String getApiKey() {
        return apiKey;
    }

    public String getUrlBase() {
        return "https://www.omdbapi.com/?";
    }
}
