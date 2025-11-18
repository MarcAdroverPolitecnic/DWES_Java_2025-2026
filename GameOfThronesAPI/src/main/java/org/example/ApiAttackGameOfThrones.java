package org.example;

import org.example.model.GameOfThronesCharacter;
import org.example.model.GameOfThronesContinent;
import org.example.model.GameOfThronesList;
import org.example.service.GameOfThronesApiService;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

public class ApiAttackGameOfThrones {

    private static final GameOfThronesApiService<GameOfThronesCharacter> apiServiceCharacters = new GameOfThronesApiService(GameOfThronesCharacter.class);
    private static final GameOfThronesApiService<GameOfThronesContinent> apiServiceContinents = new GameOfThronesApiService(GameOfThronesContinent.class);

    public ApiAttackGameOfThrones(){

    }

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        try {

            GameOfThronesList characterList = new GameOfThronesList(apiServiceCharacters.fetchGameOfThronesList());
            GameOfThronesList continentList = new GameOfThronesList(apiServiceContinents.fetchGameOfThronesList());
            GameOfThronesList list = new GameOfThronesList();

            System.out.println("\nImprimir de forma generica");

            characterList.printList();
            continentList.printList();

            System.out.println("\nFiltram amb predicates de forma generica");;

            Predicate<GameOfThronesCharacter> stark = character ->
                    character.getFamily().equals("House Stark");
            Predicate<GameOfThronesContinent> westeros = continent ->
                    continent.getName().equals("Westeros");
            Predicate<GameOfThronesContinent> essosOwesteros = continent ->
                    continent.getName().equals("Essos") ||
                            continent.getName().equals("Westeros");

            System.out.println("\nFiltrat per casa Stark");

            List<GameOfThronesCharacter> filtrada1 = characterList.filtrar(stark);
            list.printList(filtrada1);

            System.out.println("\nFiltrat per continent Westeros");
            List<GameOfThronesContinent> filtrada2 = continentList.filtrar(westeros);
            list.printList(filtrada2);

            System.out.println("\nFiltrat per continent Westeros o Essos");
            List<GameOfThronesContinent> filtrada3 = continentList.filtrar(essosOwesteros);
            list.printList(filtrada3);

            System.out.println("\nSort de forma ordenada y utilitzant el compareTo");

            List<GameOfThronesCharacter> llistaOrdenada1 = characterList.sortList();
            List<GameOfThronesContinent> llistaOrdenada2 = continentList.sortList();
            list.printList(llistaOrdenada1);
            list.printList(llistaOrdenada2);

            System.out.println("\nTranformar llista");

            List<GameOfThronesCharacter> llistaTransformada1 = characterList.listUpperCaseName();
            list.printList(llistaTransformada1);



        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Execution was interrupted: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Unable to retrieve data from the API: " + e.getMessage());
        } catch (RuntimeException e) {
            System.err.println("Unexpected error : " + e.getMessage());
        }
    }

}
