package com.o.dagger2practice.di;

import android.app.Application;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.o.dagger2practice.R;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    static RequestOptions provideRequestOption() {
        return RequestOptions.placeholderOf(R.drawable.ic_status_check)
                .error(R.drawable.ic_launcher_foreground);
    }

    @Provides
    static RequestManager provideGlideInstance(Application application, RequestOptions requestOptions) {
        return Glide.with(application)
                .setDefaultRequestOptions(requestOptions);
    }

    @Provides
    static Drawable provideAppDrawable(Application application) {
        return ContextCompat.getDrawable(application, R.drawable.ic_profile);
    }
}
