package com.example.timur.albums.ui.albums;

import com.example.timur.albums.data.ResourceManager;
import com.example.timur.albums.data.entity.AlbumModel;
import com.example.timur.albums.data.network.RetrofitService;
import com.example.timur.albums.ui.albums.AlbumsContract.View;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumsPresenter implements AlbumsContract.Presenter {
    private RetrofitService mService;
    private AlbumsContract.View mView;
    private ResourceManager mManager;

    public AlbumsPresenter(RetrofitService service, ResourceManager manager) {
        this.mService = service;
        this.mManager = manager;
    }

    @Override
    public void getAlbums() {
        if (isViewAttached()) {
            mView.showLoadingIndicator();
        }

        mService.getCallbackAlbums().enqueue(new Callback<ArrayList<AlbumModel>>() {
            @Override
            public void onResponse(Call<ArrayList<AlbumModel>> call, Response<ArrayList<AlbumModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (isViewAttached()) {
                        mView.getAllAlbums(response.body());
                        mView.hideLoadingIndicator();
                    }
                } else {
                    if (isViewAttached()) {
                        mView.showGetAlbumsError(response.message());
                        mView.hideLoadingIndicator();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<AlbumModel>> call, Throwable t) {
                if (isViewAttached()) {
                    mView.showGetAlbumsError(t.getMessage());
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

    private boolean isViewAttached() {
        return mView != null;
    }
}
