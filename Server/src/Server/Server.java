package Server;

import java.io.*;
import java.net.*;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static  ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();
    private static ServerSocket server = null;
    private static boolean running = true;
    private static ExecutorService pool = Executors.newFixedThreadPool(4);


    public static void main(String[] args){

        try{


            server = new ServerSocket(3000);
            server.setReuseAddress(true);


            while(running){


                Socket client = server.accept();
                System.out.printf("New client %s", client.getInetAddress());

                ClientHandler clientHandler = new ClientHandler(client);

                clients.add(clientHandler);

                pool.execute(clientHandler);

            }





        }catch (IOException error){

        }finally{

        }

    }

}
