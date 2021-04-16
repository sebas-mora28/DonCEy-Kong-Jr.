package Game;

import App.Window;
import Game.Cocodriles.RedCocodrile;
import Server.ClientHandler;
import Server.Serializer;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {

    private int id;
    private int score;
    private int lives;
    private DonkeyKongJunior donkeyKongJunior;
    private List<GameObject> cocodriles;
    private List<GameObject> fruits;
    private List<ClientHandler> observers;
    private JSONParser parser;
    private static Game game1;
    private static Game game2;


    private ClientHandler player;


    public Game(int id, ClientHandler player){

        this.donkeyKongJunior = new DonkeyKongJunior();
        this.observers = Collections.synchronizedList(new ArrayList<>());
        this.cocodriles = Collections.synchronizedList(new ArrayList<>());
        this.fruits = Collections.synchronizedList(new ArrayList<>());
        this.id = id;
        this.score = 0;
        this.lives = 1;
        this.player = player;
        this.parser = new JSONParser();
        this.newGame();
    }




    public void filterCommand(String command) throws ParseException {
        System.out.println(command);
        JSONObject commandJSON = (JSONObject) parser.parse(command);


        if(commandJSON.get("command").equals("moveDKJ")){
            moveDJK(commandJSON);

        }
        if(commandJSON.get("command").equals("fruit")){


        }



    }

    private void moveDJK(JSONObject info) {
        Integer posX = Integer.parseInt(info.get("posX").toString());
        Integer posY = Integer.parseInt(info.get("posY").toString());
        this.donkeyKongJunior.updatePosition(posX, posY);
        this.sendToPlayers(Serializer.serializerMoveDKJ(posX, posY, id).toString());
    }


    public void putEnemy(String color, int liana){
        if(color.equals("blue")){
            Window.updateConsole("Cocodrilo azul agregado a partida" + id);
            cocodriles.add(new RedCocodrile(liana));


        }else if(color.equals("red")){
            Window.updateConsole("Cocodrilo rojo agregado a partida" + id);
            cocodriles.add(new RedCocodrile(liana));
        }
        this.sendToPlayers(Serializer.serializerPutEnimies(color, liana, this.id).toString());

    }

    public void putFruit(String type, int position){
        fruits.add(new Fruit(type, position));
        sendToPlayers(Serializer.serializerPutFruit(type, position, this.id).toString());

    }

    public void deleteFruit(String type, int liana){
        //Implementar eliminar una fruta
    }

    public ClientHandler getPlayer() {
        return player;
    }

    public void addObserver(ClientHandler observer){

        this.observers.add(observer);
    }

    private void updateState(String newState){
        this.sendToPlayers(Serializer.serializerUpdateGame().toString());

    }


    public void endGame() throws IOException {
        this.observers.stream().forEach((observer)-> observer.send(Serializer.serializerEndGame().toString()));
        this.observers.removeAll(this.observers);
    }


    private void newGame(){
        this.sendToPlayers(Serializer.serializerNewGame(this.id).toString());

    }


    public void sendToPlayers(String state){

        if(player != null) {
            player.send(state);
            observers.stream().forEach((player)-> player.send(state));
        }
    }

    public int getId(){
        return this.id;
    }


    public void removeObserver(ClientHandler observer) {
        System.out.println("Observer deleted");
        this.observers.remove(observer);
    }

    public int numObservers(){
        return observers.size();
    }
}
