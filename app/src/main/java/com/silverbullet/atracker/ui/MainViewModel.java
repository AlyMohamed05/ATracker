package com.silverbullet.atracker.ui;

import androidx.lifecycle.ViewModel;

import com.silverbullet.atracker.core.domain.repository.RunsRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainViewModel extends ViewModel {

    private final RunsRepository repository;

    @Inject
    public MainViewModel(RunsRepository repo) {
        this.repository = repo;
    }

}
