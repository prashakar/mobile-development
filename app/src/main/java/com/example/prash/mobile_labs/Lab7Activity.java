package com.example.prash.mobile_labs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class Lab7Activity extends AppCompatActivity {

    public ArrayList<Grade> gradesList = new ArrayList<Grade>();
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab7);
        GradesAdapter adapter = new GradesAdapter(this, gradesList);
        ListView listview = (ListView) findViewById(R.id.lv);
        //ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.single_item,contactList );
        listview.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode== Activity.RESULT_OK) {
                //receive intent
                int studentId = Integer.valueOf(data.getStringExtra("studentId"));
                String courseComponent = data.getStringExtra("courseComponent");
                Float mark = Float.valueOf(data.getStringExtra("mark"));
                Grade grade = new Grade(studentId, courseComponent, mark);
                gradesList.add(grade);
                //id+=1;
                System.out.println(gradesList);
                ListView listview = (ListView) findViewById(R.id.lv);
                GradesAdapter adapter = new GradesAdapter(this, gradesList);
                listview.setAdapter(adapter);

            }
//        } else if (requestCode == 2) {
//            if (resultCode == Activity.RESULT_OK) {
//                int deleteContact = data.getIntExtra("selectedContact", -1);
//                System.out.println("deleting " + deleteContact);
//                for (Grade grade : gradesList) {
//                    if (grade.getStudentId() == deleteContact) {
//                        System.out.println("DELETED " + deleteContact);
//                        System.out.println(gradesList.size());
//                        gradesList.remove(grade);
//                        System.out.println(gradesList.size());
//                        break;
//                    }
//                }
//                GradesAdapter adapter = new GradesAdapter(this, gradesList);
//                ListView listview = (ListView) findViewById(R.id.lv);
//                //ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.single_item,contactList );
//                listview.setAdapter(adapter);
//            }
        }
    }
    public void addGrade (View button) {
        Log.i("button_click", "add grade button");
        Intent intent = new Intent(this, AddContact.class);
        startActivityForResult(intent, 1);
    }

//    public void deleteGrade (View button) {
//        Log.i("button_click", "add contact button");
//        Intent intent = new Intent(this, DeleteContact.class);
//        intent.putExtra("contacts",gradesList);
//        startActivityForResult(intent, 2);
//    }

}