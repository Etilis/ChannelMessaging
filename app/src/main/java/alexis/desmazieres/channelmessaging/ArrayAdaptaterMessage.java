package alexis.desmazieres.channelmessaging;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by desmazia on 08/03/2017.
 */
public class ArrayAdaptaterMessage extends ArrayAdapter<Message> {
    private final Context context;
    private List<Message> listmessage;
    //private final Channel[] values;

    public ArrayAdaptaterMessage(Context context, List<Message> listMessage) {
        super(context, R.layout.activity_message, listMessage);
        this.context = context;
        this.listmessage = listMessage;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.adaptater_message, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.tvNom);
        TextView textView2 = (TextView) rowView.findViewById(R.id.tvMessage);
        textView.setText(listmessage.get(position).getUtilisateur());
        textView2.setText(listmessage.get(position).getMessage());
        return rowView;
    }
}
