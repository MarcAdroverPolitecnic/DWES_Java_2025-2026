package org.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

public class GameOfThronesList <T extends GameOfThronesEntry>{
    private List<T> gameOfThronesList;

    public GameOfThronesList() {
    }

    public GameOfThronesList(List<T> gameOfThronesList) {
        this.gameOfThronesList = gameOfThronesList;
    }

    public void printList(){
        gameOfThronesList.stream().map(entry -> {
            entry.printGameOfThronesEntry();
            System.out.println();
            return entry;
        }).toList();
    }

    public void printList(List<T> list){
        for (int i = 0; i < list.size(); i++) {
            list.get(i).printGameOfThronesEntry();
        }
    }

    public List<T> filtrar(Predicate<T> predicate){
        System.out.println("Characters" + "\n");
        List<T> list = gameOfThronesList.stream().filter(predicate).toList();
        return list;
    }

    public List<T> sortList() {
        List<T> a = gameOfThronesList.stream().sorted().toList();
        return a;
    }

    public List<T> listUpperCaseName() {
        for (int i = 0; i < gameOfThronesList.size(); i++) {
            gameOfThronesList.get(i).setNameUpperCase();
        }

        return gameOfThronesList;
    }





}
