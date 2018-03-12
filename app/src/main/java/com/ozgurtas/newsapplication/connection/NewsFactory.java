package com.ozgurtas.newsapplication.connection;

import com.ozgurtas.newsapplication.model.TopStoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by Ozgur on 11.03.2018.
 */

public interface NewsFactory {

    @GET("world.json")
    Call<TopStoryResponse> getWorldSection(@Header("api-key") String apiKey);

    @GET("technology.json")
    Call<TopStoryResponse> getTechnologySection(@Header("api-key") String apiKey);

    @GET("sports.json")
    Call<TopStoryResponse> getSportSection(@Header("api-key") String apiKey);

}
