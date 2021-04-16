package Server;

import netscape.javascript.JSObject;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;

public class Serializer {

    private static JSONParser parser = new JSONParser();


    public static JSONObject serializerPutFruit(String type, int liana, int gameId){
        JSONObject obj = new JSONObject();
        obj.put("command", "putFruit");
        obj.put("game_id", gameId);
        obj.put("type", type);
        obj.put("liana", liana);
        return obj;
    }
    
    public static JSONObject serializerPutEnimies(String color, int liana, int gameId){
        JSONObject obj = new JSONObject();
        obj.put("command", "putEnemy");
        obj.put("game_id", gameId);
        obj.put("liana", liana);
        obj.put("color", color);
        return obj;
    }

    public static JSONObject serializerUpdateGame(){
        JSONObject obj = new JSONObject();
        return obj;
    }

    public static JSONObject serializerUpdateScore(int score, int game_id){
        JSONObject obj = new JSONObject();
        obj.put("command", "updateScore");
        obj.put("game_id", game_id);
        obj.put("score", score);
        return obj;
    }

    public static JSONObject serializerMoveDKJ(int posX, int posy, int game_id){
        JSONObject obj = new JSONObject();
        obj.put("command", "moveDKJ");
        obj.put("game_id", game_id);
        obj.put("posX", posX);
        obj.put("posY", posy);
        return obj;

    }

    public static JSONObject serializerEndGame(){
        JSONObject obj = new JSONObject();
        obj.put("command","endGame");
        return obj;
    }

    public static JSONObject serializerNewGame(int game_id){
        JSONObject obj = new JSONObject();
        obj.put("command", "newGameAccepted");
        obj.put("game_id", game_id);
        return obj;

    }



}
