package com.aluracursos.screenmatch.service;
import com.aluracursos.screenmatch.config.OmdbConfig;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ConsumoAPI {
    private final OmdbConfig omdbConfig;

    public ConsumoAPI(OmdbConfig omdbConfig) {
        this.omdbConfig = omdbConfig;
    }

    private String construirUrl(String parametros) {
        return omdbConfig.getUrlBase() + parametros + omdbConfig.getApiKey();
    }

    public String obtenerDatosPorTitulo(String titulo) {
        String parametros = "t=" + titulo.replace(" ", "+");
        return obtenerDatos(parametros);
    }

    public String obtenerDatosPorTituloYTemporada(String titulo, int temporada) {
        String parametros = "t=" + titulo.replace(" ", "+") + "&Season=" + temporada;
        return obtenerDatos(parametros);
    }

    private String obtenerDatos(String parametros) {
        String url = construirUrl(parametros);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error al consumir la API", e);
        }
    }
}
