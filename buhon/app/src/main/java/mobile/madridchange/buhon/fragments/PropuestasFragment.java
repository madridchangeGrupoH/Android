package mobile.madridchange.buhon.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import mobile.madridchange.buhon.R;
import mobile.madridchange.buhon.adapters.EventsBaseAdapter;
import mobile.madridchange.buhon.beans.Event;
import mobile.madridchange.buhon.helpers.Constants;
import mobile.madridchange.buhon.helpers.OnLineAsyncTask;
import mobile.madridchange.buhon.helpers.OnLineServicesHelper;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PropuestasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PropuestasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PropuestasFragment extends Fragment implements MyFragmentInterface {
    private static final String LOG_TAG = Constants.STR_LOG_TAG.concat("PropuestasFragment");
    private static final boolean isDebbud = Constants.isDebbud;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View rootView = null;

    private ListView lvEventosPropuestos = null;

    private SwipeRefreshLayout SRLayout = null;

    private EventsBaseAdapter eventsBaseAdapter = null;
    private List<Event> listaEventos = null;

    private OnFragmentInteractionListener mListener;

    public PropuestasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PropuestasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PropuestasFragment newInstance(String param1, String param2) {
        PropuestasFragment fragment = new PropuestasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_propuestas, container, false);

        lvEventosPropuestos = (ListView) rootView.findViewById(R.id.listView);
        lvEventosPropuestos.setVisibility(View.GONE);

        SRLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swiperefresh);
        SRLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                SRLayout.setRefreshing(false);
            }
        });


        buscarEventos();

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void fragmentBecameVisible() {
        StringBuffer msgLog = new StringBuffer("fragmentBecameVisible: ");
        Log.d(LOG_TAG, msgLog.toString());
    }



    private void buscarEventos(){
        if(SRLayout!=null)
            SRLayout.setRefreshing(true);

        new OnLineAsyncTask<String, List<Event>, String>("", ""){

            @Override
            protected List<Event> process(String input) {
                StringBuffer msgLog = new StringBuffer("process: ");
                msgLog.append(input);
                Log.d(LOG_TAG, msgLog.toString());

                return OnLineServicesHelper.getInstance().getEventosPropuestos();
            }

            @Override
            protected void applyOutputToTarget(List<Event> output, String target) {
                if (isDebbud) {
                    StringBuffer msgLog = new StringBuffer("applyOutputToTarget");
                    Log.d(LOG_TAG, msgLog.toString());
                }

                listaEventos = output;

                if(output!=null){
                    StringBuffer msgLog = new StringBuffer("output: ");
                    msgLog.append(output.toString());
                    Log.d(LOG_TAG, msgLog.toString());
                }

                pintarEventosPropuestos();
            }
        }.execute();

    }

    private void pintarEventosPropuestos(){
        if(listaEventos ==null)
            listaEventos = new ArrayList<Event>();

        if (isDebbud) {
            StringBuffer msgLog = new StringBuffer("pintarEventosPropuestos");
            msgLog.append(listaEventos.toString());
            Log.d(LOG_TAG, msgLog.toString());
        }

        eventsBaseAdapter = new EventsBaseAdapter(rootView.getContext(), listaEventos);

        lvEventosPropuestos.setVisibility(View.VISIBLE);

        lvEventosPropuestos.setAdapter(eventsBaseAdapter);

        lvEventosPropuestos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Event theVideoViewed = listaEventos.get(position);

                if (isDebbud) {
                    StringBuffer msgLog = new StringBuffer();
                    msgLog.append("position: ");
                    msgLog.append(position);
                    msgLog.append(", theVideo: ");
                    msgLog.append(theVideoViewed.toString());
                    Log.d(LOG_TAG, msgLog.toString());
                }
            }
        });

        if(SRLayout!=null)
            SRLayout.setRefreshing(false);
    }
}
