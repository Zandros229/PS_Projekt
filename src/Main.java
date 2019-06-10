import Client.MulticastPublisher;
import Data.WrongNickNameException;
import MulticastApp.MultiCastApp;
import Server.MulticastReceiver;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        MultiCastApp multiCastApp = new MultiCastApp();
        Scanner scanner = new Scanner(System.in);
        String myInput = null;
        Boolean enterchat = false;

        while (true) {

            if (!enterchat) {
                System.out.println("Write enter to enter chat");
                myInput = scanner.nextLine();
                if (myInput.equals("enter")) {
                    try {
                        multiCastApp.SendNick();
                        enterchat=true;
                    } catch (WrongNickNameException e) {
                        System.out.println("Nick name busy choose another one");
                    }
                }
            }else{
                System.out.println("Write msg to send message to the chat");
                myInput = scanner.nextLine();
                if(myInput.equals("msg")){
                    multiCastApp.SendMSG();
                }
            }

        }
    }
}

