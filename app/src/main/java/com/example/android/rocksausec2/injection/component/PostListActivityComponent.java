package com.example.android.rocksausec2.injection.component;


import com.example.android.rocksausec2.view.PostListActivity;
import com.example.android.rocksausec2.injection.module.PostListActivityModule;

import dagger.Component;

@Component(modules = PostListActivityModule.class)
public interface PostListActivityComponent {

    void inject(PostListActivity postListActivity);
}
