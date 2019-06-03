import Client.MulticastPublisher;
import Server.MulticastReceiver;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String temp;
        MulticastReceiver multicastServer=new MulticastReceiver();
        multicastServer.start();
        while(true){
            temp=scanner.nextLine();
            if(temp.equals("start")){
                MulticastPublisher multicastClient=new MulticastPublisher();
                try {
                    multicastClient.multicast("Witam");
                }catch(IOException e){
                    System.out.printf(e.getMessage());
                }
            }
        }
    }
}
