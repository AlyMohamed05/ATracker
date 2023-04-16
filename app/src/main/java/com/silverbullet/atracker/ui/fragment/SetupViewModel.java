package com.silverbullet.atracker.ui.fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SetupViewModel extends ViewModel {

    private MutableLiveData<String> mName;
    private MutableLiveData<String> mWeight;
    private MutableLiveData<Boolean> mIsValidForm;

    @Inject
    public SetupViewModel() {
        mName = new MutableLiveData<>("");
        mWeight = new MutableLiveData<>("");
        mIsValidForm = new MutableLiveData<>(false);
    }

    public void setName(String name) {
        mName.setValue(name);
        validateForm();
    }

    public void setWeight(String weight) {
        mWeight.setValue(weight);
        validateForm();
    }

    public LiveData<Boolean> isValidForm() {
        return mIsValidForm;
    }

    private void validateForm() {
        if (mName.getValue().isBlank() || mWeight.getValue().isBlank()) {
            mIsValidForm.setValue(false);
            return;
        }
        try {
            int weight = Integer.parseInt(mWeight.getValue());
            if (weight > 0) {
                mIsValidForm.setValue(true);
            } else {
                mIsValidForm.setValue(false);
            }
        } catch (Exception e) {
            mIsValidForm.setValue(false);
        }
    }
}
