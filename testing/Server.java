package File_transfer.testing;

import java.io.*;
import java.net.*;

import File_transfer.FileHandling;
//this code doesn't support http connection it's kinda of a separate thing.


/* to do list for the server side:
* 1. Multiple client can connect since that need to modify the logic of below statement.
* 2. Makes it so it can also UPLOAD to the CLIENT thus the client can DOWNLOAD IT (just use the FileHandling class). (done)
* 3. Maybe add something but if 2 of the above is done can go to next project.
* 4. displaying all the potential files on each of the terminal (next idea)*/


//then this is mostly about opening and reading files i think fuck this is extra difficult
public class Server {

    static ServerSocket server = null;
    static Socket client = null;
    static DataInputStream dataInputStream = null;
    static DataOutputStream dataOutputStream = null;


    public static void main(String[] args ) throws Exception {

        server = new ServerSocket(8080);
        server.setReuseAddress(true);
        System.out.println("trying to connect");
        System.out.println("connected to server  waiting for connection from client");

        while(true){

            client = server.accept();

            //if i want it to only accept one person, i can just loop it in here and it will solve all the problem but i want to make it so a lot of people and a little bit of string handling FUCK
            //why my requirement is so damn high and i just fucking spend 2-3 hours fixing some insane bugs
            dataInputStream = new DataInputStream(client.getInputStream());
            dataOutputStream = new DataOutputStream(client.getOutputStream());

            System.out.println("client connected" + client.getInetAddress().getHostAddress());

            //i think this part isn't necessary
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            //this part will always accept it thus never end, thus maybe this part needs to be multithreaded i guess
            //i think checking if there's something on the IN then execute the recieved function is better than multithreading or something is on the out like sending a request
            //FileHandling.uploadFile("",dataOutputStream) based on a certain command
            System.out.println("starting");


            //for some reason the line doesn't accept any input even though i already send it
            //wtf why this part of the code doesnt accept it fuck is it because it is not syncronize or something
            String line = in.readLine();

            System.out.println(line);
            System.out.println("testing ");

                System.out.println("tes 1");
                if(line.contains("DOWNLOAD")){
                    System.out.println("tes 2 ");
                    FileHandling.uploadFile("C:/Users/Asus/Documents/codes/Java shits/File_transfer/testing/download.txt", dataOutputStream);
                }

                else {
                    System.out.println("tes 3 got skip ___________________");
                    FileHandling.received("C:/Users/Asus/Documents/codes/Java shits/File_transfer/incoming.txt", dataInputStream);
                }

            //need to make a separate thread again i think

            //This part should handle the DOWNLOAD or UPLOAD command (maybe accept the DOWNLOAD and upload command as a string) and then put it on the file ?
            //maybe i should put it on a different package for the server and the client in order to
            in.close();
        }

    }

    //everything works perfectly fine and dont forget to declare the dataInputStream and output stream
    //but the file path is really weird and it just send back to the client not to the server
    // need more testing
}
