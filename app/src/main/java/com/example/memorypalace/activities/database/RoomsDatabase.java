package com.example.memorypalace.activities.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.memorypalace.activities.dao.RoomDao;
import com.example.memorypalace.activities.entities.Rooms;

//database
@Database(entities = Rooms.class, version = 1, exportSchema = false)
public abstract class RoomsDatabase extends RoomDatabase {

    private static RoomsDatabase roomsDatabase;

    public static synchronized RoomsDatabase getDatabase(Context context){
        if(roomsDatabase == null){
            roomsDatabase = Room.databaseBuilder(context,
                    RoomsDatabase.class, "rooms_db").build();
        }

        return roomsDatabase;
    }

    public abstract RoomDao roomDao();
}