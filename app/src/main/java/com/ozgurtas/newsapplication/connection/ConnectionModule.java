package com.ozgurtas.newsapplication.connection;

import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ozgurtas.newsapplication.model.TopStoryResponse;
import com.ozgurtas.newsapplication.utils.Constant;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ozgur on 9.03.2018.
 */

@Module
public class ConnectionModule {

    private static final int timeoutInterval = 30; //30 seconds
    private static final long cacheSize = 10 * 1024 * 1024; // 10 MB

    @Provides
    @Singleton
    Cache getHttpCache(Application application) {
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient getOkhttpClient(Cache cache) {

        //Print Data to the LogCat
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(logging);
        client.connectTimeout(timeoutInterval, TimeUnit.SECONDS);
        client.readTimeout(timeoutInterval, TimeUnit.SECONDS);
        client.cache(cache); //Optional
        return client.build();
    }

    @Provides
    @Singleton
    Retrofit getRetrofitConnection(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(Constant.BASE_URL)
                .client(okHttpClient)
                .build();
    }


    /*@Named annotations allow that you can make multiple Retrofit calls
    and make unique and recognizable every provide methods in your injection class*/
    @Named("world")
    @Provides
    public Call<TopStoryResponse> getWorldSection(Retrofit retrofit) {
        return retrofit
                .create(NewsFactory.class)
                .getWorldSection(Constant.API_KEY);
    }

    @Named("tech")
    @Provides
    public Call<TopStoryResponse> getTechnologySection(Retrofit retrofit) {
        return retrofit
                .create(NewsFactory.class)
                .getTechnologySection(Constant.API_KEY);
    }

    @Named("sport")
    @Provides
    public Call<TopStoryResponse> getSportSection(Retrofit retrofit) {
        return retrofit
                .create(NewsFactory.class)
                .getSportSection(Constant.API_KEY);
    }
}
