package com.example.prash.mobile_labs;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class Lab3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab3);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                //receive intent
                //String result = data.getStringExtra("result");
                Toast.makeText(getApplicationContext(), "Correct login!", Toast.LENGTH_LONG).show();
                Log.v("Toast","success");
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(),"Incorrect login", Toast.LENGTH_LONG).show();
                Log.v("Toast","failed");
            }
        }
    }

    public void sendLogin (View button) {
        Log.i("button_click","login button");
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent,1);
    }
    public void sendAbout (View button) {
        Log.i("button_click","about button");
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }
}
