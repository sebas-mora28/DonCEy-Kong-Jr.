package Game.Entities;

import Game.GameObject;

public class BlueCrocodrile extends GameObject implements Crocodrile {

    Integer liana;

    public BlueCrocodrile(int liana){
        this.speed = 1;
        this.updatePosition(0,0);
        this.liana = liana;
    }

    @Override
    public void move() {

    }
}
