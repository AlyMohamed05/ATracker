package com.silverbullet.atracker.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.silverbullet.atracker.R;
import com.silverbullet.atracker.databinding.ActivityMainBinding;
import com.silverbullet.atracker.ui.fragment.TrackingFragment;
import com.silverbullet.atracker.utils.NotificationUtils;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen.installSplashScreen(this);

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // TODO: you might want to set SupportActionBar here

        setupNavigation();
        NotificationUtils.createNotificationChannel(this);
        checkForTrackingFragmentAction(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        checkForTrackingFragmentAction(intent);
    }

    private void setupNavigation() {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment == null) {
            return;
        }
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(binding.bottomNavBar, navController);

        navController.addOnDestinationChangedListener((pram1, dest, pram2) -> {
            final int destId = dest.getId();
            if (destId == R.id.setupFragment) {
                binding.bottomNavBar.setVisibility(View.GONE);
            } else if (destId == R.id.trackingFragment) {
                binding.bottomNavBar.setVisibility(View.GONE);
            } else {
                binding.bottomNavBar.setVisibility(View.VISIBLE);
            }
        });
    }

    /**
     * This will check for the action Tracking fragment and if finds it then it will navigate to the Tracking fragment
     */
    private void checkForTrackingFragmentAction(Intent intent) {
        if (intent == null) {
            return;
        }
        if (Objects.equals(intent.getAction(), TrackingFragment.ACTION_OPEN_TRACKING_FRAGMENT)) {
            NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
            if (navHostFragment == null) {
                return;
            }
            NavController navController = navHostFragment.getNavController();
            navController.navigate(R.id.action_global_trackingFragment);
        }
    }
}