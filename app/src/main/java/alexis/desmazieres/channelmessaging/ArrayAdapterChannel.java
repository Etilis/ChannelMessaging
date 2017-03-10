package alexis.desmazieres.channelmessaging;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by desmazia on 30/01/2017.
 */
public class ArrayAdapterChannel extends BaseAdapter {

    private static final String TAG = "ArrayAdapterChannel";

    private final Context context;
    private List<Channel> listChannel;
    //private final Channel[] values;

    public ArrayAdapterChannel(Context context, List<Channel> listChannel) {
        super();
        this.context = context;
        this.listChannel = listChannel;
    }

    @Override
    public int getCount() {
        return listChannel.size();
    }

    @Override
    public Object getItem(int position) {
        return listChannel.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listChannel.get(position).getChannelId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.adapter_channel, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.tvChannel);
        textView.setText(listChannel.get(position).getName());
        TextView textView2 = (TextView) rowView.findViewById(R.id.tvConnectedUser);
        textView2.setText("Nombre d'utilisateur connectés : " + listChannel.get(position).getConnectedUser());
        return rowView;
    }

}