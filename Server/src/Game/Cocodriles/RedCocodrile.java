package Game.Cocodriles;

import Game.Cocodriles.Cocodrile;
import Game.Game;
import Game.GameObject;

public class RedCocodrile extends GameObject implements Cocodrile {

    int liana;



    public RedCocodrile(int liana){
        // Establece la posicion inicial del cocodrillo
        this.updatePosition(0,0);

        //Asigna el numero de liana donde se encuentra
        this.liana = liana;
    }

    @Override
    public void move() {

        //Se define el comportamiento del cocodrile dependiendo del tipo


    }
}
