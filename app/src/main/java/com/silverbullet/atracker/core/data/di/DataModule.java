package com.silverbullet.atracker.core.data.di;

import com.silverbullet.atracker.core.data.repository.RunsRepositoryImpl;
import com.silverbullet.atracker.core.domain.repository.RunsRepository;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class DataModule {

    @Binds
    public abstract RunsRepository bindRunsRepository(RunsRepositoryImpl repo);
}
