package com.example.android.rocksausec2.view;


import com.example.android.rocksausec2.model.Child;
import com.example.android.rocksausec2.utils.BasePresenter;
import com.example.android.rocksausec2.utils.BaseView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface PostListActivityContract {

    interface View extends BaseView {

        void sendInfo(ArrayList<Child> value);

        void getAllPostList(List<Child> getAllPostList) throws IOException;
    }

    interface Presenter extends BasePresenter<PostListActivityContract.View> {

        void restCall();
    }

}
