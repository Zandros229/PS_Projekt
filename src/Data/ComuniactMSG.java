package Data;

public class ComuniactMSG {
    public String msg;

    public ComuniactMSG() {
        msg=null;
    }

    synchronized public void SetMsg(String communicate){
        msg=communicate;
    }
}
