package com.silverbullet.atracker.core.data.repository;

import com.silverbullet.atracker.core.database.dao.RunDao;
import com.silverbullet.atracker.core.domain.repository.RunsRepository;

import javax.inject.Inject;

public class RunsRepositoryImpl implements RunsRepository {

    private final RunDao mRunDao;

    @Inject
    public RunsRepositoryImpl(RunDao runDao) {
        mRunDao = runDao;
    }
}
