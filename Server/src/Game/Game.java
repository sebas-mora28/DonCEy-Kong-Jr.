package Game;

import Game.Cocodriles.BlueCocodrile;
import Game.Cocodriles.RedCocodrile;
import Server.ClientHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {

    private int id;
    private DonkeyKongJunior donkeyKongJunior;
    private ArrayList<GameObject> cocodriles;
    private ArrayList<GameObject> fruits;
    private List<ClientHandler> players;



    public Game(int id, ClientHandler player){

        this.donkeyKongJunior = new DonkeyKongJunior();
        this.players = Collections.synchronizedList(new ArrayList<>());
        this.id = id;
        this.players.add(player);
    }

    public void putEnemy(String type, int liana){
        if(type.equals("blue")){
            cocodriles.add(new BlueCocodrile(liana));

        }else if(type.equals("red")){
            cocodriles.add(new RedCocodrile(liana));
        }

    }

    public void putFruit(int position){

    }


    public void addObserver(ClientHandler observer){
        this.players.add(observer);
    }

    public void updateState(String newState){
        players.stream().forEach((player)-> player.send(newState));

    }





}
