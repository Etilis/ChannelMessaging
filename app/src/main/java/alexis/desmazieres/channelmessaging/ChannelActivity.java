package alexis.desmazieres.channelmessaging;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.HashMap;

public class ChannelActivity extends AppCompatActivity implements OnDownloadCompleteListener, AdapterView.OnItemClickListener {

    private ListView lvChannelList;
    private HashMap<String, String> postparams = new HashMap<>();
    private Channels listChannels;
    String url = "http://www.raphaelbischof.fr/messaging/?function=getchannels";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
        lvChannelList = (ListView) findViewById(R.id.lvChannels);
        SharedPreferences settings = getSharedPreferences(Downloader.PREFS_NAME, 0);
        String accesstoken = settings.getString("accesstoken", "");
        postparams.put("accesstoken", accesstoken);
        Downloader d = new Downloader(this, url, postparams);
        d.setListDownload(this);
        d.execute();
        lvChannelList.setOnItemClickListener(this);
    }

    @Override
    public void onDownloadCompleted(String content) {
        Gson gson = new Gson();
        listChannels = gson.fromJson(content, Channels.class);
        lvChannelList.setAdapter(new ArrayAdapterChannel(ChannelActivity.this, listChannels.getChannels()));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent myIntent = new Intent(getApplicationContext(), ChannelMessage.class);
        myIntent.putExtra("channelID", listChannels.getChannels().get(position).getchannelID());
        startActivity(myIntent);
    }
}
