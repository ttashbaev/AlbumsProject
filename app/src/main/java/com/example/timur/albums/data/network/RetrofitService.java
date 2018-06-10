package com.example.timur.albums.data.network;

import com.example.timur.albums.config.AppConstants;
import com.example.timur.albums.data.entity.AlbumModel;
import com.example.timur.albums.data.entity.CommentsModel;
import com.example.timur.albums.data.entity.PhotoModel;
import com.example.timur.albums.data.entity.PostModels;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {


    @GET(AppConstants.POSTS_ENDPOINT)
    Call<ArrayList<PostModels>> getCallbackPosts ();

    @GET(AppConstants.COMMENTS_ENDPOINT)
    Call<ArrayList<CommentsModel>> getCallbackComments (@Query("postId") int postId);

    @GET(AppConstants.ALBUMS_ENDPOINT)
    Call<ArrayList<AlbumModel>> getCallbackAlbums();

    @GET(AppConstants.PHOTOS_ENDPOINT)
    Call<ArrayList<PhotoModel>> getCallbackPhotos (@Query("albumId") int albumId);
}
