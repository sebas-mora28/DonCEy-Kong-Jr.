package Server;

import netscape.javascript.JSObject;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;

public class Serializer {

    private static JSONParser parser = new JSONParser();


    public static String serializerPutFruit(String type, int liana, int gameId){
        JSONObject obj = new JSONObject();
        obj.put("command", "putFruit");
        obj.put("game_id", gameId);
        obj.put("type", type);
        obj.put("liana", liana);
        return obj.toJSONString();
    }

    public static String serializerDeleteFruit(String type, int liana, int gameId){
        JSONObject obj = new JSONObject();
        obj.put("command", "DeleteFruit");
        obj.put("game_id", gameId);
        obj.put("type", type);
        obj.put("liana", liana);
        return obj.toJSONString();
    }

    
    public static String serializerPutEnimies(String color, int liana, int gameId){
        JSONObject obj = new JSONObject();
        obj.put("command", "putEnemy");
        obj.put("gameId", gameId);
        obj.put("liana", liana);
        obj.put("color", color);
        return obj.toJSONString();
    }

    public static String serializerKill(Integer gameId){
        JSONObject obj = new JSONObject();
        obj.put("command","kill");
        obj.put("gameId", gameId);
        return obj.toJSONString();
    }

    public static String serializerUpdateGame(){
        JSONObject obj = new JSONObject();
        return obj.toJSONString();
    }

    public static String serializerUpdateScore(Integer score, Integer gameId){
        JSONObject obj = new JSONObject();
        obj.put("command", "updateScore");
        obj.put("gameId", gameId);
        obj.put("score", score);
        return obj.toJSONString();
    }

    public static String serializerMoveDKJ(Integer posX, Integer posy, Integer gameId){
        JSONObject obj = new JSONObject();
        obj.put("command", "moveDKJ");
        obj.put("gameId", gameId);
        obj.put("posX", posX);
        obj.put("posY", posy);
        return obj.toJSONString();

    }

    public static String serializerEndGame(){
        JSONObject obj = new JSONObject();
        obj.put("command","endGame");
        return obj.toJSONString();
    }

    public static String serializerNewGame(Integer gameId){
        JSONObject obj = new JSONObject();
        obj.put("command", "newGameAccepted");
        obj.put("gameId", gameId);
        return obj.toJSONString();

    }



}
