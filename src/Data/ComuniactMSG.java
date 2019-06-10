package Data;

public class ComuniactMSG {
    public String msg;
    public String nick;

    public ComuniactMSG() {
        msg=null;
    }

    synchronized public void SetMsg(String communicate){
        msg=communicate;
    }

    @Override
    public String toString() {
        return nick+" msg: "+msg;
    }
}
