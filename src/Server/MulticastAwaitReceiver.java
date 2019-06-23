package Server;

import Data.ComuniactMSG;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class MulticastAwaitReceiver implements Callable<String> {
    protected MulticastSocket socket = null;
    protected byte[] buf = new byte[256];
    private ComuniactMSG comuniactMSG;

    public MulticastAwaitReceiver(ComuniactMSG comuniactMSG) {
        this.comuniactMSG = comuniactMSG;
    }


//    public void run() {
//        try {
//            socket = new MulticastSocket(4446);
//            InetAddress group = InetAddress.getByName("230.0.0.0");
//            socket.joinGroup(group);
//            while (true) {
//                DatagramPacket packet = new DatagramPacket(buf, buf.length);
//                socket.receive(packet);
//                String received = new String(
//                        packet.getData(), 0, packet.getLength());
//                comuniactMSG.SetMsg(received);
//                System.out.println(received);
//                if ("end".equals(received)) {
//                    break;
//                }
//            }
//
//            socket.leaveGroup(group);
//        } catch (IOException e) {
//            System.out.printf(e.getMessage());
//        }
//        socket.close();
//    }

    @Override
    public String call() throws Exception {
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
                System.out.println(received+" from receiver");
                if (comuniactMSG.msg!=null) {
                    break;
                }
            }

            socket.leaveGroup(group);
        } catch (IOException e) {
            System.out.printf(e.getMessage());
            return e.getMessage();
        }
        socket.close();
        return comuniactMSG.msg;
    }
}
