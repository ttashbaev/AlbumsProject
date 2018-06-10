package com.example.timur.albums.ui.posts;

import android.support.annotation.NonNull;

import com.example.timur.albums.data.ResourceManager;
import com.example.timur.albums.data.entity.PostModels;
import com.example.timur.albums.data.network.RetrofitService;
import com.example.timur.albums.ui.posts.PostsContract.Presenter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsPresenter implements Presenter {

    private RetrofitService mService;
    private PostsContract.View mView;
    private ResourceManager mManager;


    public PostsPresenter(RetrofitService service, ResourceManager manager) {
        this.mService = service;
        this.mManager = manager;
    }

    @Override
    public void getPosts() {
        if (isViewAttached()) {
            mView.showLoadingIndicator();
        }

        mService.getCallbackPosts().enqueue(new Callback<ArrayList<PostModels>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<PostModels>> call, @NonNull Response<ArrayList<PostModels>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (isViewAttached()) {
                        mView.getAllPosts(response.body());
                        mView.hideLoadingIndicator();
                    }
                } else {
                    if (isViewAttached()) {
                        mView.showGetPostsError(response.message());
                        mView.hideLoadingIndicator();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<PostModels>> call, @NonNull Throwable t) {
                if (isViewAttached()) {
                    mView.showGetPostsError(t.getMessage());
                    mView.hideLoadingIndicator();
                }
            }
        });
    }

    @Override
    public void bind(PostsContract.View view) {
        this.mView = view;
    }

    @Override
    public void unbind() {
        this.mView = null;
    }

    private boolean isViewAttached() {
        return mView != null;
    }
}
