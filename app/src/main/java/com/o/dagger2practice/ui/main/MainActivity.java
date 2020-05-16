package com.o.dagger2practice.ui.main;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.o.dagger2practice.BaseActivity;
import com.o.dagger2practice.R;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainAcitivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
