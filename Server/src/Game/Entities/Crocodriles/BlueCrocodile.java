package Game.Entities.Crocodriles;

import Game.Entities.GameObject;

public class BlueCrocodile extends GameObject {


    /**
     * @author Sebastian Mora
     * @brief Class constructor
     * @param liana position
     */
    public BlueCrocodile(Integer liana){
        this.setSpeed(1);
        this.updatePosition(0,0);
        this.setType("blue");
    }

}
