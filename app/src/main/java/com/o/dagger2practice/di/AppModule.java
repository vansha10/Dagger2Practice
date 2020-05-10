package com.o.dagger2practice.di;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    static String someString() {
        return "Test 123";
    }

    @Provides
    static boolean isAppNull(Application application) {
        if (application == null)
            return true;
        return false;
    }
}
