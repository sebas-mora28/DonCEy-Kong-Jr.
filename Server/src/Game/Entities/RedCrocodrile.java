package Game.Entities;

import Game.GameObject;

public class RedCrocodrile extends GameObject implements Crocodrile {

    private Integer liana;



    public RedCrocodrile(int liana){
        // Establece la posicion inicial del cocodrillo
        this.updatePosition(0,0);

        //Asigna el numero de liana donde se encuentra
        this.liana = liana;
    }

    @Override
    public void move() {

        //Se define el comportamiento del cocodrile dependiendo del tipo


    }

    public void upSpeed(){
        this.speed += 100;

    }
}
