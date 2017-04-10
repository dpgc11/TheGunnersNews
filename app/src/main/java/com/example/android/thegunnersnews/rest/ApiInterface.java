package com.example.android.thegunnersnews.rest;

import com.example.android.thegunnersnews.model.News1;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Yogesh on 10-04-2017.
 */

public interface ApiInterface {

    // https://content.guardianapis.com/search?q=football/arsenal&section=football&tag=football/arsenal
    // &order-by=newest&show-fields=thumbnail&api-key=b29db22e-a97f-4c16-abc6-ca85fec2cde0


    @GET("search")
    Call<News1> getNewsAboutArsenal(@Query("q") String query, @Query("section") String section, @Query("tag") String tag,
                                    @Query("order-by") String order, @Query("show-fields") String showFields,
                                    @Query("api-key") String apiKey);
}
