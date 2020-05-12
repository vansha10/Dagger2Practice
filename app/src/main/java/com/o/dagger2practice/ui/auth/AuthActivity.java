package com.o.dagger2practice.ui.auth;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

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
        
        loginButton.setOnClickListener(view -> attemptLogin());

        authViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(AuthViewModel.class);

        setLogo();

        subscribeObservers();
    }

    private void subscribeObservers() {
        authViewModel.observeUser().observe(this, user -> {
            if (user != null) {
                Log.d(TAG, "subscribeObservers: " + user.getEmail() + " is authenticated");
            }
        });
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
