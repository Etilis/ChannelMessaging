package alexis.desmazieres.channelmessaging;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by desmazia on 30/01/2017.
 */
public class ArrayAdapterChannel extends ArrayAdapter<Channel> {
    private final Context context;
    private List<Channel> listChannel;
    //private final Channel[] values;

    public ArrayAdapterChannel(Context context, List<Channel> listChannel) {
        super(context, R.layout.activity_channel, listChannel);
        this.context = context;
        this.listChannel = listChannel;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_channel, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.lblTitre);
        ListView listView = (ListView) rowView.findViewById(R.id.lvChannels);
        textView.setText(this.getItemViewType(0));
        return rowView;
    }

}