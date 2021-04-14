package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.zip.InflaterOutputStream;

public class ClientHandler implements Runnable {

    private final Socket client;
    private BufferedReader in;
    private PrintWriter out;



    public ClientHandler(Socket socket) throws IOException {
        this.client = socket;

        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(), true);
    };


    @Override
    public void run(){

        try{

            while(true){

                String request = in.readLine();

                if(request.contains("name")){
                    System.out.println("Hello");
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }


    }



}
