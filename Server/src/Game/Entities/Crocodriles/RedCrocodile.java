package Game.Entities.Crocodriles;

import Game.Entities.GameObject;

public class RedCrocodile extends GameObject  {


    /**
     * @author Sebastian Mora
     * @brief Class constructor
     * @param liana liana position
     */
    public RedCrocodile(Integer liana){
        this.updatePosition(0,0);
        this.setType("red");
        this.setLiana(liana);
    }

}
