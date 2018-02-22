package com.example.android.rocksausec2.view;

import android.content.Context;

import com.example.android.rocksausec2.model.Child;
import com.example.android.rocksausec2.model.Example;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class PostListActivityPresenter implements PostListActivityContract.Presenter {

    private PostListActivityContract.View view;
    private Context context;

    @Override
    public void attachView(PostListActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void restCall() {

        final retrofit2.Call<Example> getAllPost = NetworkCall.getExampleCall();
        getAllPost.enqueue(new retrofit2.Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                List<Child> user = response.body().getData().getChildren();
                try{
                    view.getAllPostList(user);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }
}
