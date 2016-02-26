package com.example.richard.parking_lot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by richard on 2/19/2016.
 */
public class MarkedActivity extends Activity
        implements AdapterView.OnItemClickListener {

    private CheckBox checkBox;
    private ListView listView;
    private Firebase firebase;
    private ArrayList position_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marked_activity);

//        Intent intent = getIntent();
//        position_list = intent.getIntegerArrayListExtra("position_list");

//        System.out.println("position_list    "+position_list);

        Firebase.setAndroidContext(this);
        firebase = new Firebase("https://fanparking.firebaseio.com/");

        listView = (ListView) findViewById(R.id.marked_list);


        //        从firebase获取信息
        firebase.child("parking").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                System.out.println("12345676788899    ");
                ParkingAdapter parkingAdapter =
                        new ParkingAdapter(MarkedActivity.this, firebase, dataSnapshot,position_list);
                listView.setAdapter(parkingAdapter);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });



    }




    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
