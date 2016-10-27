package com.example.prash.mobile_labs;

import android.content.Intent;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Lab6Activity extends AppCompatActivity {

    public ArrayList<Contact> contactList = new ArrayList<Contact>();
    private int id = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab6);
        try{
            System.out.println("reading file");
            String filePath = this.getFilesDir().getPath().toString() + "/contactData.txt";
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            contactList = (ArrayList<Contact>)in.readObject();
        }catch(IOException i){
            i.printStackTrace();
        }catch (ClassNotFoundException i){
            i.printStackTrace();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        try{
            System.out.println("writing file");
            String filePath = this.getFilesDir().getPath().toString() + "/contactData.txt";
            System.out.println(filePath);
            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(contactList);
            out.close();
            fileOut.close();
        }catch(IOException i){
            i.printStackTrace();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        try{
            System.out.println("reading file");
            String filePath = this.getFilesDir().getPath().toString() + "/contactData.txt";
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            contactList = (ArrayList<Contact>)in.readObject();
        }catch(IOException i){
            i.printStackTrace();
        }catch (ClassNotFoundException i){
            i.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode==Activity.RESULT_OK) {
                //receive intent
                String firstName = data.getStringExtra("firstName");
                String lastName = data.getStringExtra("lastName");
                String phoneNumber = data.getStringExtra("phoneNumber");
                Contact contact = new Contact(id, firstName, lastName, phoneNumber);
                contactList.add(contact);
                id+=1;
                System.out.println(contactList);
                ListView listview = (ListView) findViewById(R.id.lv);
                UsersAdapter adapter = new UsersAdapter(this, contactList);

                //ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.single_item,contactList );
                listview.setAdapter(adapter);
            }
        }
    }
    public void addContact (View button) {
        Log.i("button_click", "add contact button");
        Intent intent = new Intent(this, AddContact.class);
        startActivityForResult(intent, 1);
    }


}

