package com.suwonsmartapp.android4ki.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suwonsmartapp.android4ki.R;

/**
 * Created by junsuk on 16. 6. 20..
 */
public class ColorFragment2 extends Fragment implements View.OnClickListener {

    private OnClickMyListener mListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_color2, container, false);
        view.findViewById(R.id.red_button).setOnClickListener(this);
        view.findViewById(R.id.green_button).setOnClickListener(this);
        view.findViewById(R.id.blue_button).setOnClickListener(this);
        return view;
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnClickMyListener) {
//            mListener = (OnClickMyListener) context;
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    public void setOnMyClickListener(OnClickMyListener listener) {
        mListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            mListener.onClick(v, getView());
        }
    }

    public interface OnClickMyListener {
        void onClick(View clickedView, View colorChangeView);
    }
}
