package MulticastApp;

import Client.MulticastPublisher;
import Data.ChatMember;
import Data.ComuniactMSG;
import Data.WrongNickNameException;
import Server.MulticastAwaitReceiver;
import Server.MulticastReceiver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class MultiCastApp {
    private ChatMember chatMember;
    private MulticastAwaitReceiver multicastReceiver;
    private MulticastPublisher multicastPublisher;
    private ComuniactMSG comuniactMSG;

    public MultiCastApp() {
        chatMember = new ChatMember();
        comuniactMSG = new ComuniactMSG();
        multicastPublisher = new MulticastPublisher();
        multicastReceiver = new MulticastAwaitReceiver(comuniactMSG,chatMember,multicastPublisher);
    }

    public void StartChat(String nick) throws IOException, WrongNickNameException {
        String tempList = null;
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(multicastReceiver);
        String msg = null;
        try {
            tempList = future.get(10, TimeUnit.SECONDS);

        } catch (TimeoutException e) {
            if (tempList == null) {
                //System.out.println(tempList);
                future.cancel(true);
                executor.shutdown();
                this.chatMember.setNick(nick);
                System.out.println("set up nick");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (tempList != null) {

            System.out.println(tempList);
            try {
                if (tempList.split(" ")[2].equals("BUSY")) {
                    System.out.println("Choose another nickname");
                    throw new WrongNickNameException("Nick name Busy");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }

        //chatMember.setNick(nick);
    }

    public void SendNick() throws WrongNickNameException {
        System.out.println("Send nick to start chat");
        Scanner scanner = new Scanner(System.in);
        String myMSG = scanner.nextLine();
        String[] myMSGTABLE = myMSG.split(" ");
        try {
            if (myMSGTABLE[0].equals("NICK")) {
                multicastPublisher.multicast(myMSG);
                StartChat(myMSGTABLE[1]);
            } else {
                System.out.println("Wrong nick format");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("wrong nick format");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        //this.setAwait();
    }

    public void SendMSG() {
        System.out.println("Send msg with format MSG nick message");
        Scanner scanner = new Scanner(System.in);
        String myMSG = scanner.nextLine();
        String[] myMSGTABLE = myMSG.split(" ");
        try {
            if (myMSGTABLE[0].equals("MSG") && myMSGTABLE[1].equals(chatMember.getNick())) {
                multicastPublisher.multicast(myMSG);
            } else {
                System.out.println("Wrong msg format");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("wrong msg format");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setAwait() {
        //System.out.println("set await");
        String tempString = new String();
        MulticastAwaitReceiver multicastAwaitReceiver = new MulticastAwaitReceiver(comuniactMSG);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        FutureTask<String> futureTask = new FutureTask<>(multicastAwaitReceiver);
        ExecutorService executor= Executors.newSingleThreadExecutor();

        executor.execute(futureTask);

    }


}
