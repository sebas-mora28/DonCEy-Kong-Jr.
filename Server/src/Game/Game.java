package Game;

import Game.Cocodriles.BlueCocodrile;
import Game.Cocodriles.RedCocodrile;

import java.util.ArrayList;

public class Game {

    private int id;
    private DonkeyKongJunior donkeyKongJunior;
    private ArrayList<GameObject> cocodriles;
    private ArrayList<GameObject> fruits;



    public Game(){
        this.donkeyKongJunior = new DonkeyKongJunior();
    }


    public void putEnemy(String type, int position){
        if(type.equals("blue")){
            cocodriles.add(new BlueCocodrile());

        }else if(type.equals("red")){
            cocodriles.add(new RedCocodrile());
        }

    }

    public void putFruit(int position){

    }




}
