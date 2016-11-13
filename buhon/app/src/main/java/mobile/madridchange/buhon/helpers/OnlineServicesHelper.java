package mobile.madridchange.buhon.helpers;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mobile.madridchange.buhon.beans.Event;

/**
 * Created by omar on 13/11/16.
 */
public class OnLineServicesHelper {
    private static final String LOG_TAG = Constants.STR_LOG_TAG.concat("OnLineServicesHelper: ");
    private static final boolean isDebbud = Constants.isDebbud;

    private static OnLineServicesHelper ourInstance = new OnLineServicesHelper();

    public static OnLineServicesHelper getInstance() {
        return ourInstance;
    }

    private OnLineServicesHelper() {
    }

    public List<Event> getEventosPropuestos() {

        return getJsonData();
    }

    private List<Event> getJsonData(){
        List<Event> response = null;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Event[] datos = objectMapper.readValue(Constants.STR_JSON_SAMPLE, Event[].class);
            if(datos!=null){
                if(datos.length>0)
                    response = new ArrayList<Event>();

                for (Event itemVideoList : datos) {
                    response.add(itemVideoList);

                    if (isDebbud) {
                        StringBuffer msgLog = new StringBuffer("response: ");
                        msgLog.append(itemVideoList.toString());
                        Log.d(LOG_TAG, msgLog.toString());
                    }
                }

            }
        } catch (IOException e) {

        }


        return response;

    }
}
