package File_transfer;

import java.net.*;
import java.util.*;
import java.io.*;

/*to do list for the client side:
* 1. just make it so it can DOWNLOAD and also maybe display the available txt file (done 1/2)
* 2. test number 2 maybe makes it so it also can transfer image i guess
* 3. Maybe add something but if 2 of the above is done can go to next project*/

public class Client {
    public static BufferedWriter out;

    static File file;
    static DataOutputStream dataOutputStream = null;
    static DataInputStream dataInputStream = null;

    public static void main(String[] args) throws Exception {

        try {
            String line = "";
            Scanner input = new Scanner(System.in);
            Socket client = new Socket("localhost", 8080);
            System.out.println("connected to the server");

            dataOutputStream = new DataOutputStream(client.getOutputStream());
            dataInputStream = new DataInputStream(client.getInputStream());

            out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

            //i think that you also need a thread for the incoming multiple files
            //FileHandling fileHandling = new FileHandling();

            //since the action of the CLIENT is always like this, thus no need for multithreading since there's no need for constant input like the messaging part
            //its either making it like this >UPLOAD/DOWNLOAD <insert file here> or the more simple version just UPLOAD and then specify the file path or check it first
            while (!line.equals("exit")) {

                line = input.nextLine();
                //its gonna be DOWNLOAD bla bla bla
                //this function will split the string into two section DOWNLOAD filepath
                String[] splitted = line.split(" ", 2);
                System.out.println(splitted[1]);
                file = new File(splitted[1]);

                if (line.contains("DOWNLOAD") && file.exists()) {
                    out.write(line);//maybe this section can like send the request to the server
                    out.newLine();
                    out.flush();
                    System.out.println("-waiting for file received");
                    System.out.println(out);
                    FileHandling.received("C:/Users/Asus/Documents/codes/Java shits/File_transfer/downloaded.txt" ,dataInputStream);
                    System.out.println("got file");
                } else if (line.contains("UPLOAD") && file.exists()) {
                    out.write(line);
                    out.newLine();
                    out.flush();
                    System.out.println("file exist and uploading");
                    //uploadFile("C:/Users/Asus/Documents/codes/Java shits/File_transfer/testing_1.txt");
                    //pass it as a String so it can be modified
                    FileHandling.uploadFile(splitted[1], dataOutputStream);
                    System.out.println("file uploaded");


                } else {
                    System.out.println("only DOWNLOAD or UPLOAD no other choice or the file doesn't exist");
                }

            }
        }catch (Exception e){
            throw new RuntimeException(e);

        }

        out.close();

        //i think i also need to create a thread for the client but im not sure

        //we need to give an command like UPLOAD and DOWNLOAD
    }

    //the whole idea is set file path using the FileInputStream (for read the data or convert it )and then send the data out
    //using the dataOutputStream and then using the dataInputStream to accept the File and
    //to actually convert the file use the FileOutputStream

    /*FileInputStream: Use when you need to read binary data from a file.
    * FileOutputStream: Use when you need to write binary data to a file.*/

}
