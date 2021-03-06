package alexis.desmazieres.channelmessaging;

/**
 * Created by desmazia on 27/01/2017.
 */
public class Channel {
    int channelID;
    String name;
    int connectedusers;

    public Channel(int channelID, String name, int connectedusers) {
        this.channelID= channelID;
        this.name = name;
        this.connectedusers = connectedusers;
    }

    public int getChannelId() {
        return channelID;
    }

    public int getConnectedUser() {
        return connectedusers;
    }

    public String getName() {
        return name;
    }

    public int getchannelID() {
        return channelID;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "channelID=" + channelID +
                ", name='" + name + '\'' +
                ", connectedusers=" + connectedusers +
                '}';
    }
}
