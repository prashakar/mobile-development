package com.example.prash.mobile_labs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class Lab7Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab7);
    }

    @Override
    protected void onStart(){
        super.onStart();
        GradesDBHelper gradesDBHelper = new GradesDBHelper(this);
        ArrayList<Grade> allGrades = gradesDBHelper.getAllGrades();
        GradesAdapter adapter = new GradesAdapter(this, allGrades);
        ListView listview = (ListView) findViewById(R.id.lv);
        //ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.single_item,contactList );
        listview.setAdapter(adapter);
    }

    public void addGrade (View button) {
        Log.i("button_click", "add grade button");
        Intent intent = new Intent(this, AddGrade.class);
        startActivity(intent);
    }

    public void deleteGrade (View button) {
        Log.i("button_click", "delete grade button");
        Intent intent = new Intent(this, DeleteGrade.class);
        startActivity(intent);
    }

}