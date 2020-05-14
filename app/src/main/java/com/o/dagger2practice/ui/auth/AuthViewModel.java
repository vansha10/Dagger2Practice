package com.o.dagger2practice.ui.auth;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.o.dagger2practice.model.User;
import com.o.dagger2practice.network.auth.AuthApi;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "Dagger2Practice";

    private final AuthApi authApi;

    private MediatorLiveData<AuthResource<User>> authUser = new MediatorLiveData<>();

    @Inject
    public AuthViewModel(AuthApi authApi) {
        Log.d(TAG, "AuthViewModel: ViewModel is working");
        this.authApi = authApi;
    }

    public void authenticateWithId(int userId) {

        authUser.setValue(AuthResource.loading((User) null));

        final LiveData<AuthResource<User>> source = LiveDataReactiveStreams.fromPublisher(
                authApi.getUser(userId)
                        .onErrorReturn(throwable -> {
                            User errorUser = new User();
                            errorUser.setId(-1);
                            return errorUser;
                        })
                        .map(user -> {
                            if (user.getId() == -1) {
                                return AuthResource.error("Could not authenticate", (User) null);
                            }
                            return AuthResource.authenticated(user);
                        })
                        .subscribeOn(Schedulers.io()));

        authUser.addSource(source, userAuthResource -> {
            authUser.setValue(userAuthResource);
            authUser.removeSource(source);
        });
    }

    public LiveData<AuthResource<User>> observeUser() {
        return authUser;
    }
}
