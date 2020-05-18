package com.o.dagger2practice.ui.main.posts;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.o.dagger2practice.SessionManager;
import com.o.dagger2practice.network.main.MainApi;

import javax.inject.Inject;

public class PostsViewModel extends ViewModel {

    private static final String TAG = "PostsViewModel";

    //inject
    SessionManager sessionManager;
    MainApi mainApi;

    @Inject
    public PostsViewModel(SessionManager sessionManager, MainApi mainApi) {
        this.sessionManager = sessionManager;
        this.mainApi = mainApi;
        Log.d(TAG, "PostsViewModel: viewmodel is working...");
    }
}
