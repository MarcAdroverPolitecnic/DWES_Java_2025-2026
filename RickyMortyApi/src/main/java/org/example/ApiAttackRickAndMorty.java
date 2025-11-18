package org.example;

import org.example.model.RickAndMortyCharacter;
import org.example.model.RickAndMortyResponse;
import org.example.service.RickAndMortyApiService;

import java.io.IOException;

public class ApiAttackRickAndMorty {

    public static void main(String[] args) {
        RickAndMortyApiService apiService = new RickAndMortyApiService();

        try {
            RickAndMortyResponse response = apiService.fetchRickAndMortyData();

            System.out.println("Total de personajes: " + response.getInfo().getCount());
            System.out.println("Mostrando primeros personajes:\n");

            for (RickAndMortyCharacter c : response.getResults()) {
                System.out.println("ID: " + c.getId());
                System.out.println("Name: " + c.getName());
                System.out.println("Status: " + c.getStatus());
                System.out.println("Species: " + c.getSpecies());
                System.out.println("Type: " + (c.getType().isEmpty() ? "(none)" : c.getType()));
                System.out.println("Gender: " + c.getGender());
                System.out.println("--------------------------");
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("Error al obtener datos: " + e.getMessage());
        }
    }
}
