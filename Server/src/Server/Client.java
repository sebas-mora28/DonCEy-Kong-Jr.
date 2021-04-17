package Server;

import org.json.simple.JSONObject;

import javax.management.relation.RoleUnresolved;
import java.io.*;
import java.net.*;
import java.util.*;

// Client class
class Client {

    // driver code
    public static void main(String[] args)
    {


        // establish a connection by providing host and port
        // number
        try (Socket socket = new Socket("localhost", 4000)) {


            System.out.println("Start");

            // writing to server
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);

            // reading from server
            BufferedReader in
                    = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));

            // object of scanner class
            Scanner sc = new Scanner(System.in);
            String line = null;

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("command","new game");
            jsonObject.put("gameId", 1);
            out.println(jsonObject.toJSONString());
            System.out.println(in.readLine());


            JSONObject fruit = new JSONObject();
            fruit.put("command","moveDKJ");
            fruit.put("gameId", 1);
            fruit.put("posX", 24);
            fruit.put("posY", 25);

            int time = 1;

            boolean running = true;
            while(running){
                //System.out.println(fruit.toString());
                String xd = fruit.toJSONString();
                out.println(xd);
                System.out.println("pasa");
                Thread.sleep(10);
                //System.out.println(time);
                time++;
                if(time > 10000){
                    running = false;
                    break;
                }
            }


            // closing the scanner object
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}