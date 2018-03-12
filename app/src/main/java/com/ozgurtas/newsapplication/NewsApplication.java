package com.ozgurtas.newsapplication;

import android.app.Application;

import com.ozgurtas.newsapplication.connection.ConnectionComponent;
import com.ozgurtas.newsapplication.connection.ConnectionModule;
import com.ozgurtas.newsapplication.connection.DaggerConnectionComponent;

/**
 * Created by Ozgur on 11.03.2018.
 */

public class NewsApplication extends Application {

    private ConnectionComponent component;
    private static NewsApplication app;

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;

        component = DaggerConnectionComponent.builder()
                .appModule(new AppModule(this))
                .connectionModule(new ConnectionModule())
                .build();

        component.inject(this);
    }

    public ConnectionComponent getConnectionComponent() {
        return component;
    }

    public static NewsApplication getApp() {
        return app;
    }
}
