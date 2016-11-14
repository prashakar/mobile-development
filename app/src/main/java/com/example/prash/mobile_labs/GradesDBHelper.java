package com.example.prash.mobile_labs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by prash on 13/11/16.
 */

public class GradesDBHelper extends SQLiteOpenHelper {

    private Context context;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_FILENAME = "grades.db";

    private static String CREATE_STATEMENT = "" +
            "CREATE TABLE grades(" +
            "studentId int primary key," +
            "courseComponent varchar(100) not null," +
            "mark decimal not null)";

    private static String DROP_STATEMENT = "" +
            "DROP TABLE grades";

    public GradesDBHelper(Context context) {
        super(context, DATABASE_FILENAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_STATEMENT);
        db.execSQL(CREATE_STATEMENT);
    }

    public ArrayList<Grade> getAllGrades(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Grade> results = new ArrayList<>();

        String[] columns = new String[] {"studentId", "courseComponent", "mark"};
        String where = "";
        String[] whereArgs = new String[]{};
        String groupBy = "";
        String groupArgs = "";
        String orderBy = "studentId";

        Cursor cursor = db.query("grades", columns, where, whereArgs, groupBy, groupArgs, orderBy);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int studentId = cursor.getInt(0);
            String courseComponent = cursor.getString(1);
            float mark = cursor.getFloat(2);

            Grade grade = new Grade(studentId, courseComponent, mark);
            results.add(grade);

            cursor.moveToNext();
        }

        return results;
    }

    public Grade addNewGrade(int studentId, String courseComponent, float mark){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("studentId", studentId);
        values.put("courseComponent", courseComponent);
        values.put("mark", mark);
        db.insertOrThrow("grades", null, values);

        Grade grade = new Grade(studentId, courseComponent, mark);
        return grade;
    }

    public void deleteGrade(int studentId){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("grades", "studentId = ?", new String[]{""+studentId});
    }
}
