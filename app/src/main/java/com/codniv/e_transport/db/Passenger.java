package com.codniv.e_transport.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class  Passenger {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name="start_time")
    public String start_time;

    @ColumnInfo(name="journey_start_id")
    public int journey_start_id;

    @ColumnInfo(name="journey_end_id")
    public int journey_end_id;

    @ColumnInfo(name="end_time")
    public String end_time;

    @ColumnInfo(name="distance")
    public double distance;

    @ColumnInfo(name="fare")
    public double fare;

    @ColumnInfo(name="status")
    public String status;

    public Passenger(String start_time, int journey_start_id, int journey_end_id, String end_time, double distance, double fare, String status) {
        this.start_time = start_time;
        this.journey_start_id = journey_start_id;
        this.journey_end_id = journey_end_id;
        this.end_time = end_time;
        this.distance = distance;
        this.fare = fare;
        this.status = status;
    }
}
