package com.silverbullet.atracker.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.silverbullet.atracker.databinding.FragmentTrackingBinding;
import com.silverbullet.atracker.service.TrackingService;
import com.silverbullet.atracker.ui.MainActivity;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class TrackingFragment extends Fragment {

    public static final String ACTION_OPEN_TRACKING_FRAGMENT = "action_open_tracking_fragment";

    private FragmentTrackingBinding binding;

    private GoogleMap mMap;

    @NonNull
    public static Intent getTrackingFragmentIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setAction(ACTION_OPEN_TRACKING_FRAGMENT);
        return intent;
    }

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
        setupClickListeners();
        return binding.getRoot();
    }

    private void sendCommandToTrackingService(String action) {
        Intent intent = new Intent(requireContext(), TrackingService.class);
        intent.setAction(action);
        requireContext().startService(intent);
    }

    private void setupClickListeners() {
        binding.fabStartTracking.setOnClickListener(v -> {
            sendCommandToTrackingService(TrackingService.ACTION_START_OR_RESUME_SERVICE);
        });
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