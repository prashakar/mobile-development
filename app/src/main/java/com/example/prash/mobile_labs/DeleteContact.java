package com.example.prash.mobile_labs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class DeleteContact extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    SpinnerAdapter spinnerAdapter;
    ArrayList<Contact> contacts;
    int selectedContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_contact);
        contacts = getIntent().getParcelableArrayListExtra("contacts");
        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(this, contacts);
        spinnerAdapter.setDropDownViewResource(R.layout.single_item);
        spinner.setAdapter(spinnerAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedContact = Contact.getId();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void deleteContact(View v){
        Intent returnIntent = new Intent();
        returnIntent.putExtra("selectedContact", (Parcelable) selectedContact);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    public class SpinnerAdapter extends ArrayAdapter<Contact> {
        public SpinnerAdapter(Context context, ArrayList<Contact> contacts) {
            super(context, R.layout.single_item, contacts);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            Contact contact = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.single_item, parent, false);
            }
            // Lookup view for data population
            TextView id = (TextView) convertView.findViewById(R.id.id);
            TextView firstName = (TextView) convertView.findViewById(R.id.firstName);
            TextView lastName = (TextView) convertView.findViewById(R.id.lastName);
            TextView phoneNumber = (TextView) convertView.findViewById(R.id.phoneNumber);

            // Populate the data into the template view using the data object
            id.setText(toString().valueOf(contact.id));
            firstName.setText(contact.firstName);
            lastName.setText(contact.lastName);
            phoneNumber.setText(contact.phoneNum);

            // Return the completed view to render on screen
            return convertView;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return getView(position, convertView, parent);
        }

    }
}
