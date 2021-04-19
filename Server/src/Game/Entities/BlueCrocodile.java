package Game.Entities;

import Game.GameObject;

public class BlueCrocodile extends GameObject implements Crocodile {


    /**
     * @author Sebastian Mora
     * @brief Class constructor
     * @param liana liana position
     */
    public BlueCrocodile(int liana){
        this.speed = 1;
        this.updatePosition(0,0);
        this.liana = liana;
    }

    @Override
    public void move() {

    }
}
