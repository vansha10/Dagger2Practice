package com.o.dagger2practice;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.o.dagger2practice.ui.auth.AuthActivity;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    private static final String TAG = "BaseActivity";

    @Inject
    SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscribeObservers();
    }

    private void subscribeObservers() {
        sessionManager.getAuthUser().observe(this, userAuthResource -> {
            if (userAuthResource != null) {
                switch (userAuthResource.status) {
                    case LOADING:{
                        break;
                    }
                    case ERROR:{
                        Toast.makeText(this, userAuthResource.message, Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case AUTHENTICATED:{
                        Log.d(TAG, "subscribeObservers: login success: user = " + userAuthResource.data.getEmail());
                        break;
                    }
                    case NOT_AUTHENTICATED: {
                        navLogInScreen();
                        break;
                    }
                }
            }
        });
    }

    private void navLogInScreen() {
        Intent intent = new Intent(BaseActivity.this, AuthActivity.class);
        startActivity(intent);
        finish();
    }
}
