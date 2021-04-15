package Game;

import Game.Cocodriles.BlueCocodrile;
import Game.Cocodriles.RedCocodrile;
import Server.ClientHandler;
import Server.Serializer;
import org.json.simple.JSONObject;

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
    private List<ClientHandler> players;


    public Game(int id, ClientHandler player){

        this.donkeyKongJunior = new DonkeyKongJunior();
        this.players = Collections.synchronizedList(new ArrayList<>());
        this.cocodriles = Collections.synchronizedList(new ArrayList<>());
        this.fruits = Collections.synchronizedList(new ArrayList<>());
        this.id = id;
        this.score = 0;
        this.lives = 1;
        this.players.add(player);
        this.newGame();
    }


    public void putEnemy(String color, int liana){
        if(color.equals("blue")){
            System.out.println("Agrega cocodrilo azul");
            cocodriles.add(new RedCocodrile(liana));


        }else if(color.equals("red")){
            System.out.println("Agrega cocodrulo rojo");
            cocodriles.add(new RedCocodrile(liana));
        }
        this.sendToPlayers(Serializer.serializerPutEnimies(color, liana, this.id).toString());

    }

    private void putFruit(int position){
        sendToPlayers(Serializer.serializerPutFruit(position, this.id).toString());

    }

    public void addObserver(ClientHandler observer){

        this.players.add(observer);
    }

    private void updateState(String newState){
        this.sendToPlayers(Serializer.serializerUpdateGame().toString());

    }

    private void newGame(){
        this.sendToPlayers(Serializer.serializerNewGame(this.id).toString());

    }


    public void sendToPlayers(String state){
        players.stream().forEach((player)-> player.send(state));
    }

    public int getId(){
        return this.id;
    }




}
