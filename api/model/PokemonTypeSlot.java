package cat.politecnicllevant.api.model;

public class PokemonTypeSlot {

    private int slot;
    private NamedResource type;

    public PokemonTypeSlot() {
    }

    public PokemonTypeSlot(int slot, NamedResource type) {
        this.slot = slot;
        this.type = type;
    }

    public int getSlot() {
        return slot;
    }

    public NamedResource getType() {
        return type;
    }
}
