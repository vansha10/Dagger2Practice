package com.o.dagger2practice.di;

import com.o.dagger2practice.di.auth.AuthViewModelsModule;
import com.o.dagger2practice.ui.auth.AuthActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = {AuthViewModelsModule.class})
    abstract AuthActivity contributeAuthActivity();
}
