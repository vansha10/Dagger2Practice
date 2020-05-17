package com.o.dagger2practice.ui.main.profile;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.o.dagger2practice.SessionManager;
import com.o.dagger2practice.model.User;
import com.o.dagger2practice.ui.auth.AuthResource;

import javax.inject.Inject;

public class ProfileViewModel extends ViewModel {
    
    private static final String TAG = "ProfileViewModel";

    private SessionManager sessionManager;

    @Inject
    public ProfileViewModel(SessionManager sessionManager) {
        Log.d(TAG, "ProfileViewModel: viewmodel is working...");
        this.sessionManager = sessionManager;
    }

    public LiveData<AuthResource<User>> getAuthenticatedUser() {
        return sessionManager.getAuthUser();
    }
}
