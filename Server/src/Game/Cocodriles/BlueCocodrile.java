package Game.Cocodriles;

import Game.GameObject;

public class BlueCocodrile extends GameObject implements Cocodrile {

    int liana;

    public BlueCocodrile(int liana){
        this.speed = 1;
        this.updatePosition(0,0);
        this.liana = liana;
    }

    @Override
    public void move() {

    }
}
