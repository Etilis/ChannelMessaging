package alexis.desmazieres.channelmessaging;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by desmazia on 03/03/2017.
 */
public class ChannelMessage extends AppCompatActivity implements OnDownloadCompleteListener{

    private ListView messages;
    private TextView title;
    private HashMap<String, String> postparams = new HashMap<>();
    private String url = "http://www.raphaelbischof.fr/messaging/?function=getmessages";
    private Messages listMessages;
    private int channelID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        //ChannelList = (ListView) findViewById(R.id.lvChannels);
        title = (TextView) findViewById(R.id.txtTitleChannel);
        messages = (ListView) findViewById(R.id.listViewMessages);
        SharedPreferences settings = getSharedPreferences(Downloader.PREFS_NAME, 0);
        String accesstoken = settings.getString("accesstoken", "");
        postparams.put("accesstoken", accesstoken);
        channelID = getIntent().getIntExtra("channelID", 0);
        postparams.put("channelid", Integer.toString(channelID));
        Downloader d = new Downloader(this, url, postparams);
        d.setListDownload(this);
        d.execute();
    }

    @Override
    public void onDownloadCompleted(String content) {
        Gson gson = new Gson();
        listMessages = gson.fromJson(content, Messages.class);
        messages.setAdapter(new ArrayAdaptaterMessage(ChannelMessage.this, listMessages.getMessages()));
    }
}
