package com.example.android.thegunnersnews.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Yogesh on 10-04-2017.
 */

public class ApiClient {

    public static final String BASE_URL = "https://content.guardianapis.com/";
    public static final String COMPLETE_URL = "https://content.guardianapis.com/search?q=football/arsenal&section=football&tag=football/arsenal&order-by=newest&show-fields=thumbnail&api-key=b29db22e-a97f-4c16-abc6-ca85fec2cde0";
    private static Retrofit retrofit = null;
    public final String QUERY_URL = "search?q=football/arsenal&section=football&tag=football/arsenal&order-by=newest&show-fields=thumbnail&api-key=b29db22e-a97f-4c16-abc6-ca85fec2cde0";

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }


}
