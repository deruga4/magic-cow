package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent intent = getIntent();

        Bundle extras = intent.getExtras();
        if (extras != null){
            Log.d("CONDITION", "Extras are not null");
            if (extras.containsKey(MainActivity.EXTRA_LIST_NAMES_MESSAGE)){
                Log.d("STATE", extras.get(MainActivity.EXTRA_LIST_NAMES_MESSAGE).toString());
                ArrayList<String> listNames = (ArrayList<String>) extras.get(MainActivity.EXTRA_LIST_NAMES_MESSAGE);

                // use this setting to improve performance if you know that changes
                // in content do not change the layout size of the RecyclerView
                recyclerView.setHasFixedSize(true);

                // use a linear layout manager
                layoutManager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(layoutManager);

                // specify an adapter (see also next example)
                mAdapter = new MyAdapter(listNames);
                recyclerView.setAdapter(mAdapter);
            }
            else{
                TextView text = findViewById(R.id.emptyListNamesText);
                text.setText("No lists yet! Tap + to create one.");
            }
//    Log.d("STATE", "what is this lol" + namesList);
//    Log.d("STATE", intent.getStringExtra(MainActivity.EXTRA_LIST_NAMES_MESSAGE).toString());


        }
    }
