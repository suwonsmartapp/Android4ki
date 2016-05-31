package com.suwonsmartapp.android4ki.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.suwonsmartapp.android4ki.R;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        ListView listView = (ListView) findViewById(R.id.list_view);

        List<Contact> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            data.add(new Contact("", "아무개 " + i));
        }

        ContactAdapter adapter = new ContactAdapter(data);

        listView.setAdapter(adapter);
    }
}
