package com.example.timur.albums.ui.albums;

import com.example.timur.albums.data.entity.AlbumModel;
import com.example.timur.albums.data.entity.CommentsModel;
import com.example.timur.albums.ui.IProgressBar;
import com.example.timur.albums.ui.LifeCycle;

import java.util.ArrayList;

public interface AlbumsContract {

    interface View extends IProgressBar{
        void getAllAlbums(ArrayList<AlbumModel> arrayList);

        void showGetAlbumsError(String msg);
    }

    interface Presenter extends LifeCycle<View>{
        void getAlbums();
    }
}
