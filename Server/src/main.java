
import org.json.simple.*;
public class main {


    public static void main(String[] args){

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "emanuel");
        jsonObject.put("age", 25);
        jsonObject.put("command", "get");


        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("name", "sebas");
        jsonObject2.put("age", 21);
        jsonObject2.put("command", "post");

        JSONArray arr = new JSONArray();

        arr.add(jsonObject);
        arr.add(jsonObject2);
    }
}
