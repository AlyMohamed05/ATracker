package com.silverbullet.atracker.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.silverbullet.atracker.databinding.FragmentTrackingBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class TrackingFragment extends Fragment {

    private FragmentTrackingBinding binding;

    private GoogleMap mMap;

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
        binding = FragmentTrackingBinding.inflate(inflater, container, false);
        binding.map.onCreate(savedInstanceState);
        binding.map.getMapAsync(map -> mMap = map);
        return binding.getRoot();
    }

    private void sendCommandToTrackingService(String action) {
        Intent intent = new Intent(requireContext(), TrackingFragment.class);
        intent.setAction(action);
        requireContext().startService(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        binding.map.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.map.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        binding.map.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        binding.map.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding.map.onDestroy();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        binding.map.onSaveInstanceState(outState);
    }
}