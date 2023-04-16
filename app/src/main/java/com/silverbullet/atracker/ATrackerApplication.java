package com.silverbullet.atracker;

import android.app.Application;

import dagger.hilt.android.HiltAndroidApp;
import pub.devrel.easypermissions.BuildConfig;
import timber.log.Timber;

@HiltAndroidApp
public class ATrackerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
