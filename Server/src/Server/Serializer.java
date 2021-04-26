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
     * @param liana row position
     * @param gameId game Id
     * @return JSON string
     */
    public static String serializerPutFruit(String type, Integer liana,Integer gameId){
        JSONObject obj = new JSONObject();
        obj.put("command", "putFruit");
        obj.put("gameId", gameId);
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
        obj.put("command", "deleteFruit");
        obj.put("gameId", gameId);
        obj.put("type", type);
        obj.put("liana", liana);
        return obj.toJSONString();
    }


    /**
     * @author Sebastian Mora
     * @brief Serializes putEnemies command information into a JSON
     * @param type enemy color
     * @param liana row position
     * @param gameId game id
     * @param speed enemy speed
     * @return JSON string
     */
    public static String serializerPutEnimies(String type, Integer liana, Integer gameId, Integer speed){
        JSONObject obj = new JSONObject();
        obj.put("command", "putEnemy");
        obj.put("gameId", gameId);
        obj.put("liana", liana);
        obj.put("type", type);
        obj.put("speed", speed);
        return obj.toJSONString();
    }

    /**
     * @author Sebastian Mora
     * @brief Serializes kill command information into a JSON
     * @param gameId game id
     * @return JSON string
     */
    public static String serializerLives(Integer lives, Integer gameId){
        JSONObject obj = new JSONObject();
        obj.put("command","lives");
        obj.put("lives",lives);
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
    public static String serializerScore(Integer score, Integer gameId){
        JSONObject obj = new JSONObject();
        obj.put("command", "score");
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
    public static String serializerMoveDKJ(Integer posX, Integer posy, Integer facing, Integer jumping, Integer falling, Integer onLiana,  Integer gameId){
        JSONObject obj = new JSONObject();
        obj.put("command", "moveDKJ");
        obj.put("gameId", gameId);
        obj.put("posX", posX);
        obj.put("posY", posy);
        obj.put("facing", facing);
        obj.put("jumping", jumping);
        obj.put("falling", falling);
        obj.put("onLiana", onLiana);
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
        obj.put("command", "gameAccepted");
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

    public static String serializerConnectionRefused(Integer gameId){
        JSONObject obj = new JSONObject();
        obj.put("command", "connectionRefused");
        obj.put("gameId", gameId);
        return obj.toJSONString();

    }



}
