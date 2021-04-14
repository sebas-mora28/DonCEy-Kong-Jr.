package Server;

import java.io.*;
import java.lang.reflect.Array;
import java.net.*;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Game.Game;
import org.json.simple.*;


public class Server extends Thread{

    public static List<Game> games;
    private static ServerSocket server;
    private static boolean running = true;
    public static Server serverInstance;



    public static Server getInstance(int port){
        if(serverInstance == null){
            serverInstance = new Server(port);
        }
        return serverInstance;
    }

    public Server(int port){
        try{

            this.server = new ServerSocket(port);
            this.games = Collections.synchronizedList(new ArrayList<Game>());
            System.out.println("Init Server");
            this.start();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(){

        while(running){

            try{
                Socket client = server.accept();

                System.out.printf("Client conneted %s \n", client.getInetAddress());

                ClientHandler newClient = new ClientHandler(client);


                new Thread(newClient).start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
