package cat.politecnicllevant.exemples.generic;

import cat.politecnicllevant.exemples.generic.interfaces.Reproductible;
import cat.politecnicllevant.exemples.generic.model.Media;

import java.util.List;

public class PlayList <T extends Reproductible>{
    private List<T> playList;


}
