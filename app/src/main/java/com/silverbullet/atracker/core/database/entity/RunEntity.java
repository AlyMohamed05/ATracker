package com.silverbullet.atracker.core.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "t_runs")
public class RunEntity {

    public RunEntity(
            int distanceInMeters,
            float avgSpeedInKMH,
            long timeInMillis,
            int caloriesBurned,
            String imagePath,
            long timestamp
    ) {
        this.distanceInMeters = distanceInMeters;
        this.avgSpeedInKMH = avgSpeedInKMH;
        this.timeInMillis = timeInMillis;
        this.caloriesBurned = caloriesBurned;
        this.imagePath = imagePath;
        this.timestamp = timestamp;
    }

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "distance_in_meters")
    public int distanceInMeters;

    @ColumnInfo(name = "avg_speed_in_kmh")
    public float avgSpeedInKMH;

    @ColumnInfo(name = "time_in_millis")
    public long timeInMillis;

    @ColumnInfo(name = "calories_burned")
    public int caloriesBurned;

    @ColumnInfo(name = "img_path")
    public String imagePath;

    @ColumnInfo(name = "timestamp")
    public long timestamp;

    public int getId() {
        return id;
    }

    public int getDistanceInMeters() {
        return distanceInMeters;
    }

    public float getAvgSpeedInKMH() {
        return avgSpeedInKMH;
    }

    public long getTimeInMillis() {
        return timeInMillis;
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    public String getImagePath() {
        return imagePath;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
