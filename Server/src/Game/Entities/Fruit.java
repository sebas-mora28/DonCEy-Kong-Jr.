package Game.Entities;

import Game.Game;
import Game.GameObject;

public class Fruit extends GameObject {

    Integer liana;
    String type;


    public Fruit( String type, Integer liana){
        this.liana = liana;
        this.type = type;
    }

    public Integer getLiana() {
        return liana;
    }

    public void setLiana(Integer liana) {
        this.liana = liana;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
