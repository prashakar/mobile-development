package com.example.prash.mobile_labs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.app.Activity;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Log.i("onCreate","opened!");
    }
    public void onFinishClick(View button) {
        Intent returnIntent = new Intent();
        //used to send data back to main
        //returnIntent.putExtra("result",result);
        EditText username = (EditText)findViewById(R.id.username);
        EditText pass = (EditText)findViewById(R.id.password);
        String str_username = username.getText().toString();
        String str_pass = pass.getText().toString();
        Log.v("EditText", username.getText().toString());
        Log.v("EditText", pass.getText().toString());
        if(str_username.equals("prash") && str_pass.equals("abc")) {
            setResult(Activity.RESULT_OK, returnIntent);
        } else {
            setResult(Activity.RESULT_CANCELED, returnIntent);
        }
        finish();
    }
}
