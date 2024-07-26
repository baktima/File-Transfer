package File_transfer.testing;

import java.io.*;
import java.util.*;
import java.net.*;

//want to try implementing multithreading for multiple client but got 2 lazy

public class ServerThread implements Runnable{

    private final Socket client;
    BufferedReader in;
    BufferedWriter out;
    File file;

    public ServerThread (Socket client){
        this.client = client;

    }

    @Override
    public void run() {
        String line;
        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            line = in.readLine();
            if(line.contains("DOWNLOAD")){

                System.out.println("Downloading server file for "+ Thread.currentThread().getId());

                return; //pass out to the server using the out i think
            }
            else if(line.contains("UPLOAD")){
                System.out.println("Uploading server file for "+ Thread.currentThread().getId());

                return; //pass the value to the server ??? still got no idea at all
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
