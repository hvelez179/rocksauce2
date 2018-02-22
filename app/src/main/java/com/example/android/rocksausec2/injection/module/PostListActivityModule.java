package com.example.android.rocksausec2.injection.module;


import com.example.android.rocksausec2.view.PostListActivityPresenter;

import dagger.Provides;

public class PostListActivityModule {

    @Provides
    PostListActivityPresenter providerPostListActivity() {
        return new PostListActivityPresenter();
    }
}
