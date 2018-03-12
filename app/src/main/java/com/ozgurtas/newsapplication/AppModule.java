package com.ozgurtas.newsapplication;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ozgur on 9.03.2018.
 */

@Module
public class AppModule {

    private Application appModule;

    AppModule(Application appModule) {
        this.appModule = appModule;
    }

    @Provides
    @Singleton
    public Application getAppModule() {
        return appModule;
    }
}
