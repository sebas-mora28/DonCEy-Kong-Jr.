package Game.Cocodriles;

import Game.Cocodriles.Cocodrile;
import Game.Game;
import Game.GameObject;

public class RedCocodrile extends GameObject implements Cocodrile {



    public RedCocodrile(){
        // Establece la posicion inicial del cocodrillo
        this.updatePosition(0,0);
    }

    @Override
    public void move() {

        //Se define el comportamiento del cocodrile dependiendo del tipo


    }
}
