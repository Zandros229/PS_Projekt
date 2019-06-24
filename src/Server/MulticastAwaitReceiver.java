package Server;

import Client.MulticastPublisher;
import Data.ChatMember;
import Data.ComuniactMSG;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class MulticastAwaitReceiver implements Runnable {
    protected MulticastSocket socket = null;
    protected byte[] buf = new byte[256];
    private ComuniactMSG comuniactMSG;
    private ChatMember chatMember;
    private MulticastPublisher multicastPublisher;

    public MulticastAwaitReceiver(ComuniactMSG comuniactMSG) {
        this.comuniactMSG = comuniactMSG;
    }

    public MulticastAwaitReceiver(ComuniactMSG comuniactMSG, ChatMember chatMember, MulticastPublisher multicastPublisher) {
        this.comuniactMSG = comuniactMSG;
        this.chatMember = chatMember;
        this.multicastPublisher = multicastPublisher;
    }

    @Override
    public void run() {

        try {
            socket = new MulticastSocket(4446);
            InetAddress group = InetAddress.getByName("230.0.0.0");
            socket.joinGroup(group);
            while (true) {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String received = new String(
                        packet.getData(), 0, packet.getLength());
                comuniactMSG.SetMsg(received);
                //System.out.println(received+" from receiver");
                info(received);
                    if (comuniactMSG.msg != null) {
                        break;
                    }
            }

            socket.leaveGroup(group);
        } catch (IOException e) {
            System.out.printf(e.getMessage());

        }
        socket.close();

    }

    private void info(String tempString) {
        String[] msg = tempString.split(" ");
        //System.out.println(msg[0]);
        if (msg[0].equals("NICK")) {
            try {
                //System.out.println(msg[1]);
                if (msg[1].equals(chatMember.getNick())) {
                    System.out.println("Nick busy");
                    multicastPublisher.multicast("NICK " + chatMember.getNick() + " BUSY");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else if (msg[0].equals("MSG")) {
            comuniactMSG.nick = msg[1];
            comuniactMSG.msg = msg[2];
            if (!(comuniactMSG.nick.equals(chatMember.getNick()))) {
                //System.out.println(comuniactMSG.nick + " " + chatMember.getNick());
                System.out.println(comuniactMSG);
            }
        }
    }
}
