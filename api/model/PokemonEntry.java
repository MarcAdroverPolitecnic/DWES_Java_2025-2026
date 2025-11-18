package cat.politecnicllevant.api.model;

public class PokemonEntry {

    private String name;
    private String url;

    public PokemonEntry() {
    }

    public PokemonEntry(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
