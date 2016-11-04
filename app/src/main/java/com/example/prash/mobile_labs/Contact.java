package com.example.prash.mobile_labs;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Contact implements Serializable, Parcelable{
    public int id;
    public String lastName;
    public String firstName;
    public String phoneNum;
    private static final long serialVersionUID = -7668777571950573101L;

    public Contact(int id, String firstName, String lastName, String phoneNum) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNum = phoneNum;
    }

    public int getId(){
        return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel pc, int flags) {
        pc.writeInt(id);
        pc.writeString(firstName);
        pc.writeString(lastName);
        pc.writeString(phoneNum);
    }

    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>(){
        public Contact createFromParcel(Parcel pc){
            return new Contact(pc);
        }
        public Contact[] newArray(int size){
            return new Contact[size];
        }
    };

    /**Ctor from Parcel, reads back fields IN THE ORDER they were written */
    public Contact(Parcel pc){
        id = pc.readInt();
        firstName = pc.readString();
        lastName = pc.readString();
        phoneNum = pc.readString();
    }
}