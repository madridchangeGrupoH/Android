package mobile.madridchange.buhon.adapters.beans;

import android.widget.TextView;

/**
 * Created by omar on 13/11/16.
 */

public class EventsBaseAdapterBean {
    private TextView txtName;
    private TextView txtDescription;

    public EventsBaseAdapterBean(TextView txtName, TextView txtDescription) {
        this.txtName = txtName;
        this.txtDescription = txtDescription;
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
