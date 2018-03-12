package com.ozgurtas.newsapplication.connection;

import com.ozgurtas.newsapplication.AppModule;
import com.ozgurtas.newsapplication.NewsApplication;
import com.ozgurtas.newsapplication.activity.StreamActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Ozgur on 11.03.2018.
 */
@Singleton
@Component(modules = {AppModule.class, ConnectionModule.class})
public interface ConnectionComponent {
    void inject(StreamActivity streamActivity); //Inject activity
    void inject(NewsApplication newsApplication); //Inject application
}
