import Client.MulticastClient;
import Server.MulticastServer;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String temp;
        MulticastServer multicastServer=new MulticastServer();
        multicastServer.start();
        while(true){
            temp=scanner.nextLine();
            if(temp.equals("start")){
                MulticastClient multicastClient=new MulticastClient();
                try {
                    multicastClient.multicast("Witam");
                }catch(IOException e){
                    System.out.printf(e.getMessage());
                }
            }
        }
    }
}
