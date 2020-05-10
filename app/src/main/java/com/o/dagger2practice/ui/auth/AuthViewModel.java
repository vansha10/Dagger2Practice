package com.o.dagger2practice.ui.auth;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "Dagger2Practice";

    @Inject
    public AuthViewModel() {
        Log.d(TAG, "AuthViewModel: ViewModel is working");
    }
}
