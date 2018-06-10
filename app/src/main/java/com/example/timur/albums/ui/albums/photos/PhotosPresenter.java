package com.example.timur.albums.ui.albums.photos;

import com.example.timur.albums.data.ResourceManager;
import com.example.timur.albums.data.entity.CommentsModel;
import com.example.timur.albums.data.entity.PhotoModel;
import com.example.timur.albums.data.network.RetrofitService;
import com.example.timur.albums.ui.albums.photos.PhotosContract.View;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotosPresenter implements PhotosContract.Presenter{

    private RetrofitService mService;
    private PhotosContract.View mView;
    private ResourceManager mManager;

    public PhotosPresenter(RetrofitService service, ResourceManager manager){
        this.mManager = manager;
        this.mService = service;
    }

    @Override
    public void getPhotos(int albumId) {
        if (isViewAttached()) mView.showLoadingIndicator();

        mService.getCallbackPhotos(albumId).enqueue(new Callback<ArrayList<PhotoModel>>() {
            @Override
            public void onResponse(Call<ArrayList<PhotoModel>> call, Response<ArrayList<PhotoModel>> response) {
                if (response.isSuccessful() && response.body() != null){
                    if (isViewAttached()){
                        mView.getAllPhotos(response.body());
                        mView.hideLoadingIndicator();
                    }
                } else {
                    if (isViewAttached()){
                        mView.showGetPhotosError(response.message());
                        mView.hideLoadingIndicator();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PhotoModel>> call, Throwable t) {
                if (isViewAttached()){
                    mView.showGetPhotosError(t.getMessage());
                    mView.hideLoadingIndicator();
                }
            }
        });
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
}
