package com.o.dagger2practice.di.main;

import androidx.lifecycle.ViewModel;

import com.o.dagger2practice.di.ViewModelKey;
import com.o.dagger2practice.ui.main.posts.PostsViewModel;
import com.o.dagger2practice.ui.main.profile.ProfileViewModel;

import dagger.Binds;
import dagger.MapKey;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    abstract ViewModel bindProfileViewModel(ProfileViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel.class)
    abstract ViewModel bindPostsViewModel(PostsViewModel viewModel);
}
