package File_transfer;

import java.util.*;
import java.io.*;
import java.net.*;

public class FileHandling {

    public static void received(String fileName, DataInputStream dataInputStream) throws IOException {
        try {

            int bytes = 0;

            File file = new File(fileName);
            FileOutputStream fileOutputStream = new FileOutputStream(file);

            //the junk value neeed to be read first cause of this
            // dataInputStream.readLong();

            byte[] buffer = new byte[4 *1024];
//            while((bytes = dataInputStream.read(buffer)) != -1 ){
//                System.out.println(buffer);
//                System.out.println("sapi keBO");
//                fileOutputStream.write(buffer, 0, bytes);
//                fileOutputStream.flush();
//
//            }
            while((bytes = dataInputStream.read()) != -1){
                //fileOutputStream.flush();
                System.out.print((char) bytes);

                //the write method to the file doesn't need the char typecasting
                fileOutputStream.write(bytes);
                fileOutputStream.flush();

            }
            System.out.println("File received");

            fileOutputStream.close();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void uploadFile(String path, DataOutputStream dataOutputStream) throws Exception{

        int bytes = 0;

        FileInputStream fIn = new FileInputStream(path);

        //write the length to the server ? wtf
        //dataOutputStream.writeLong(file.length());

        //i think this sets the max of each file reading to 4kb
        //this aint do fucking shit but messed up my fucking codes
        //more compact to use the buffer method to reduce the I/O usage i think
        byte[] buffer = new byte[4 * 1024];

        //it will read the files binary value i think but the point is reading
        //and it will write out to the dataOutputStream
        //the logic for this one is really flawed since there's no data at all for some fucking reason

        while ((bytes = fIn.read()) != -1) {
            // Send the file to Server Socket
            System.out.print( (char) bytes);
            //dataOutputStream.write(buffer, 0, bytes);
            dataOutputStream.write(bytes);
            dataOutputStream.flush();
        }

        //got dammint stan
//        fIn = new FileInputStream("C:/Users/Asus/Documents/codes/Java shits/File_transfer/testing_1.txt");
//
//        while ((bytes = fIn.read()) != -1) {
//            System.out.print((char) bytes);
//        }

        System.out.println("files uploaded and the data is transferred ");

        fIn.close();
        dataOutputStream.close();
    }

}
