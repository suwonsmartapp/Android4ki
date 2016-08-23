package com.suwonsmartapp.android4ki.rx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.suwonsmartapp.android4ki.R;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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

    @Override
    public void onClick(View v) {

        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    Thread.sleep(5000);
                    subscriber.onNext("변경 됨");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        observable.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        mTextView.setText(s);
                    }
                });
    }

}
