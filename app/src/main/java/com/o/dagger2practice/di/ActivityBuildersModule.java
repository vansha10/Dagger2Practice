package com.o.dagger2practice.di;

import com.o.dagger2practice.di.auth.AuthModule;
import com.o.dagger2practice.di.auth.AuthScope;
import com.o.dagger2practice.di.auth.AuthViewModelsModule;
import com.o.dagger2practice.di.main.MainFragmentBuildersModule;
import com.o.dagger2practice.di.main.MainModule;
import com.o.dagger2practice.di.main.MainScope;
import com.o.dagger2practice.di.main.MainViewModelsModule;
import com.o.dagger2practice.ui.auth.AuthActivity;
import com.o.dagger2practice.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @AuthScope
    @ContributesAndroidInjector(modules = {AuthViewModelsModule.class, AuthModule.class})
    abstract AuthActivity contributeAuthActivity();

    @MainScope
    @ContributesAndroidInjector(modules = {
            MainFragmentBuildersModule.class,
            MainViewModelsModule.class,
            MainModule.class
    })
    abstract MainActivity contributeMainActivity();
}
