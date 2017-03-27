package alexis.desmazieres.channelmessaging;

/**
 * Created by desmazia on 08/03/2017.
 */
public class Message {
    private String message;
    private String username;
    private String date;

    public String getUtilisateur() {
        return getUsername();
    }

    public void setUtilisateur(String utilisateur){ setUsername(utilisateur);    }

    public String getUsername(){return username;}
    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String image;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
