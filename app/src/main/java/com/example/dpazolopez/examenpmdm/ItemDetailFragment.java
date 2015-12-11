package com.example.dpazolopez.examenpmdm;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.dpazolopez.examenpmdm.dummy.DummyContent;

public class ItemDetailFragment extends Fragment implements View.OnClickListener {

    public static final String ARG_ITEM_ID = "item_id";


    private DummyContent.DummyItem mItem;


    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {

            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.content);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);

        //8 RECOGEMOS TEXTVIEW Y LO PONEMOS A CEERO
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.textView)).setText("Item " + mItem.id);
        }


        Button button = (Button) rootView.findViewById(R.id.button);
        button.setOnClickListener(this);

        return rootView;
    }

    //9 ACCION PARA EL CLICK DEL BOTON
    @Override
    public void onClick(View v) {
        mListener.cerrar();
    }

    private MyFragmentListener mListener;

    //10 DEFINIMOS UNA INTERFAZ PARA PODER DECIRLE A LAS ACTIVITY QUE ESTE FRAGMENT SE CIERRE
    public interface MyFragmentListener {
        public void cerrar();
    }

    //11 ESTO SE EJECUTARA CUANDO ESTE FRAGMENT SEA UNIDO A UNA ACTIVITY, EN CONTEXT  SE PASA LA ACTIVITY
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (MyFragmentListener) context;
    }
}
