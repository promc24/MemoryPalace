package com.example.memorypalace.activities.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.memorypalace.activities.entities.Rooms;

import java.util.List;

@Dao
public interface RoomDao {

    @Query("SELECT * FROM Rooms ORDER BY id DESC ")
    List<Rooms> getAllRooms();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRoom(Rooms rooms);

    @Delete
    void deleteRoom(Rooms rooms);
}
