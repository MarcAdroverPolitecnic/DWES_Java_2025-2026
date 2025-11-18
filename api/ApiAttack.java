package cat.politecnicllevant.api;

import cat.politecnicllevant.api.model.PokemonDetail;
import cat.politecnicllevant.api.model.PokemonListResponse;
import cat.politecnicllevant.api.model.PokemonTypeSlot;
import cat.politecnicllevant.api.service.PokemonApiService;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ApiAttack {

    private final PokemonApiService apiService;

    public ApiAttack(PokemonApiService apiService) {
        this.apiService = apiService;
    }

    public static void main(String[] args) {
        new ApiAttack(new PokemonApiService()).run();
    }

    private void run() {
        try {
            PokemonListResponse listResponse = apiService.fetchPokemonList();

            if (listResponse.getResults() == null || listResponse.getResults().isEmpty()) {
                System.out.println("No Pokémon received to display.");
                return;
            }

            List<PokemonDetail> details = listResponse.getResults().stream()
                    .map(entry -> apiService.fetchPokemonDetail(entry.getUrl()))
                    .sorted(Comparator.comparingInt(PokemonDetail::getId))
                    .toList();

            details.forEach(ApiAttack::printPokemonInfo);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Execution was interrupted: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Unable to retrieve data from the PokéAPI: " + e.getMessage());
        } catch (RuntimeException e) {
            System.err.println("Unexpected error : " + e.getMessage());
        }
    }

    private static void printPokemonInfo(PokemonDetail detail) {
        List<PokemonTypeSlot> typeSlots = detail.getTypes();

        String types = typeSlots == null ? "" : typeSlots.stream()
                .sorted(Comparator.comparingInt(PokemonTypeSlot::getSlot))
                .map(slot -> slot.getType() != null ? slot.getType().getName() : null)
                .filter(name -> name != null && !name.isBlank())
                .collect(Collectors.joining(", "));

        System.out.printf("Pokémon #%d - %s%n", detail.getId(), capitalize(detail.getName()));
        System.out.printf("  Height: %.1f m%n", detail.getHeight() / 10.0);
        System.out.printf("  Weight: %.1f kg%n", detail.getWeight() / 10.0);
        System.out.printf("  Base experience: %d%n", detail.getBaseExperience());
        System.out.printf("  Types: %s%n%n", types.isBlank() ? "Unknown" : capitalizeWords(types));
    }

    private static String capitalize(String text) {
        if (text == null || text.isBlank()) {
            return text;
        }
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }

    private static String capitalizeWords(String text) {
        return Arrays.stream(text.split(",\\s*"))
                .map(ApiAttack::capitalize)
                .collect(Collectors.joining(", "));
    }
}
