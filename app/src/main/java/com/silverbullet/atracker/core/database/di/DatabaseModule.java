package com.silverbullet.atracker.core.database.di;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.room.Room;

import com.silverbullet.atracker.core.database.ATrackerDatabase;
import com.silverbullet.atracker.core.database.dao.RunDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {

    @NonNull
    @Provides
    @Singleton
    public static ATrackerDatabase provideATrackerDatabase(Application app) {
        return Room
                .databaseBuilder(
                        app,
                        ATrackerDatabase.class,
                        ATrackerDatabase.DATABASE_NAME
                )
                .build();
    }

    @Provides
    @Singleton
    public static RunDao provideRunDao(@NonNull ATrackerDatabase db){
        return db.runDao();
    }
}
