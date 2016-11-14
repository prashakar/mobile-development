package com.example.prash.mobile_labs;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class GradesAdapter extends ArrayAdapter<Grade> {
    public GradesAdapter(Context context, ArrayList<Grade> grades) {
        super(context, 0, grades);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Grade grade = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.single_item, parent, false);
        }
        // Lookup view for data population
        TextView studentId = (TextView) convertView.findViewById(R.id.studentId);
        TextView courseComponent = (TextView) convertView.findViewById(R.id.courseComponent);
        TextView mark = (TextView) convertView.findViewById(R.id.mark);

        // Populate the data into the template view using the data object
        studentId.setText(toString().valueOf(grade.studentId));
        courseComponent.setText(grade.courseComponent);
        mark.setText(toString().valueOf(grade.mark));

        // Return the completed view to render on screen
        return convertView;
    }
}