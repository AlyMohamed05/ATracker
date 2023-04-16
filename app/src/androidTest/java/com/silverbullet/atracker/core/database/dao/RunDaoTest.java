package com.silverbullet.atracker.core.database.dao;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.silverbullet.atracker.core.database.ATrackerDatabase;
import com.silverbullet.atracker.core.database.entity.RunEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import io.reactivex.rxjava3.observers.TestObserver;

@RunWith(AndroidJUnit4.class)
public class RunDaoTest {

    private ATrackerDatabase mDatabase;

    @Before
    public void setup() {
        mDatabase = Room
                .inMemoryDatabaseBuilder(
                        ApplicationProvider.getApplicationContext(),
                        ATrackerDatabase.class
                )
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void clear() {
        mDatabase.close();
    }

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Test
    public void insertRunAndGetIt() {

        // Given that database has one run
        RunEntity run = new RunEntity(0, 0, 0, 0, "", 0);

        mDatabase
                .runDao()
                .upsert(run)
                .blockingAwait();

        TestObserver<List<RunEntity>> testObserver = mDatabase.runDao().observeRuns().test();
        testObserver.awaitCount(1);
        testObserver.assertValue(runs -> runs.size() == 1);
    }
}
