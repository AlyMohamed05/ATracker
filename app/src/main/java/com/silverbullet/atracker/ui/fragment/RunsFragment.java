package com.silverbullet.atracker.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.silverbullet.atracker.databinding.FragmentRunsBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RunsFragment extends Fragment {

    private FragmentRunsBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentRunsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}