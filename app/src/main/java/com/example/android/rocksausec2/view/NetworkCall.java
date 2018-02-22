package com.example.android.rocksausec2.view;


import com.example.android.rocksausec2.model.Data_;
import com.example.android.rocksausec2.model.Example;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class NetworkCall extends Data_ {

    public static final String BASE_SCHEMA_WND = "http";
    public static final String BASE_URL = "http://www.reddit.com/";
    public static final String PATH = "r/%s/.json";
    public static final String EXT_WND = ".json";

    public static Retrofit restCall() {


        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        // create a custom client to add the interceptor
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory( GsonConverterFactory.create())
                .addCallAdapterFactory( RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }


    public static Call<Example> getExampleCall() {
        Retrofit retrofit = restCall();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        return retrofitService.getRetrofitServiceData();
    }

    public interface RetrofitService {

        @GET(PATH)
        Call<Example> getRetrofitServiceData();
    }




}
