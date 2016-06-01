package com.suwonsmartapp.android4ki.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.suwonsmartapp.android4ki.R;
import com.suwonsmartapp.android4ki.listview.retrofit.ContactAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListViewActivity extends AppCompatActivity {

    private static final String TAG = ListViewActivity.class.getSimpleName();

    private List<Contact> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        mData = new ArrayList<>();
        mData.add(new Contact("http://suwonsmartapp.iptime.org/test/twoong/retrofit/Picture1.png", "test"));

        final ListView listView = (ListView) findViewById(R.id.list_view);

        // retrofit 준비
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ContactAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ContactAPI api = retrofit.create(ContactAPI.class);

        Call<List<Contact>> contactCall = api.getContacts();

        contactCall.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                Log.d(TAG, "onResponse: ok");

                List<Contact> data = response.body();
                Log.d(TAG, "onResponse: " + data);

                mData = data;
                listView.setAdapter(new ContactAdapter(data));
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                Toast.makeText(ListViewActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
