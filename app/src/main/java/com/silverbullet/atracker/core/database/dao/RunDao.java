package com.silverbullet.atracker.core.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.silverbullet.atracker.core.database.entity.RunEntity;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

@Dao
public interface RunDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable upsert(RunEntity run);

    @Delete
    Completable delete(RunEntity run);

    @Query("SELECT * FROM t_runs ORDER BY timestamp DESC")
    Observable<List<RunEntity>> observeRuns();

    @Query("SELECT SUM(time_in_millis) FROM t_runs")
    Observable<Long> observeTotalTimeInMillis();

    @Query("SELECT SUM(calories_burned) FROM t_runs")
    Observable<Integer> observeTotalCaloriesBurned();

    @Query("SELECT SUM(distance_in_meters) FROM t_runs")
    Observable<Integer> observeTotalDistance();

    @Query("SELECT AVG(avg_speed_in_kmh) FROM t_runs")
    Observable<Float> observeTotalAvgSpeed();
}
