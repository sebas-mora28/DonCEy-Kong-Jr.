package Server;

import Game.Game;
import netscape.javascript.JSObject;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.plaf.BorderUIResource;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.zip.InflaterOutputStream;

public class ClientHandler implements Runnable {

    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private static int currentGameId = 1;
    private final int MAX_GAMES = 2;



    public ClientHandler(Socket client) throws IOException {
        this.client = client;
        this.out = new PrintWriter(client.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));


    }

    @Override
    public void run() {


        try {

            while(true){

                String response = in.readLine();
                System.out.printf("Client: %s \n", response);
                JSONParser parser = new JSONParser();
                JSONObject responseJson = (JSONObject)(parser.parse(response));

                if(response == null){
                    this.client.close();
                    System.out.println("Client disconnected");
                    break;
                }

                String command = responseJson.get("command").toString();

                if(command.equals("new game")){
                    if(Server.games.size() < MAX_GAMES){
                        Game game = new Game(currentGameId, this);
                        Server.games.add(game);
                        currentGameId++;

                    }else{
                        System.out.println("Maximun number of games has been exceed");
                    }
                }

                if(command.equals("observer")){
                    Integer game_id = Integer.parseInt(responseJson.get("gameId").toString());

                    for(Game game : Server.games){
                        if(game.getId() == game_id){
                            game.addObserver(this);
                        }
                    }
                }
                else{

                    for(Game game : Server.games){
                        Integer game_id = Integer.parseInt(responseJson.get("gameId").toString());
                        if(game.getId() == game_id){

                        }
                    }
                }






            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }


    }


    public void send(String message) {
        try {
            this.out  = new PrintWriter(client.getOutputStream(), true);
            this.out.println(message);

            if(this.out.checkError()){
                throw new IOException("Client Disconneted");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
