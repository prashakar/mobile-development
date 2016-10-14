package com.example.prash.mobile_labs;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Lab5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab5);
    }

    public void terms_btn (View button) {
        GetData data = new GetData();
        data.execute();

    }

    private class GetData extends AsyncTask<String, Void, String> {
        private Exception exception = null;
        @Override
        protected String doInBackground(String... params) {
            //do background tasks here
            String str_result = "";
            try {
                URL url = new URL("https://www.gnu.org/licenses/gpl.txt");
                HttpURLConnection conn;
                conn = (HttpURLConnection) url.openConnection();
                int result = conn.getResponseCode();
                if (result == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line = null;
                    StringBuilder out = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        str_result+=line+"\n";
                    }
                    //str_result = out.toString();
                    Log.v("output",str_result);

                }
            }catch (Exception e) {
                e.printStackTrace();
            }
            return str_result;
        }
        @Override
        protected void onPostExecute(String result){
            if (exception != null) {
                exception.printStackTrace();
            }
            Intent intent = new Intent(Lab5Activity.this, ShowLicense.class);
            intent.putExtra("data",result);
            startActivity(intent);

        }

    }
}



