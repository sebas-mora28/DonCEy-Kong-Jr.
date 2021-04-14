package Server;

import netscape.javascript.JSObject;
import org.json.simple.JSONObject;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.zip.InflaterOutputStream;

public class ClientHandler implements Runnable {

    private Socket client;
    private BufferedReader in;
    private PrintWriter out;



    public ClientHandler(Socket client) throws IOException {
        this.client = client;
        this.out = new PrintWriter(client.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));

    }

    @Override
    public void run() {


        try {

            while(true){

                System.out.println(in.readLine());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void send(String message) {
        try {
            this.out  = new PrintWriter(client.getOutputStream(), true);
            this.out.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
