package cat.politecnicllevant.api.model;

import java.util.List;

public class PokemonListResponse {

    private int count;
    private String next;
    private String previous;
    private List<PokemonEntry> results;

    public PokemonListResponse() {
    }

    public PokemonListResponse(int count, String next, String previous, List<PokemonEntry> results) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

    public int getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public String getPrevious() {
        return previous;
    }

    public List<PokemonEntry> getResults() {
        return results;
    }
}
