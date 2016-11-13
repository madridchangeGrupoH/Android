package mobile.madridchange.buhon.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import mobile.madridchange.buhon.R;
import mobile.madridchange.buhon.adapters.beans.EventsBaseAdapterBean;
import mobile.madridchange.buhon.beans.Event;
import mobile.madridchange.buhon.helpers.Constants;

/**
 * Created by omar on 13/11/16.
 */

public class EventsBaseAdapter extends BaseAdapter {
    private static final String LOG_TAG = Constants.STR_LOG_TAG.concat("EventsBaseAdapter");
    private static final boolean isDebbud = Constants.isDebbud;

    private Context context;
    private List<Event> eventosPropuestos;

    public EventsBaseAdapter(Context context, List<Event> videoViewed) {
        this.context = context;
        this.eventosPropuestos = videoViewed;
    }

    @Override
    public int getCount() {
        return eventosPropuestos.size();
    }

    @Override
    public Object getItem(int position) {
        return eventosPropuestos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return eventosPropuestos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Event eventoItemSelected = (Event) getItem(position);
        if (isDebbud) {
            StringBuffer msgLog = new StringBuffer("getView: ");
            msgLog.append(", eventoItemSelected: ");
            msgLog.append(eventoItemSelected.toString());
            Log.d(LOG_TAG, msgLog.toString());
        }

        View row;
        final EventsBaseAdapterBean itemEvent;

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = infalInflater.inflate(R.layout.fragment_list_events, parent, false);

            itemEvent = new EventsBaseAdapterBean(
                    (TextView) row.findViewById(R.id.miEvento),
                    (TextView) row.findViewById(R.id.miDescripcion),
                    (ImageView) row.findViewById(R.id.imageView)
            );

            row.setTag(itemEvent);
        }else{
            row = convertView;
            itemEvent = (EventsBaseAdapterBean) row.getTag();
        }

        itemEvent.getTxtName().setText(eventoItemSelected.getName());
        itemEvent.getTxtDescription().setText(eventoItemSelected.getDescription());

        return row;
    }
}
