package Server;

import Data.ComuniactMSG;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastReceiver extends Thread {
    protected MulticastSocket socket = null;
    protected byte[] buf = new byte[256];
    private ComuniactMSG comuniactMSG;

    public MulticastReceiver(ComuniactMSG comuniactMSG) {
        this.comuniactMSG = comuniactMSG;
    }


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
                System.out.println(received);
                if ("end".equals(received)) {
                    break;
                }
            }

            socket.leaveGroup(group);
        } catch (IOException e) {
            System.out.printf(e.getMessage());
        }
        socket.close();
    }
}
