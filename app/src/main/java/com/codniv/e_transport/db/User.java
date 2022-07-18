package com.codniv.e_transport.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name="full_name")
    public String full_name;

    @ColumnInfo(name = "phone_number")
    public String phone_number;

    @ColumnInfo(name="staff")
    public boolean staff;

    public User(String full_name, String phone_number , boolean staff){
        this.full_name = full_name;
        this.phone_number = phone_number;
        this.staff =staff;
    }
}
