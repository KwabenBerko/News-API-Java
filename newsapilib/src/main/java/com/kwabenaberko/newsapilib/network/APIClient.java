package com.kwabenaberko.newsapilib.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kwabena Berko on 5/7/2018.
 */

public class APIClient {
    private static Retrofit mRetrofit = null;

    private static Retrofit getRetrofit(){
        if(mRetrofit == null){
            mRetrofit = new Retrofit.Builder()
                    .baseUrl("https://newsapi.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return mRetrofit;
    }

    public static APIService getAPIService(){
        return getRetrofit().create(APIService.class);
    }

}

