package com.example.prash.mobile_labs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("onCreate","opened!");
    }
    public void openLab(View button) {
        String btn_clicked = button.getTag().toString();
        Log.v("clicked","btn_clicked");
        switch (btn_clicked){
            case "lab3":
                Intent intent = new Intent(this, Lab3Activity.class);
                startActivity(intent);
                Log.v("switch_activity","lab3");
                break;
            case "lab4":
                intent = new Intent(this, Lab4Activity.class);
                startActivity(intent);
                Log.v("switch_activity","lab4");
                break;
            case "lab5":
                intent = new Intent(this, Lab5Activity.class);
                startActivity(intent);
                Log.v("switch_activity","lab5");
                break;
            case "lab6":
                intent = new Intent(this, Lab6Activity.class);
                startActivity(intent);
                Log.v("switch_activity","lab6");
                break;
        }

    }
}
