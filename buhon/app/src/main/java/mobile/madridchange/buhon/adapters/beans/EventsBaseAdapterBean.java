package mobile.madridchange.buhon.adapters.beans;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by omar on 13/11/16.
 */

public class EventsBaseAdapterBean {
    private TextView txtName;
    private TextView txtDescription;
    private ImageView imagenEvento;

    public ImageView getImagenEvento() {
        return imagenEvento;
    }

    public void setImagenEvento(ImageView imagenEvento) {
        this.imagenEvento = imagenEvento;
    }

    public EventsBaseAdapterBean(TextView txtName, TextView txtDescription, ImageView imagenEvento) {
        this.txtName = txtName;
        this.txtDescription = txtDescription;
        this.imagenEvento = imagenEvento;
    }

    public TextView getTxtName() {
        return txtName;
    }

    public void setTxtName(TextView txtName) {
        this.txtName = txtName;
    }

    public TextView getTxtDescription() {
        return txtDescription;
    }

    public void setTxtDescription(TextView txtDescription) {
        this.txtDescription = txtDescription;
    }
}
