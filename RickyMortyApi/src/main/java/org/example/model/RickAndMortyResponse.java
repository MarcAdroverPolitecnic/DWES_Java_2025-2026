package org.example.model;

import lombok.Data;
import java.util.List;

@Data
public class RickAndMortyResponse {
    private Info info;
    private List<RickAndMortyCharacter> results;

    @Data
    public static class Info {
        private int count;
        private int pages;
        private String next;
        private String prev;
    }
}