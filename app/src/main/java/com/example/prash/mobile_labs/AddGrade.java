package com.example.prash.mobile_labs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class AddGrade extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_grade);
    }
    public void onFinishClick(View button) {
        Intent returnIntent = new Intent();
        //used to send data back to main
        EditText studentId = (EditText)findViewById(R.id.studentId);
        EditText courseComponent = (EditText)findViewById(R.id.courseComponent);
        EditText mark = (EditText)findViewById(R.id.mark);

        String str_studentId = studentId.getText().toString();
        String str_courseComponent = courseComponent.getText().toString();
        String str_mark = mark.getText().toString();

        Log.v("EditText", str_studentId);
        Log.v("EditText", str_courseComponent);
        Log.v("EditText", str_mark);

        returnIntent.putExtra("studentId",str_studentId);
        returnIntent.putExtra("courseComponent",str_courseComponent);
        returnIntent.putExtra("mark",str_mark);

        setResult(Activity.RESULT_OK, returnIntent);

        finish();
    }
}
