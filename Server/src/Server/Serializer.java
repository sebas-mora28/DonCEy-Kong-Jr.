package Server;

import org.json.simple.*;

public class Serializer {


    public static JSONArray serializaerFruit(int position, int gameId){
        JSONObject obj = new JSONObject();
        obj.put("command", "putFruit");
        obj.put("game_id", gameId);
        obj.put("position", position);
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(obj);
        return jsonArray;
    }


}
