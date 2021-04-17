package Server;

import App.Window;
import Game.Game;
import netscape.javascript.JSObject;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.zip.InflaterOutputStream;

public class ClientHandler implements Runnable {



    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private JSONParser parser;
    private final Integer MAX_GAMES = 2;
    private final Integer MAX_NUM_OF_OBSERVERS = 2;
    private Game game;
    private static boolean isGame1Taken = false;
    private static boolean isGame2Taken = false;
    private boolean isActive = true;

    public ClientHandler(Socket client) throws IOException {
        this.client = client;
        this.out = new PrintWriter(client.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        this.parser = new JSONParser();


    }

    @Override
    public void run() {


        try {

            while(isActive){


                String response = in.readLine();

                if(response == null){

                    //Verifica que el cliente que esta jugando no se haya desconectado de la partida
                    if(game.getPlayer() == this){
                        this.endGame();
                        break;

                    }else{
                        Window.updateConsole("Observer from game " + game.getId() + " disconnected");
                        this.game.removeObserver(this);
                        this.client.close();
                        break;

                    }

                }

                JSONObject responseJson = (JSONObject)(this.parser.parse(response));
                String command = responseJson.get("command").toString();

                if(command.equals("new game")){

                    if(Server.games.size() < MAX_GAMES){

                        Integer game_id = Integer.parseInt(responseJson.get("gameId").toString());

                        if(game_id == 1){
                            if(!isGame1Taken){
                                createNewGame(game_id);
                                isGame1Taken = true;
                            }else{
                                Window.updateConsole("Client tried to connect game 1, but it's taken");
                            }
                        }

                        if(game_id == 2){
                            if(!isGame2Taken){
                                createNewGame(game_id);
                                isGame2Taken = true;
                            }else{
                                Window.updateConsole("Client tried to connect game 2, but it's taken");
                            }
                        }
                        continue;

                    }else{
                        continue;
                    }
                }

                if(command.equals("observer")){
                    Integer game_id = Integer.parseInt(responseJson.get("gameId").toString());

                    for(Game game : Server.games){
                        if(game.getId() == game_id){
                            if(game.numObservers() <= MAX_NUM_OF_OBSERVERS){
                                this.game = game;
                                game.addObserver(this);
                                Window.updateConsole("New observer added to game " + game_id);
                            }else{
                                Window.updateConsole("Client tried to observe game " + game_id + " but it's full");
                            }
                        }
                    }
                    continue;
                }
                else{
                    for(Game game : Server.games){
                        Integer game_id = Integer.parseInt(responseJson.get("gameId").toString());
                        if(game.getId() == game_id){
                            game.filterCommand(response);

                        }
                    }
                    continue;
                }

            }
        }catch (SocketException e){
            try { this.endGame(); }
            catch (IOException er) { er.printStackTrace(); }
        }
        catch (IOException | ParseException e) {
            e.printStackTrace();
        }catch (NullPointerException e){
            e.printStackTrace();
        }


    }


    private void endGame() throws IOException {
        game.endGame();
        freeGame(game.getId());
        Window.updateConsole("Game  " + game.getId() + " end: player disconnected");
        Server.games.remove(this.game);
        this.isActive = false;
        this.client.close();
        this.in.close();
        this.out.close();

    }



    private void freeGame(Integer game_id){
        if(game_id ==1){
            isGame1Taken = false;
        } else if(game_id ==2) {
            isGame2Taken = false;
        } else{
            System.out.println("Invalid id");

        }
    }


    private void createNewGame(Integer game_id){
        Game game = new Game(game_id, this);
        Server.games.add(game);
        this.game = game;
        Window.updateConsole("New game id: " + game_id);

    }


    public synchronized void send(String message) {
        try {

            this.out  = new PrintWriter(client.getOutputStream(), true);
            this.out.println(message);

            if(this.out.checkError()){
                throw new IOException("Client Disconnected");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }catch (NullPointerException e){
            e.printStackTrace();
        }


    }
}
