package com.example.android.rocksausec2.data.remote;

import android.content.Context;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RemoteDataSource {

    public static final String APP_ID = "f6e40eb3";
    public static final String APP_KEY = "0fde0a5350c63784cc20690ebcda8185";

    Context context;

    private String BASE_URL;

    @Inject
    public RemoteDataSource(String BASE_URL, Context context) {
        this.BASE_URL = BASE_URL;
        this.context = context;
    }

    public  Retrofit create(){


        int cacheSize = 10 * 1024 * 1024;

        Cache cache = new Cache(context.getCacheDir(), cacheSize);


        //Create a logging interceptor
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

        //create a client for the adding the interceptor
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .cache(cache)
                .build();




        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)//add the client to use logging interceptor
                .addConverterFactory( GsonConverterFactory.create())
                .addCallAdapterFactory( RxJava2CallAdapterFactory.create())
                .build();

        return retrofit;

    }

    public Observable<Response> getResponse(String query, String from, String to){
        Retrofit retrofit = create();
        RemoteService remoteService = retrofit.create(RemoteService.class);
        return remoteService.getRecipes(query, from, to, APP_ID, APP_KEY);

    }
}
