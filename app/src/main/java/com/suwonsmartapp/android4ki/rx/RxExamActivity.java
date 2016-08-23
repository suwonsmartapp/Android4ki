package com.suwonsmartapp.android4ki.rx;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.suwonsmartapp.android4ki.R;

import static com.suwonsmartapp.android4ki.R.id.text_view;

public class RxExamActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_exam);

        mTextView = (TextView) findViewById(text_view);

        findViewById(R.id.button).setOnClickListener(this);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mTextView.setText((String)msg.obj);
        }
    };

    @Override
    public void onClick(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    Message msg = new Message();
                    msg.obj = "변경 됨";
                    mHandler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
