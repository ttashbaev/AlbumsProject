package com.example.timur.albums.ui.posts.comments;

import android.support.annotation.NonNull;

import com.example.timur.albums.data.ResourceManager;
import com.example.timur.albums.data.entity.CommentsModel;
import com.example.timur.albums.data.entity.PostModels;
import com.example.timur.albums.data.network.RetrofitService;
import com.example.timur.albums.ui.posts.PostsContract;
import com.example.timur.albums.ui.posts.PostsContract.Presenter;
import com.example.timur.albums.ui.posts.comments.CommentsContract.View;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentsPresenter implements CommentsContract.Presenter {

    private RetrofitService mService;
    private CommentsContract.View mView;
    private ResourceManager mManager;


    public CommentsPresenter(RetrofitService service, ResourceManager manager){
        this.mService = service;
        this.mManager = manager;
    }

    @Override
    public void bind(View view) {
        this.mView = view;
    }

    @Override
    public void unbind() {
        this.mView = null;
    }

    private boolean isViewAttached(){
        return mView != null;
    }

    @Override
    public void getComments(int userId) {
        if(isViewAttached()){
            mView.showLoadingIndicator();
        }
        mService.getCallbackComments(userId).enqueue(new Callback<ArrayList<CommentsModel>>() {
            @Override
            public void onResponse(Call<ArrayList<CommentsModel>> call, Response<ArrayList<CommentsModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (isViewAttached()){
                        mView.getAllComments(response.body());
                        mView.hideLoadingIndicator();
                    }
                } else {
                    if (isViewAttached()){
                        mView.showGetCommentsError(response.message());
                        mView.hideLoadingIndicator();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<CommentsModel>> call, Throwable t) {
                if (isViewAttached()){
                    mView.showGetCommentsError(t.getMessage());
                    mView.hideLoadingIndicator();
                }
            }
        });

    }
}
