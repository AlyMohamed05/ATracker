package com.silverbullet.atracker.ui.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.silverbullet.atracker.databinding.FragmentRunsBinding;

import java.util.Map;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RunsFragment extends Fragment {

    private FragmentRunsBinding binding;

    private final ActivityResultLauncher<String[]> locationPermissionRequest = registerForActivityResult(
            new ActivityResultContracts.RequestMultiplePermissions(),
            this::handlePermissionResult
    );

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
        setupClickListeners();
        ensureHavingLocationPermissions();
        return binding.getRoot();
    }

    private void setupClickListeners() {
        binding.fabNewRun.setOnClickListener(view -> {
            Navigation.findNavController(getView()).navigate(RunsFragmentDirections.actionRunsFragmentToTrackingFragment());
        });
    }

    private void ensureHavingLocationPermissions() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED
        ) {
            return;
        }
        locationPermissionRequest.launch(new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        });
    }

    private void handlePermissionResult(Map<String, Boolean> result) {
        Boolean fineLocationGranted = result.get(Manifest.permission.ACCESS_FINE_LOCATION);
        if (fineLocationGranted != null && !fineLocationGranted) {
            if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                new AlertDialog
                        .Builder(getContext())
                        .setTitle("Precise Location Needed")
                        .setMessage("This app needs precise location in order to provide it's service properly")
                        .setPositiveButton(
                                "Ok",
                                (dialog, which) -> locationPermissionRequest.launch(new String[]{
                                        Manifest.permission.ACCESS_FINE_LOCATION,
                                        Manifest.permission.ACCESS_COARSE_LOCATION
                                })
                        )
                        .setNegativeButton("No, thanks", (dialog, which) -> {
                        })
                        .create()
                        .show();
            } else {
                // Permission is permanently rejected
                new AlertDialog
                        .Builder(getContext())
                        .setTitle("Location permission permanently denied")
                        .setMessage("Please enable location precise location from settings")
                        .setPositiveButton(
                                "Ok",
                                (dialog, which) -> {
                                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
                                    intent.setData(uri);
                                    startActivity(intent);
                                }
                        )
                        .setNegativeButton("No, thanks", (dialog, which) -> {
                        })
                        .create()
                        .show();
            }
        }
        // Permission is granted
    }
}