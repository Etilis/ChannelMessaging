package alexis.desmazieres.channelmessaging;

import java.util.ArrayList;

/**
 * Created by desmazia on 03/03/2017.
 */
public class ChannelMessages{
    public ChannelMessages(ArrayList<ChannelMessage> messages) {
        this.messages = messages;
    }

    public ArrayList<ChannelMessage> getMessages() {
        return messages;
    }

    private ArrayList<ChannelMessage> messages;
}
