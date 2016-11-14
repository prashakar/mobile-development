package com.example.prash.mobile_labs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class DeleteGrade extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    SpinnerAdapter spinnerAdapter;
    ArrayList<Grade> grades;
    Grade selectedGrade;
    int selectedGradeId;
    GradesDBHelper gradesDBHelper = new GradesDBHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_grade);
        grades = gradesDBHelper.getAllGrades();
        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(this, grades);
        spinnerAdapter.setDropDownViewResource(R.layout.single_item_grades);
        spinner.setOnItemSelectedListener(this);
        spinner.setAdapter(spinnerAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedGrade = (Grade)parent.getItemAtPosition(position);
        selectedGradeId = selectedGrade.getStudentId();
        System.out.println(selectedGradeId);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void deleteGrade(View v){
        gradesDBHelper.deleteGrade(selectedGradeId);
        finish();
    }

    public class SpinnerAdapter extends ArrayAdapter<Grade> {

        public SpinnerAdapter(Context context, ArrayList<Grade> grades) {
            super(context, R.layout.single_item_grades, grades);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            Grade grade = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.single_item_grades, parent, false);
            }
            // Lookup view for data population
            TextView studentId = (TextView) convertView.findViewById(R.id.studentId);
            TextView courseComponent = (TextView) convertView.findViewById(R.id.courseComponent);
            TextView mark = (TextView) convertView.findViewById(R.id.mark);

            // Populate the data into the template view using the data object
            studentId.setText(String.valueOf(grade.studentId));
            courseComponent.setText(grade.courseComponent);
            mark.setText(String.valueOf(grade.mark));

            // Return the completed view to render on screen
            return convertView;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return getView(position, convertView, parent);
        }
    }
}
