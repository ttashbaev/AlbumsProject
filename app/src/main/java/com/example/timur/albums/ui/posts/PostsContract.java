package com.example.timur.albums.ui.posts;

import com.example.timur.albums.data.entity.PostModels;
import com.example.timur.albums.ui.IProgressBar;
import com.example.timur.albums.ui.LifeCycle;

import java.util.ArrayList;

public interface PostsContract {

    interface View extends IProgressBar{
        void getAllPosts(ArrayList<PostModels> arrayList);

        void showGetPostsError(String msg);
    }

    interface Presenter extends LifeCycle<View>{
        void getPosts();
    }
}
