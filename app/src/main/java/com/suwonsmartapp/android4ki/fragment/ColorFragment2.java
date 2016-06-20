package com.suwonsmartapp.android4ki.fragment;

import android.graphics.Color;
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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_color2, container, false);
        view.findViewById(R.id.red_button).setOnClickListener(this);
        view.findViewById(R.id.green_button).setOnClickListener(this);
        view.findViewById(R.id.blue_button).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.red_button:
                getView().setBackgroundColor(Color.RED);
                break;
            case R.id.green_button:
                getView().setBackgroundColor(Color.GREEN);
                break;
            case R.id.blue_button:
                getView().setBackgroundColor(Color.BLUE);
                break;
        }
    }
}
