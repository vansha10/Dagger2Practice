package com.o.dagger2practice.ui.auth;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.o.dagger2practice.model.User;
import com.o.dagger2practice.network.auth.AuthApi;

import java.util.Observable;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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

        authApi.getUser(10)
                .toObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(User user) {
                        Log.d(TAG, "onNext: " + user.getEmail());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
