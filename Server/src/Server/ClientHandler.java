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
    private final int MAX_GAMES = 2;
    private final int MAX_NUM_OF_OBSERVERS = 2;
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
                    if(game == null){
                        break;
                    }

                    if(game.getPlayer() == this){
                        game.endGame();
                        if(game.getId() == 1) {isGame1Taken = false;};
                        if(game.getId() ==2) {isGame2Taken = false;}
                        Window.updateConsole("Partida " + game.getId() + " terminada");
                        Server.games.remove(this.game);
                        this.isActive = false;
                        this.client.close();
                        this.in.close();
                        this.out.close();
                        break;

                    }else{
                        Window.updateConsole("Observador de la partida " + game.getId() + " desconectado");
                        this.game.removeObserver(this);
                        this.client.close();
                        break;

                    }

                }

                //System.out.printf("Client: %s \n", response);
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
                                Window.updateConsole("Partida 1 ocupada : No se puede conectar");
                            }
                        }

                        if(game_id == 2){
                            if(!isGame2Taken){
                                createNewGame(game_id);
                                isGame2Taken = true;
                            }else{
                                Window.updateConsole("Partida 3 ocupada: No se puede conectar");
                            }
                        }
                        continue;

                    }else{
                        System.out.println("Todas las partidas se encuentran ocupadas");
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
                                Window.updateConsole("Nuevo observador agregado a la partida " + game_id);
                            }else{
                                Window.updateConsole("Maxima cantidad de observadores en la partida " + game_id);
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
            System.out.println("Puta error");
        }
        catch (IOException | ParseException e) {
            System.out.println("Error1");
            e.printStackTrace();
        }catch (NullPointerException e){
            System.out.println("Error2");
            e.printStackTrace();
        }


    }


    private void createNewGame(int game_id){
        Game game = new Game(game_id, this);
        Server.games.add(game);
        this.game = game;
        Window.updateConsole("Nueva partida id: " + game_id);

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
