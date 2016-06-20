
package com.suwonsmartapp.android4ki.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.suwonsmartapp.android4ki.R;

public class FragmentExamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_exam);

        final ColorFragment2 colorFragment2 = new ColorFragment2();

        colorFragment2.setOnMyClickListener(new ColorFragment2.OnClickMyListener() {
            @Override
            public void onClick(View clickedView, View colorChangeView) {
                switch (clickedView.getId()) {
                    case R.id.red_button:
                        colorChangeView.setBackgroundColor(Color.RED);
                        break;
                    case R.id.green_button:
                        colorChangeView.setBackgroundColor(Color.GREEN);
                        break;
                    case R.id.blue_button:
                        getSupportFragmentManager().beginTransaction().remove(colorFragment2)
                                .commit();
                        break;
                }
            }
        });

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contents, colorFragment2)
                .commit();
    }
}
