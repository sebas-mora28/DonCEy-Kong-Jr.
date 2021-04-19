package Game.Entities;

import Game.GameObject;

public class RedCrocodile extends GameObject implements Crocodile {




    /**
     * @atuhor Sebastian Mora
     * @brief Class constructor
     * @param liana crocodile location
     */
    public RedCrocodile(int liana){
        this.updatePosition(0,0);
        this.liana = liana;
        this.speed = 1;
    }

    @Override
    public void move() {

        //Se define el comportamiento del cocodrile dependiendo del tipo


    }


    /**
     * @author Sebastian Mora
     * @brief Increases crocodile speed
     */
    public void upSpeed(){
        this.speed += 100;

    }
}
