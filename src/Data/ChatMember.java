package Data;

public class ChatMember {
    private String nick;
    private String room;


    public ChatMember(String nick, String room) {
        this.nick = nick;
        this.room = room;
    }

    public ChatMember() {
        nick=null;
        room=null;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
