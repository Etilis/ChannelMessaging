package alexis.desmazieres.channelmessaging;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChannelActivity extends AppCompatActivity implements OnDownloadCompleteListener, AdapterView.OnItemClickListener {

    private ListView ChannelList;
    private HashMap<String, String> postparams = new HashMap<>();
    private Channels listChannels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
        ChannelList = (ListView) findViewById(R.id.lvChannels);
        SharedPreferences settings = getSharedPreferences(Downloader.PREFS_NAME, 0);
        String accesstoken = settings.getString("accesstoken", "");
        postparams.put("accesstoken", accesstoken);
        String url = "http://www.raphaelbischof.fr/messaging/?function=getchannels";
        Downloader d = new Downloader(this, url, postparams);
        d.setListDownload(this);
        d.execute();
        ChannelList.setOnItemClickListener(this);
    }

    @Override
    public void onDownloadCompleted(String content) {
        Gson gson = new Gson();
        Channels channels = gson.fromJson(content, Channels.class);
        ChannelList.setAdapter(new ArrayAdapterChannel(this.getApplicationContext(), listChannels.getChannels()));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
