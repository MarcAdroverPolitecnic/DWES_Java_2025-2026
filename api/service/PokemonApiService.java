package cat.politecnicllevant.api.service;

import cat.politecnicllevant.api.model.PokemonDetail;
import cat.politecnicllevant.api.model.PokemonListResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PokemonApiService {

    private static final URI LIST_ENDPOINT = URI.create("https://pokeapi.co/api/v2/pokemon?limit=10");

    private final HttpClient httpClient;
    private final Gson gson;

    public PokemonApiService() {
        this(HttpClient.newHttpClient(), new GsonBuilder().create());
    }

    public PokemonApiService(HttpClient httpClient, Gson gson) {
        this.httpClient = httpClient;
        this.gson = gson;
    }

    public PokemonListResponse fetchPokemonList() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(LIST_ENDPOINT).GET().build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        ensureSuccess(response, LIST_ENDPOINT.toString());
        return gson.fromJson(response.body(), PokemonListResponse.class);
    }

    public PokemonDetail fetchPokemonDetail(String url) {
        try {
            HttpRequest request = HttpRequest.newBuilder(URI.create(url)).GET().build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            ensureSuccess(response, url);
            return gson.fromJson(response.body(), PokemonDetail.class);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("La solicitud fue interrumpida mientras se consultaba: " + url, e);
        } catch (IOException e) {
            throw new RuntimeException("No se ha podido obtener el detalle del Pokémon: " + url, e);
        }
    }

    private void ensureSuccess(HttpResponse<?> response, String url) {
        if (response.statusCode() >= 400) {
            throw new RuntimeException("La llamada " + url + " ha fallado con el código " + response.statusCode());
        }
    }
}
