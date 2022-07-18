package com.codniv.e_transport.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Journey {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name="journey_date")
    public String journey_date;

    @ColumnInfo(name="latitude")
    public double latitude;

    @ColumnInfo(name="longitude")
    public double longitude;

    @ColumnInfo(name="distance")
    public double distance;

    @ColumnInfo(name="journey")
    public int journey;

    public Journey(String journey_date,double latitude, double longitude,double distance ,int journey){
        this.journey_date = journey_date;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
        this.journey = journey;
    }
}
