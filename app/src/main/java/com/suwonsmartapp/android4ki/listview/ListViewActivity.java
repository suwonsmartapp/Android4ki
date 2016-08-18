package com.suwonsmartapp.android4ki.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.suwonsmartapp.android4ki.R;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    private static final String TAG = ListViewActivity.class.getSimpleName();

    private List<Contact> mData;
    private RecyclerView mRecyclerView;
    private ContactRecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        mData = new ArrayList<>();
        mData.add(new Contact("http://suwonsmartapp.iptime.org/test/twoong/retrofit/Picture1.png", "test"));
        mData.add(new Contact("http://suwonsmartapp.iptime.org/test/twoong/retrofit/Picture1.png", "test"));
        mData.add(new Contact("http://suwonsmartapp.iptime.org/test/twoong/retrofit/Picture1.png", "test"));
        mData.add(new Contact("http://suwonsmartapp.iptime.org/test/twoong/retrofit/Picture1.png", "test"));
        mData.add(new Contact("http://suwonsmartapp.iptime.org/test/twoong/retrofit/Picture1.png", "test"));
        mData.add(new Contact("http://suwonsmartapp.iptime.org/test/twoong/retrofit/Picture1.png", "test"));
        mData.add(new Contact("http://suwonsmartapp.iptime.org/test/twoong/retrofit/Picture1.png", "test"));
        mData.add(new Contact("http://suwonsmartapp.iptime.org/test/twoong/retrofit/Picture1.png", "test"));
        mData.add(new Contact("http://suwonsmartapp.iptime.org/test/twoong/retrofit/Picture1.png", "test"));
        mData.add(new Contact("http://suwonsmartapp.iptime.org/test/twoong/retrofit/Picture1.png", "test"));

        mRecyclerView = (RecyclerView) findViewById(R.id.list_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ContactRecyclerAdapter(mData);
        mRecyclerView.setAdapter(mAdapter);

        // retrofit 준비
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(ContactAPI.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        ContactAPI api = retrofit.create(ContactAPI.class);
//
//        Call<List<Contact>> contactCall = api.getContacts();
//
//        contactCall.enqueue(new Callback<List<Contact>>() {
//            @Override
//            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
//                Log.d(TAG, "onResponse: ok");
//
//                List<Contact> data = response.body();
//                Log.d(TAG, "onResponse: " + data);
//
//                mData = data;
//                mRecyclerView.setAdapter(new ContactAdapter(data));
//            }
//
//            @Override
//            public void onFailure(Call<List<Contact>> call, Throwable t) {
//                Toast.makeText(ListViewActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });

        Log.d(TAG, "onCreate: ");

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.putInt("position", mRecyclerView.getFirstVisiblePosition());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        int position = savedInstanceState.getInt("position");
//        mRecyclerView.setSelection(position);
    }
}
