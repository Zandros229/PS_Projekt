package MulticastApp;

import Client.MulticastPublisher;
import Data.ChatMember;
import Data.ComuniactMSG;
import Server.MulticastReceiver;

public class MultiCastApp {
    private ChatMember chatMember;
    private MulticastReceiver multicastReceiver;
    private MulticastPublisher multicastPublisher;
    private ComuniactMSG comuniactMSG;

    public MultiCastApp() {
        chatMember=new ChatMember();
        multicastPublisher= new MulticastPublisher();
        multicastReceiver= new MulticastReceiver();
    }

    public void StartChat(){

    }




}
