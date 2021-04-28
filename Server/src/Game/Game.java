package Game;

import App.Window;
import Game.Entities.*;
import Game.Entities.Crocodriles.CrocodileFactory;
import Game.Entities.Fruit;
import Server.ClientHandler;
import Server.Serializer;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {

    private Integer id;
    private DonkeyKongJunior donkeyKongJunior;
    private List<GameObject> crocodiles;
    private List<Fruit> fruits;
    private List<ClientHandler> observers;
    private JSONParser parser;
    private ClientHandler player;


    /**
     * author Sebastian Mora
     * @brief Class constructor
     * @param id game id
     * @param player player who starts the game
     */
    public Game(Integer id, ClientHandler player){

        this.donkeyKongJunior = new DonkeyKongJunior();
        this.observers = Collections.synchronizedList(new ArrayList<>());
        this.crocodiles = Collections.synchronizedList(new ArrayList<>());
        this.fruits = Collections.synchronizedList(new ArrayList<>());
        this.id = id;
        this.player = player;
        this.parser = new JSONParser();
        this.newGame();
    }


    /**
     * @author Sebastian
     * @brief Receives and filter the command given by the client. Calls the respective function depending
     *        on the command given.
     * @param command command given by client
     */
    public void filterCommand(String command) throws ParseException {
        System.out.println(command);
        JSONObject commandJSON = (JSONObject) parser.parse(command);


        if(commandJSON.get("command").equals("moveDKJ")){
            this.moveDJK(commandJSON);
        }
        if(commandJSON.get("command").equals("fruit")){
            this.fruitCaught(commandJSON);
        }
        if(commandJSON.get("command").equals("attacked")){
            this.attacked(commandJSON);
        }
        if(commandJSON.get("command").equals("win")){
            this.win(commandJSON);
        }
        if(commandJSON.get("command").equals("moveEntity")){
            moveEntity(commandJSON);
        }

    }


    /**
     * @author Sebastian Mora
     * @brief Update Donkey-Kong-Jr position. Additionally, send the new position to player and observer.
     * @param info information needed contained in a JSON object
     */
    private void moveDJK(JSONObject info) {
        Integer posX = Integer.parseInt(info.get("posX").toString());
        Integer posY = Integer.parseInt(info.get("posY").toString());
        Integer facing = Integer.parseInt(info.get("facing").toString());
        Integer jumping = Integer.parseInt(info.get("jumping").toString());
        Integer falling = Integer.parseInt(info.get("falling").toString());
        Integer onLiana = Integer.parseInt(info.get("onLiana").toString());
        this.donkeyKongJunior.updatePosition(posX, posY);
        this.sendObservers(Serializer.serializerMoveDKJ(posX, posY, facing, jumping, falling , onLiana,id));
    }

    /**
     *
     */
    private void moveEntity(JSONObject info){
        try{
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.sendObservers(info.toJSONString());


    }

    /**
     * @author Sebastian Mora
     * @brief Update player score
     * @param info information needed contained in a JSON object
     */
    private void fruitCaught(JSONObject info){
        Integer liana = Integer.parseInt(info.get("liana").toString());
        String type = info.get("type").toString();

        for(int i=0; i<fruits.size(); i++){
            Fruit fruit = fruits.get(i);
            if(fruit.getType().equals(type) && fruit.getLiana() == liana){
                this.donkeyKongJunior.increaseScore(fruit.getScore());
                this.deleteFruit(type, liana);
            }

        }
        updateScore(this.donkeyKongJunior.getScore());
    }


    /**
     * @author Sebastian Mora
     * @brief Updates player score
     * @param newScore Integer that represents the new score
     */
    private void updateScore(Integer newScore){
        this.sendPlayers(Serializer.serializerScore(newScore, this.id));

    }

    /**
     * @author Sebastian Mora
     * @brief This function it's call when command 'attacked' is received. Indicates DKJ has been attacked by
     *        a crocodile
     * @param info information needed contained in a JSON object
     */
    private void attacked(JSONObject info){
        Integer gameId = Integer.parseInt(info.get("gameId").toString());
        this.donkeyKongJunior.decrementLives();

        if(this.donkeyKongJunior.getLives() == 0){
            this.gameOver();

        }else{
            this.sendPlayers(Serializer.serializerLives(this.donkeyKongJunior.getLives(), gameId));
        }

    }



    private void updateEnemiesSpeed(){
        if(crocodiles.size()!= 0){
            sendPlayers(Serializer.serializerUpdateSpeedEnemies(crocodiles.get(0).getSpeed(), this.id));
        }

    }


    /**
     * @author Sebastian Mora
     * @brief This function it's call when command 'win' is received. Indicates that player won.
     * @param info information needed contained in a JSON object
     */
    private void win(JSONObject info){
        Integer id = Integer.parseInt(info.get("gameId").toString());
        sendObservers(Serializer.serializerMoveDKJ(donkeyKongJunior.getPosX_inital(), donkeyKongJunior.getPosY_inital(), 1, 0 ,0, 0,id));

        if(crocodiles.size() > 0){
            crocodiles.get(0).setSpeed(crocodiles.get(0).getSpeed() + 1);
        }


        this.donkeyKongJunior.increaseScoreWin();
        updateScore(this.donkeyKongJunior.getScore());
    }


    /**
     * @author Sebastian Mora
     * @brief This function it's call when command 'putEnemy' is received. Indicates the creation of
     *        a new enemy.
     * @param type Indicates the color of the enemy. Might be red or blue.
     * @param liana Position where the enemy has to be placed.
     */
    public void putEnemy(String type, Integer liana){
        GameObject crocodile = CrocodileFactory.createCrocodile(type, liana);
        crocodiles.add(crocodile);
        this.sendPlayers(Serializer.serializerPutEnemies(type, liana, this.id, crocodile.getSpeed()));

    }


    /**
     * @author Sebastian Mora
     * @brief This function it's call when command 'putFruit' is received. Indicates the creation of
     *        a new fruit.
     * @param type Indicates the type of the fruit. Might be banana or apple.
     * @param liana Position where the fruit has to be placed.
     */
    public void putFruit(String type, Integer liana, Integer score){
        fruits.add(new Fruit(type, liana, score));
        sendPlayers(Serializer.serializerPutFruit(type, liana,id));
        Window.updateConsole("Fruta tipo: " + type + " a gregada a la partida " + this.id);

    }


    /**
     * @author Sebastian Mora
     * @brief This function it's call when command 'putEnemy' is received. Indicates the creation of
     *        a new enemy.
     * @param type Indicates the type of the fruit. Might be red banana or apple .
     * @param liana Position where the enemy has to be placed.
     */
    public void deleteFruit(String type, Integer liana){
        Fruit fruitToRemoved = null;
        for(Fruit fruit : fruits){
            if(fruit.getType().equals(type) && fruit.getLiana() == liana){
                fruitToRemoved = fruit;
            }
        }
        fruits.remove(fruitToRemoved);
        sendPlayers(Serializer.serializerDeleteFruit(type, liana, this.id));
    }


    /**
     * @author Sebastian Mora
     * @brief Returs player instance
     * @return player instance
     */
    public ClientHandler getPlayer() {
        return player;
    }


    /**
     * author Sebastian Mora
     * @brief Add a new observer to observer list.
     * @param observer new observer. ClientHandler instance
     */
    public void addObserver(ClientHandler observer) {
        this.observers.add(observer);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        observer.send(Serializer.serializerGameObjects(this.fruits, this.crocodiles, donkeyKongJunior,  this.id));

    }


    /**
     * author Sebastian Mora
     * @brief .
     * @param newState
     */
    private void updateState(String newState){
        //this.sendPlayers(Serializer.serializerUpdateGame());

    }

    /**
     * author Sebastian Mora
     * @brief Notify to all observers that game is over.
     */
    public void gameOver()  {
        donkeyKongJunior.setDefaultScore();
        donkeyKongJunior.setDefaultLives();
        sendPlayers(Serializer.serializerGameOver(this.donkeyKongJunior.getScore(), this.donkeyKongJunior.getLives()));


    }


    /**
     * @author Sebastian Mora
     * @brief Confirms to the player that a game was created successfully
     */
    private void newGame(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.sendPlayers(Serializer.serializerNewGame(this.donkeyKongJunior.getScore(), this.donkeyKongJunior.getLives(), this.id));

    }



    /**
     * @author Sebastian Mora
     * @brief This function receives a command and send it to player and observers.
     * @param command JSON string with the command ready to be sent.
     */
    public void sendPlayers(String command){

        System.out.println("Comienza a enviar");
        if(player != null) {
            player.send(command);
            observers.stream().forEach((player)-> player.send(command));
        }
    }

    public void sendObservers(String command){

        if(player != null) {
            observers.stream().forEach((player)-> player.send(command));
        }
    }

    /**
     * @author Sebastian Mora
     * @brief Returns game id
     * @return game id
     */
    public Integer getId(){
        return this.id;
    }


    /**
     * @author Sebastian Mora
     * @brief Remove the given observer from observer list.
     * @param observer observer instance
     */
    public void removeObserver(ClientHandler observer) {
        System.out.println("Observer deleted");
        this.observers.remove(observer);
    }


    /**
     * @author Sebastian Mora
     * @brief Returns number of observers. Numbers of observer is indicated by observer list size.
     * @return number of observers.
     */
    public Integer numObservers(){
        return observers.size();
    }
}
