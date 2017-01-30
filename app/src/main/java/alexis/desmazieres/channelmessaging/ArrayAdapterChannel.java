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

/**
 * Created by desmazia on 30/01/2017.
 */
public class ArrayAdapterChannel extends ArrayAdapter<Channel> {
    private final Context context;
    //private final Channel[] values;

    public ArrayAdapterChannel(Context context, ArrayList<Channel> channels) {
        super(context, R.layout.channels, channels);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.channels, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.lblTitre);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        textView.setText(this.getItemViewType(0));
        return rowView;
    }

}