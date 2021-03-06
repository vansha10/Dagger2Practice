package com.o.dagger2practice.di.main;

import com.o.dagger2practice.ui.main.posts.PostsFragment;
import com.o.dagger2practice.ui.main.profile.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract ProfileFragment contributesProfileFragment();

    @ContributesAndroidInjector
    abstract PostsFragment contributesPostsFragment();
}
