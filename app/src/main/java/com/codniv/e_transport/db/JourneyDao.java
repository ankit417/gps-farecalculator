package com.codniv.e_transport.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface JourneyDao {

    @Query("SELECT * FROM journey")
    List<Journey> getAllJourney();

    @Insert
    void insertJourney(Journey... Journey);

//    @Delete
//    void deleteJourney(Journey Journey);

    @Query("DELETE FROM journey")
    void deleteJourney();

    @Query("DELETE FROM journey WHERE id= :id")
    void deleteAllJourney(int id);
}
