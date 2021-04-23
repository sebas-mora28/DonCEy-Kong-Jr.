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

/**
 * @author Sebastian Mora
 * @brief Server implementation
 *
 */
public class Server extends Thread{

    public static List<Game> games;
    private static ServerSocket server;
    private static boolean running = true;
    public static Server serverInstance;


    /**
     * @author Sebastian Mora
     * @brief Singleton implementation, returns server instance.
     * @param port
     * @return server instance
     */
    public static Server getInstance(Integer port){
        if(serverInstance == null){
            serverInstance = new Server(port);
        }
        return serverInstance;
    }

    /**
     * @author Sebastian Mora
     * @brief Class constructor, declared private because of Singleton.
     * @param port
     */
    private Server(Integer port){
        try{

            this.server = new ServerSocket(port);
            this.games = Collections.synchronizedList(new ArrayList<Game>());
            System.out.println("Init Server");
            this.start();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**\
     * @author Sebastian Mora
     * @brief
     */
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
