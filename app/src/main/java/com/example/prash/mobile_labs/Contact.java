package com.example.prash.mobile_labs;

import java.io.Serializable;

public class Contact implements Serializable {
    public int id;
    public String lastName;
    public String firstName;
    public String phoneNum;

    public Contact(int id, String lastName, String firstName, String phoneNum) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNum = phoneNum;
    }
}