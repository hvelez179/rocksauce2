package com.example.android.rocksausec2.data.remote;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Android on 2/21/2018.
 */

public interface RemoteService {

    @GET("search")
    Observable<Response> getRecipes(
            @Query("q") String query
            , @Query("from") String from
            , @Query("to") String to
            , @Query("app_id") String app_id
            , @Query("app_key") String app_key);

}
