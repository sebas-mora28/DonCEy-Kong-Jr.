package Server;

import App.Window;
import Game.Game;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class ClientHandler implements Runnable {



    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private JSONParser parser;
    private final Integer MAX_GAMES = 2;
    private final Integer MAX_NUM_OF_OBSERVERS = 2;
    private Game game;
    private static boolean isGame1Available = false;
    private static boolean isGame2Available = false;
    private boolean isActive = true;


    /**
     * @author Sebastian Mora
     * @brief Class constructor
     * @param client clientHandler instance that represents client who is playing
     */
    public ClientHandler(Socket client) throws IOException {
        this.client = client;
        this.out = new PrintWriter(client.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(client.getInputStream(), "US-ASCII"));
        this.parser = new JSONParser();


    }

    /**
     * @author Sebastian Mora
     * @brief Override run method from Runnable interface
     */
    @Override
    public void run() {

        //this.send(Serializer.serializerNewGame(1));




        try {


            while(isActive){

                String response = in.readLine();


                if(response == null){ //Verifica que el cliente que esta jugando no se haya desconectado de la partida

                    if(game.getPlayer() == this){
                        this.finishResources();
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

                if(command.equals("newGame")){
                    this.newGameHandler(responseJson);
                    continue;
                }

                if(command.equals("observer")){
                    this.observerHandler(responseJson);
                    continue;
                }
                else{
                    Integer game_id = Integer.parseInt(responseJson.get("gameId").toString());
                    for(Game game : Server.games){
                        if(game.getId() == game_id){
                            game.filterCommand(response);

                        }
                    }
                    continue;
                }

            }
        }catch (SocketException e){
            try { this.finishResources(); }
            catch (IOException er) { er.printStackTrace(); }
        }
        catch (IOException | ParseException e) {
            e.printStackTrace();
        }catch (NullPointerException e){
            e.printStackTrace();
        }


    }


    /**
     * @author Sebastian Mora
     * @brief Finish all the resources involved in game. It's call when the player disconnects
     * @throws IOException
     */
    private void finishResources() throws IOException {
        game.endGame();
        freeGame(game.getId());
        Window.updateConsole("Game  " + game.getId() + " end: player disconnected");
        Server.games.remove(this.game);
        this.isActive = false;
        this.client.close();
        this.in.close();
        this.out.close();

    }


    /**
     * @author Sebastian Mora
     * @brief Verifies whether a new game can be created.
     * @param responseJson response obtained from client
     */
    public void newGameHandler(JSONObject responseJson){
        if(Server.games.size() < MAX_GAMES){

            Integer game_id = Integer.parseInt(responseJson.get("gameId").toString());

            if(game_id == 1){
                if(!isGame1Available){
                    createNewGame(game_id);
                    isGame1Available = true;
                }else{
                    Window.updateConsole("Client tried to connect game 1, but it is unavailable");
                    send(Serializer.serializerConnectionRefused(game_id));
                }
            }

            if(game_id == 2){
                if(!isGame2Available){
                    createNewGame(game_id);
                    isGame2Available = true;
                }else{
                    Window.updateConsole("Client tried to connect game 2, but it is unavailable");
                    send(Serializer.serializerConnectionRefused(game_id));
                }
            }

        }else{
            Window.updateConsole("No games available");
        }

    }

    /**
     * @author Sebastian Mora
     * @brief Verifies whether client can observe a game given the game id.
     * @param responseJson response obtained from client
     */
    public void observerHandler(JSONObject responseJson){
        Integer game_id = Integer.parseInt(responseJson.get("gameId").toString());

        for(Game game : Server.games){
            if(game.getId() == game_id){
                if(game.numObservers() <= MAX_NUM_OF_OBSERVERS){
                    this.game = game;
                    game.addObserver(this);
                    Window.updateConsole("New observer added to game " + game_id);
                }else{
                    Window.updateConsole("Client tried to observe game " + game_id + " but it's full");
                    send(Serializer.serializerConnectionRefused(game_id));
                }
            }
        }

    }


    /**
     * @author Sebastian Mora
     * @brief Free a game when the player disconnects, by its id.
     * @param game_id Integer that represents game id
     */
    private void freeGame(Integer game_id){
        if(game_id ==1){
            isGame1Available = false;
        } else if(game_id ==2) {
            isGame2Available = false;
        } else{
            System.out.println("Invalid id");

        }
    }


    /**
     * @author Sebastian Mora
     * @brief  Creates a new Game instance and add it to games list.
     * @param game_id Integer that represents game id
     */
    private void createNewGame(Integer game_id){
        Game game = new Game(game_id, this);
        Server.games.add(game);
        this.game = game;
        Window.updateConsole("New game id: " + game_id);

    }


    /**
     * @author Sebastian Mora
     * @brief Sends a message to client socket associated.
     * @param message String that represents the message
     */
    public synchronized void send(String message) {
        try {

            System.out.println("Enviando");
            this.out.println(message);;
            this.out  = new PrintWriter(client.getOutputStream(), true);


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
