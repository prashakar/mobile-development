package com.example.prash.mobile_labs;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by prash on 12/10/16.
 */

public class ShowLicense extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terms);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("data");
            System.out.println(value);
            //The key argument here must match that used in the other activity
            TextView text=(TextView)findViewById(R.id.text_placeholder);
            text.setText(value);
        }

    }

    public void close (View button) {
        finish();
    }
}
