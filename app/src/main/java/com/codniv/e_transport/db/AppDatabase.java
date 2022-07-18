package com.codniv.e_transport.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities ={Journey.class,Passenger.class,User.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract JourneyDao journeyDao();
    public abstract PassengerDao passengerDao();
    public abstract UserDao userDao();
    private static AppDatabase INSTANCE;
    public static AppDatabase getDbInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "E_TRANSPORT").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

}
