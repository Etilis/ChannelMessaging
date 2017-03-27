package alexis.desmazieres.channelmessaging;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by desmazia on 03/03/2017.
 */
public class ChannelMessage extends AppCompatActivity implements OnDownloadCompleteListener, View.OnClickListener{

    private ListView messages;
    private TextView title;
    private HashMap<String, String> postparams = new HashMap<>();
    private String url = "http://www.raphaelbischof.fr/messaging/?function=getmessages";
    private String urlEnvoie = "http://www.raphaelbischof.fr/messaging/?function=sendmessage";
    private Messages listMessages;
    private int channelID;
    private Button btnEnvoyer;
    private TextView txtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        //ChannelList = (ListView) findViewById(R.id.lvChannels);
        title = (TextView) findViewById(R.id.txtTitleChannel);
        messages = (ListView) findViewById(R.id.listViewMessages);
        btnEnvoyer = (Button) findViewById(R.id.btnEnvoyer);
        txtMessage = (EditText) findViewById(R.id.txtMessage);
        SharedPreferences settings = getSharedPreferences(Downloader.PREFS_NAME, 0);
        String accesstoken = settings.getString("accesstoken", "");
        postparams.put("accesstoken", accesstoken);
        channelID = getIntent().getIntExtra("channelID", 0);
        postparams.put("channelid", Integer.toString(channelID));
        Downloader d = new Downloader(this, url, postparams);
        d.setListDownload(this);
        d.execute();
        btnEnvoyer.setOnClickListener(this);
    }

    @Override
    public void onDownloadCompleted(String content) {
        Gson gson = new Gson();
        listMessages = gson.fromJson(content, Messages.class);
        messages.setAdapter(new ArrayAdaptaterMessage(ChannelMessage.this, listMessages.getMessages()));
    }

    @Override
    public void onClick(View v) {
        String message = txtMessage.getText().toString();
        postparams.put("message", message);
        Downloader d = new Downloader(this, this.urlEnvoie , this.postparams);
        d.setListDownload(this);
        d.execute();
        txtMessage.setText(null);
    }
}
