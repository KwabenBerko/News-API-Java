package com.kwabenaberko.newsapilib;

import com.kwabenaberko.newsapilib.models.request.EverythingRequest;
import com.kwabenaberko.newsapilib.models.request.SourcesRequest;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;
import com.kwabenaberko.newsapilib.models.response.SourcesResponse;
import com.kwabenaberko.newsapilib.network.APIClient;
import com.kwabenaberko.newsapilib.network.APIService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class NewsApiClient {
    private String mApiKey;
    private Map<String, String> query;
    private APIService mAPIService;

    public NewsApiClient(String apiKey){
        mApiKey = apiKey;
        mAPIService = APIClient.getAPIService();
        query = new HashMap<>();
        query.put("apiKey", apiKey);
    }

    //Callbacks
    public interface SourcesCallback{
        void onSuccess(SourcesResponse response);
        void onFailure(Throwable throwable);
    }

    public interface ArticlesResponseCallback{
        void onSuccess(ArticleResponse response);
        void onFailure(Throwable throwable);
    }


    private Throwable errMsg(String str) {
        Throwable throwable = null;
        try {
            JSONObject obj = new JSONObject(str);
            throwable = new Throwable(obj.getString("message"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (throwable == null){
            throwable = new Throwable("An error occured");
        }


        return throwable;
    }

    private Map<String, String> createQuery(){
        query = new HashMap<>();
        query.put("apiKey", mApiKey);
        return query;
    }


    //Get Sources
    public void getSources(SourcesRequest sourcesRequest, final SourcesCallback callback){
        query = createQuery();
        query.put("category", sourcesRequest.getCategory());
        query.put("language", sourcesRequest.getLanguage());
        query.put("country", sourcesRequest.getCountry());

        query.values().removeAll(Collections.singleton(null));


        mAPIService.getSources(query)
                .enqueue(new Callback<SourcesResponse>() {
                    @Override
                    public void onResponse(Call<SourcesResponse> call, retrofit2.Response<SourcesResponse> response) {
                        if (response.code() == HttpURLConnection.HTTP_OK){
                            callback.onSuccess(response.body());
                        }

                        else{
                            try {
                                callback.onFailure(errMsg(response.errorBody().string()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<SourcesResponse> call, Throwable throwable) {
                        callback.onFailure(throwable);
                    }
                });
    }


    public void getTopHeadlines(TopHeadlinesRequest topHeadlinesRequest, final ArticlesResponseCallback callback){


        query = createQuery();
        query.put("country", topHeadlinesRequest.getCountry());
        query.put("language", topHeadlinesRequest.getLanguage());
        query.put("category", topHeadlinesRequest.getCategory());
        query.put("sources", topHeadlinesRequest.getSources());
        query.put("q", topHeadlinesRequest.getQ());
        query.put("pageSize", topHeadlinesRequest.getPageSize());
        query.put("page", topHeadlinesRequest.getPage());

        query.values().removeAll(Collections.singleton(null));
        query.values().removeAll(Collections.singleton("null"));


        mAPIService.getTopHeadlines(query)
                .enqueue(new Callback<ArticleResponse>() {
                    @Override
                    public void onResponse(Call<ArticleResponse> call, retrofit2.Response<ArticleResponse> response) {
                        if (response.code() == HttpURLConnection.HTTP_OK){
                            callback.onSuccess(response.body());
                        }

                        else{
                            try {
                                callback.onFailure(errMsg(response.errorBody().string()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ArticleResponse> call, Throwable throwable) {
                        callback.onFailure(throwable);
                    }
                });
    }


    public void getEverything(EverythingRequest everythingRequest, final ArticlesResponseCallback callback){
        query = createQuery();
        query.put("q", everythingRequest.getQ());
        query.put("sources", everythingRequest.getSources());
        query.put("domains", everythingRequest.getDomains());
        query.put("from", everythingRequest.getFrom());
        query.put("to", everythingRequest.getTo());
        query.put("language", everythingRequest.getLanguage());
        query.put("sortBy", everythingRequest.getSortBy());
        query.put("pageSize", everythingRequest.getPageSize());
        query.put("page", everythingRequest.getPage());

        query.values().removeAll(Collections.singleton(null));
        query.values().removeAll(Collections.singleton("null"));

        mAPIService.getEverything(query)
                .enqueue(new Callback<ArticleResponse>() {
                    @Override
                    public void onResponse(Call<ArticleResponse> call, retrofit2.Response<ArticleResponse> response) {
                        if (response.code() == HttpURLConnection.HTTP_OK){
                            callback.onSuccess(response.body());
                        }

                        else{
                            try {
                                callback.onFailure(errMsg(response.errorBody().string()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ArticleResponse> call, Throwable throwable) {
                        callback.onFailure(throwable);
                    }
                });
    }
}