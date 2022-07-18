package com.codniv.e_transport.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PassengerDao {

    @Query("SELECT * FROM passenger")
    List<Passenger> getAllPassenger();

    @Insert
    void insertPassenger(Passenger... passenger);

    @Delete
    void delete(Passenger passenger);

    @Query("DELETE FROM passenger WHERE id = :id")
    void deletePassenger(int id);
}
