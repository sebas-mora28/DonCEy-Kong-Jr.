package Server;

import netscape.javascript.JSObject;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;

public class Serializer {

    private static JSONParser parser = new JSONParser();


    /**
     * @author Sebastian Mora
     * @brief Serializes putFruit command information into a JSON
     * @param type fruit type
     * @param liana liana position
     * @param gameId game Id
     * @return JSON string
     */
    public static String serializerPutFruit(String type, Integer liana, Integer gameId){
        JSONObject obj = new JSONObject();
        obj.put("command", "putFruit");
        obj.put("game_id", gameId);
        obj.put("type", type);
        obj.put("liana", liana);
        return obj.toJSONString();
    }


    /**
     * @author Sebastian Mora
     * @brief Serializes deleteFruit command information into a JSON
     * @param type fruit type
     * @param liana liana position
     * @param gameId game id position
     * @return JSON string
     */
    public static String serializerDeleteFruit(String type, Integer liana, Integer gameId){
        JSONObject obj = new JSONObject();
        obj.put("command", "DeleteFruit");
        obj.put("game_id", gameId);
        obj.put("type", type);
        obj.put("liana", liana);
        return obj.toJSONString();
    }


    /**
     * @author Sebastian Mora
     * @brief Serializes putEnemies command information into a JSON
     * @param color enemy color
     * @param liana liana position
     * @param gameId game id
     * @param speed enemy speed
     * @return JSON string
     */
    public static String serializerPutEnimies(String color, Integer liana, Integer gameId, Integer speed){
        JSONObject obj = new JSONObject();
        obj.put("command", "putEnemy");
        obj.put("gameId", gameId);
        obj.put("liana", liana);
        obj.put("color", color);
        obj.put("speed", speed);
        return obj.toJSONString();
    }

    /**
     * @author Sebastian Mora
     * @brief Serializes kill command information into a JSON
     * @param gameId game id
     * @return JSON string
     */
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


    /**
     * @author Sebastian Mora
     * @brief Serializes UpdateScore command information into a JSON
     * @param score player score
     * @param gameId game id
     * @return JSON string
     */
    public static String serializerUpdateScore(Integer score, Integer gameId){
        JSONObject obj = new JSONObject();
        obj.put("command", "updateScore");
        obj.put("gameId", gameId);
        obj.put("score", score);
        return obj.toJSONString();
    }


    /**
     * @author Sebastian Mora
     * @brief Serializes moveDKJ command information into a JSON
     * @param posX DKJ position in X axis
     * @param posy DKJ position in Y axis
     * @param gameId game id
     * @return JSON string
     */
    public static String serializerMoveDKJ(Integer posX, Integer posy, Integer gameId){
        JSONObject obj = new JSONObject();
        obj.put("command", "moveDKJ");
        obj.put("gameId", gameId);
        obj.put("posX", posX);
        obj.put("posY", posy);
        return obj.toJSONString();

    }


    /**
     * @author Sebastian Mora
     * @brief Serializes game over command information into a JSON
     * @return JSON string
     */
    public static String serializerGameOver(){
        JSONObject obj = new JSONObject();
        obj.put("command","gameOver");
        return obj.toJSONString();
    }


    /**
     * @author Sebastian Mora
     * @brief Serializes new game command information into a JSON
     * @param gameId game id
     * @return JSON string
     */
    public static String serializerNewGame(Integer gameId){
        JSONObject obj = new JSONObject();
        obj.put("command", "GameAccepted");
        obj.put("gameId", gameId);
        return obj.toJSONString();

    }

    /**
     * @author Sebastian Mora
     * @brief Serializes observer confirmation command information into a JSON
     * @param gameId game id
     * @return JSON string
     */
    public static String serializerObserverAdded(Integer gameId){
        JSONObject obj = new JSONObject();
        obj.put("command", "ObserverAccepted");
        obj.put("gameId", gameId);
        return obj.toJSONString();

    }



}
