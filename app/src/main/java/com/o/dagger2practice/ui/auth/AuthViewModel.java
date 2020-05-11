package com.o.dagger2practice.ui.auth;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.o.dagger2practice.network.auth.AuthApi;

import javax.inject.Inject;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "Dagger2Practice";

    private AuthApi authApi;

    @Inject
    public AuthViewModel(AuthApi authApi) {
        Log.d(TAG, "AuthViewModel: ViewModel is working");

        this.authApi = authApi;
        if (this.authApi == null) {
            Log.d(TAG, "AuthViewModel: authApi is null");
        } else {
            Log.d(TAG, "AuthViewModel: authApi is not null");
        }
    }
}
