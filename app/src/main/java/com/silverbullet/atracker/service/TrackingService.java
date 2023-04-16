package com.silverbullet.atracker.service;

import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleService;

import timber.log.Timber;

public class TrackingService extends LifecycleService {

    public static final String ACTION_START_OR_RESUME_SERVICE = "ACTION_START_OR_RESUME_SERVICE";
    public static final String ACTION_PAUSE_SERVICE = "ACTION_PAUSE_SERVICE";
    public static final String ACTION_STOP_SERVICE = "ACTION_STOP_SERVICE";

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        if (intent != null) {
            switch (intent.getAction()) {
                case ACTION_START_OR_RESUME_SERVICE:
                    Timber.d(ACTION_START_OR_RESUME_SERVICE);
                    break;
                case ACTION_PAUSE_SERVICE:
                    Timber.d(ACTION_PAUSE_SERVICE);
                    break;
                case ACTION_STOP_SERVICE:
                    Timber.d(ACTION_STOP_SERVICE);
                    break;
                default:
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

}
