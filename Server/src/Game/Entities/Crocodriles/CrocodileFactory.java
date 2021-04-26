package Game.Entities.Crocodriles;

import Game.Entities.GameObject;
import Game.Game;
import Server.Serializer;

public class CrocodileFactory {


    public static GameObject createCrocodile(String type, Integer liana){
        if(type.equals("blue")){
            return new BlueCrocodile(liana);
        }
        if(type.equals("red")){
            return new RedCrocodile(liana);
        }

        return null;

    }
}
