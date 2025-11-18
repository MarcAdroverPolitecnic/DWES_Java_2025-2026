package cat.politecnicllevant.exemples.generic.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class Card implements Comparable{
    private String name;
    private int value;
    private boolean faceDown;

    public abstract void flip();


}