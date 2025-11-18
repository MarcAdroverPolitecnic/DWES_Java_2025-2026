package org.example.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.model.RickAndMortyResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RickAndMortyApiService {
    private static final URI LIST_ENDPOINT = URI.create("https://rickandmortyapi.com/api/character");
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final Gson gson = new GsonBuilder().create();

    public RickAndMortyResponse fetchRickAndMortyData() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(LIST_ENDPOINT).GET().build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        ensureSuccess(response);

        // Deserializamos el objeto raíz
        return gson.fromJson(response.body(), RickAndMortyResponse.class);
    }

    private void ensureSuccess(HttpResponse<?> response) {
        if (response.statusCode() >= 400) {
            throw new RuntimeException("Llamada fallida con código: " + response.statusCode());
        }
    }
}
