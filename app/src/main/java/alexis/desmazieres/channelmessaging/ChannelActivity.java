package alexis.desmazieres.channelmessaging;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChannelActivity extends AppCompatActivity implements OnDownloadCompleteListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);


        Downloader d = new Downloader(this);
        d.setListDownload(this);
        d.execute();
    }

    @Override
    public void onDownloadCompleted(String content) {
        Gson gson = new Gson();
        Channels channels = gson.fromJson(content, Channels.class);


        ListView listview = (ListView) this.findViewById(R.id.listView);
        ArrayAdapterChannel tvs = new ArrayAdapterChannel(this.getApplicationContext(), channels);


        TextView tv;

        for (Channel channel : channels.getChannels())
        {
            tv = new TextView(this);
            tv.setText(channel.name);
            tvs.add(tv);
        }


        listview.setAdapter(tvs);

        listview.invalidate();
        /*
        for (Channel channel: channels.getChannels())
        {


        }
        */

    }


}
