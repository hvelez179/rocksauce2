package com.example.android.rocksausec2.utils;

public interface BasePresenter <V extends BaseView>{

    void attachView(V view);
    void detachView();
}
