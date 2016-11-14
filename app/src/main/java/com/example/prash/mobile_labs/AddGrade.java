package com.example.prash.mobile_labs;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddGrade extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_grade);
    }
    public void onFinishClick(View button) {


        Intent returnIntent = new Intent();
        //used to send data back to main
        EditText studentId_edit = (EditText)findViewById(R.id.studentId);
        EditText courseComponent_edit = (EditText)findViewById(R.id.courseComponent);
        EditText mark_edit = (EditText)findViewById(R.id.mark);

        int studentId = Integer.valueOf(studentId_edit.getText().toString());
        String courseComponent = courseComponent_edit.getText().toString();
        float mark = Float.valueOf(mark_edit.getText().toString());

        Log.v("EditText", studentId + "");
        Log.v("EditText", courseComponent);
        Log.v("EditText", mark + "");

        try {
            GradesDBHelper gradesDBHelper = new GradesDBHelper(this);
            gradesDBHelper.addNewGrade(studentId, courseComponent, mark);
        } catch (SQLiteConstraintException e){
            Toast.makeText(this, "Student ID may already exist, try again!", Toast.LENGTH_SHORT).show();
        }

//        returnIntent.putExtra("studentId",str_studentId);
//        returnIntent.putExtra("courseComponent",str_courseComponent);
//        returnIntent.putExtra("mark",str_mark);
//
//        setResult(Activity.RESULT_OK, returnIntent);
//
        finish();
    }
}
