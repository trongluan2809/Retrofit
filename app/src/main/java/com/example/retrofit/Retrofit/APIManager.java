package com.example.retrofit.Retrofit;

import com.example.retrofit.Video;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;

public interface APIManager {
    public static String BaseUrl = "http://demo1913415.mockable.io/";
    @GET("GetCategory")
    Call<List<Video>> getDataVideo();
}
//    @Field("title") String title,
//    @Field("thumb") String thumb