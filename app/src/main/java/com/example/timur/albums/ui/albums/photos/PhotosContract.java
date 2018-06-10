package com.example.timur.albums.ui.albums.photos;

import com.example.timur.albums.data.entity.PhotoModel;
import com.example.timur.albums.ui.IProgressBar;
import com.example.timur.albums.ui.LifeCycle;

import java.util.ArrayList;

public interface PhotosContract {

    interface View extends IProgressBar{
        void getAllPhotos(ArrayList<PhotoModel> arrayList);

        void showGetPhotosError(String msg);

    }

    interface Presenter extends LifeCycle<View> {
        void getPhotos (int albumId);
    }
}
