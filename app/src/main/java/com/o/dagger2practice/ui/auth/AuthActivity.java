package com.o.dagger2practice.ui.auth;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.RequestManager;
import com.o.dagger2practice.R;
import com.o.dagger2practice.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity {

    private static final String TAG = "Dagger2Practice";

    private AuthViewModel authViewModel;

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    @Inject
    Drawable logo;

    @Inject
    RequestManager requestManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        authViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(AuthViewModel.class);

        setLogo();
    }

    private void setLogo() {
        requestManager.load(logo)
                .into((ImageView) findViewById(R.id.login_logo));
    }
}
