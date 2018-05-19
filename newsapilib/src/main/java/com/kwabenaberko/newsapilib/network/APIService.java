package com.kwabenaberko.newsapilib.network;

import com.kwabenaberko.newsapilib.models.response.SourcesResponse;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Kwabena Berko on 5/7/2018.
 */

public interface APIService {
    @GET("/v2/sources")
    Call<SourcesResponse> getSources(@QueryMap Map<String, String> query);

    @GET("/v2/top-headlines")
    Call<ArticleResponse> getTopHeadlines(@QueryMap Map<String, String> query);

    @GET("/v2/everything")
    Call<ArticleResponse> getEverything( @QueryMap Map<String, String> query);
}

