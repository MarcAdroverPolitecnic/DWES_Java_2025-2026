package org.example.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.model.GameOfThronesContinent;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class GameOfThronesApiService <T>{

    private static final URI LIST_ENDPOINT_CHARACTERS = URI.create("https://thronesapi.com/api/v2/characters");
    private static final URI LIST_ENDPOINT_CONTINENT = URI.create("https://thronesapi.com/api/v2/continents");

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final Gson gson = new GsonBuilder().create();
    private final Class<T> typeClass;

    public GameOfThronesApiService(Class<T> typeClass) {
        this.typeClass = typeClass;
    }

    public List<T> fetchGameOfThronesList() throws IOException, InterruptedException {
        URI uri = LIST_ENDPOINT_CHARACTERS;

       if (typeClass == GameOfThronesContinent.class){
            uri = LIST_ENDPOINT_CONTINENT;
       }

        HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        ensureSuccess(response, uri.toString());

        Type listType = TypeToken.getParameterized(List.class, typeClass).getType();
        return gson.fromJson(response.body(), listType);
    }

    private void ensureSuccess(HttpResponse<?> response, String url) {
        if (response.statusCode() >= 400) {
            throw new RuntimeException("La llamada " + url + " ha fallado con el código " + response.statusCode());
        }
    }
}
