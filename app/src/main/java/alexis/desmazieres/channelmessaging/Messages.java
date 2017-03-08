package alexis.desmazieres.channelmessaging;

import java.util.ArrayList;

/**
 * Created by desmazia on 08/03/2017.
 */
public class Messages {
    ArrayList<Message> messages;

    public Messages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }


    public ArrayList<String> messagesArrayList(){
        ArrayList<String> messages = new ArrayList<String>();

        return messages;
    }
}
