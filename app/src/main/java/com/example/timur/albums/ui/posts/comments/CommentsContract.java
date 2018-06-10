package com.example.timur.albums.ui.posts.comments;

import com.example.timur.albums.data.entity.CommentsModel;
import com.example.timur.albums.data.entity.PostModels;
import com.example.timur.albums.ui.IProgressBar;
import com.example.timur.albums.ui.LifeCycle;

import java.util.ArrayList;

public interface CommentsContract {

    interface View extends IProgressBar{
        void getAllComments(ArrayList<CommentsModel> arrayList);

        void showGetCommentsError(String msg);
    }

    interface Presenter extends LifeCycle<View>{
        void getComments(int userId);
    }
}
