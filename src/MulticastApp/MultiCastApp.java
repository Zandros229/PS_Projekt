package MulticastApp;

import Client.MulticastPublisher;
import Data.ChatMember;
import Server.MulticastReceiver;

public class MultiCastApp {
    private ChatMember chatMember;
    private MulticastReceiver multicastReceiver;
    private MulticastPublisher multicastPublisher;

    public MultiCastApp() {
        chatMember=new ChatMember();
        multicastPublisher= new MulticastPublisher();
        multicastReceiver= new MulticastReceiver();
    }


}
