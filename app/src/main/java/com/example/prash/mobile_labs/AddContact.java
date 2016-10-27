package com.example.prash.mobile_labs;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class AddContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact);
    }
    public void onFinishClick(View button) {
        Intent returnIntent = new Intent();
        //used to send data back to main
        EditText firstName = (EditText)findViewById(R.id.firstName);
        EditText lastName = (EditText)findViewById(R.id.lastName);
        EditText phoneNumber = (EditText)findViewById(R.id.phoneNumber);

        String str_firstName = firstName.getText().toString();
        String str_lastName = lastName.getText().toString();
        String str_phoneNumber = phoneNumber.getText().toString();

        Log.v("EditText", str_firstName);
        Log.v("EditText", str_lastName);
        Log.v("EditText", str_phoneNumber);

        returnIntent.putExtra("firstName",str_firstName);
        returnIntent.putExtra("lastName",str_lastName);
        returnIntent.putExtra("phoneNumber",str_phoneNumber);

        setResult(Activity.RESULT_OK, returnIntent);

        finish();
    }
}
