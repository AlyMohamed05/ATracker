package com.silverbullet.atracker.core.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.silverbullet.atracker.core.database.dao.RunDao;
import com.silverbullet.atracker.core.database.entity.RunEntity;


@Database(
        entities = {RunEntity.class},
        version = 1,
        exportSchema = false
)
public abstract class ATrackerDatabase extends RoomDatabase {

    public static String DATABASE_NAME = "a_tracker_database.db";

    public abstract RunDao runDao();
}
