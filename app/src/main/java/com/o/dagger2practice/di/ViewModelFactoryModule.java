package com.o.dagger2practice.di;

import androidx.lifecycle.ViewModelProvider;

import com.o.dagger2practice.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindFactory(ViewModelProviderFactory viewModelProviderFactory);
}
