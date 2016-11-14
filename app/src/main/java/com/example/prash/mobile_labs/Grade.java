package com.example.prash.mobile_labs;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Grade implements Serializable, Parcelable{
    public int studentId;
    public String courseComponent;
    public float mark;
    //private static final long serialVersionUID = -7668777571950573101L;

    public Grade(int studentId, String courseComponent, float mark) {
        this.studentId = studentId;
        this.courseComponent = courseComponent;
        this.mark = mark;
    }

    public int getStudentId(){
        return studentId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel pc, int flags) {
        pc.writeInt(studentId);
        pc.writeString(courseComponent);
        pc.writeFloat(mark);
    }

    public static final Parcelable.Creator<Grade> CREATOR = new Parcelable.Creator<Grade>(){
        public Grade createFromParcel(Parcel pc){
            return new Grade(pc);
        }
        public Grade[] newArray(int size){
            return new Grade[size];
        }
    };

    /**Ctor from Parcel, reads back fields IN THE ORDER they were written */
    public Grade(Parcel pc){
        studentId = pc.readInt();
        courseComponent = pc.readString();
        mark = pc.readFloat();
    }
}