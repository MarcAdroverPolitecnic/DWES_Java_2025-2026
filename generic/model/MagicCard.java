package cat.politecnicllevant.exemples.generic.model;

import lombok.Getter;

@Getter
public class MagicCard extends Card{
    private String description;
    private String altDescription;
    private String type;

    public MagicCard(String name, int value, boolean faceDown, String description, String altDescription, String type){
        super(name, value, faceDown);
        this.description = description;
        this.altDescription = altDescription;
        this.type = type;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof MagicCard) {
            MagicCard otherCard = (MagicCard) o;
            int comparedValue = Integer.compare(this.getValue(), otherCard.getValue());
            if (comparedValue == 0) {
                return this.getName().compareTo(otherCard.getName());
            }
            return comparedValue;
        }
        return 0;
    }

    public String getDescription() {
        if (isFaceDown())
            return altDescription;
        return description;
    }

    @Override
    public void flip() {
        this.setFaceDown(true);
    }

    @Override
    public String toString() {
        return "MagicCard{" +
                "name='" + getName() + '\'' +
                ", value=" + getValue() +
                ", faceDown=" + isFaceDown() +
                ", description='" + getDescription() + '\'' +
                ", altDescription='" + altDescription + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
