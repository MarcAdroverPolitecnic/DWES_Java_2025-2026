package cat.politecnicllevant.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PokemonDetail {

    private int id;
    private String name;
    private int height;
    private int weight;
    @SerializedName("base_experience")
    private int baseExperience;
    private List<PokemonTypeSlot> types;

    public PokemonDetail() {
    }

    public PokemonDetail(int id,
                         String name,
                         int height,
                         int weight,
                         int baseExperience,
                         List<PokemonTypeSlot> types) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.baseExperience = baseExperience;
        this.types = types;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public int getBaseExperience() {
        return baseExperience;
    }

    public List<PokemonTypeSlot> getTypes() {
        return types;
    }
}
