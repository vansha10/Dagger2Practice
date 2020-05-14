package com.o.dagger2practice.ui.auth;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.RequestManager;
import com.o.dagger2practice.R;
import com.o.dagger2practice.model.User;
import com.o.dagger2practice.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity {

    private static final String TAG = "Dagger2Practice";

    private AuthViewModel authViewModel;
    
    private EditText editText;
    private Button loginButton;
    private ProgressBar progressBar;

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
        
        editText = findViewById(R.id.user_id_input);
        loginButton = findViewById(R.id.login_button);
        progressBar = findViewById(R.id.progress_bar);
        
        loginButton.setOnClickListener(view -> attemptLogin());

        authViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(AuthViewModel.class);

        setLogo();

        subscribeObservers();
    }

    private void subscribeObservers() {
        authViewModel.observeUser().observe(this, userAuthResource -> {
            if (userAuthResource != null) {
                switch (userAuthResource.status) {
                    case LOADING:{
                        showProgressBar(true);
                        break;
                    }
                    case ERROR:{
                        showProgressBar(false);
                        Toast.makeText(this, userAuthResource.message, Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case AUTHENTICATED:{
                        showProgressBar(false);
                        Log.d(TAG, "subscribeObservers: login success: user = " + userAuthResource.data.getEmail());
                        break;
                    }
                    case NOT_AUTHENTICATED: {
                        showProgressBar(false);
                        break;
                    }
                }
            }
        });
    }

    private void showProgressBar(boolean isVisible) {
        if (isVisible)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);
    }

    private void attemptLogin() {
        String id = editText.getText().toString();
        if (!id.isEmpty()) {
            authViewModel.authenticateWithId(Integer.parseInt(id));
        }
    }

    private void setLogo() {
        requestManager.load(logo)
                .into((ImageView) findViewById(R.id.login_logo));
    }
}
