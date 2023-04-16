package com.silverbullet.atracker.ui.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.silverbullet.atracker.databinding.FragmentSetupBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SetupFragment extends Fragment {

    private FragmentSetupBinding binding;
    private ObjectAnimator fabAppearAnimation;
    private ObjectAnimator fabDisappearAnimation;
    private SetupViewModel viewModel;
    private Observer<Boolean> mIsValidFormObserver;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(SetupViewModel.class);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSetupBinding.inflate(inflater, container, false);
        setupListeners();
        initializeObjectAnimators();
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewModel.isValidForm().removeObserver(mIsValidFormObserver);
    }


    private void navigateToRunsFragment() {
        Navigation
                .findNavController(getView())
                .navigate(SetupFragmentDirections.actionSetupFragmentToRunsFragment());
    }

    private void initializeObjectAnimators() {
        fabAppearAnimation = ObjectAnimator.ofPropertyValuesHolder(
                binding.fabContinue,
                PropertyValuesHolder.ofFloat(View.SCALE_X, 0f, 1f),
                PropertyValuesHolder.ofFloat(View.SCALE_Y, 0f, 1f),
                PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, binding.fabContinue.getHeight() , 0f)
        );
        fabDisappearAnimation = ObjectAnimator.ofPropertyValuesHolder(
                binding.fabContinue,
                PropertyValuesHolder.ofFloat(View.SCALE_X, 1f, 0f),
                PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f, 0f),
                PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, 0f, binding.fabContinue.getHeight())
        );
        fabAppearAnimation.setDuration(175);
        fabDisappearAnimation.setDuration(175);
        fabAppearAnimation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                binding.fabContinue.setVisibility(View.VISIBLE);
            }
        });
        fabDisappearAnimation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                binding.fabContinue.setVisibility(View.GONE);
            }
        });
    }

    private void setupListeners() {
        binding.fabContinue.setOnClickListener(v -> navigateToRunsFragment());
        binding.editName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.editWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.setWeight(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mIsValidFormObserver = isValid -> {
            if (isValid) {
                fabAppearAnimation.start();
            } else {
                fabDisappearAnimation.start();
            }
        };
        viewModel.isValidForm().observe(getViewLifecycleOwner(), mIsValidFormObserver);
    }

}