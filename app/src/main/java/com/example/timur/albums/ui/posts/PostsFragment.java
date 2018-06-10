package com.example.timur.albums.ui.posts;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.timur.albums.R;
import com.example.timur.albums.StartApp;
import com.example.timur.albums.data.ResourceManager;
import com.example.timur.albums.data.entity.PostModels;
import com.example.timur.albums.data.network.RetrofitService;
import com.example.timur.albums.ui.BaseFragment;
import com.example.timur.albums.ui.posts.comments.CommentsActivity;

import java.util.ArrayList;
import java.util.Random;

public class PostsFragment extends BaseFragment implements PostsContract.View, OnItemClickListener {

    private ListView mListView;
    private PostsPresenter mPresenter;
    private RetrofitService mService;
    private ArrayList<PostModels> mListRandom;
    //private boolean mCheckList = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListRandom = new ArrayList<>();
        mService = StartApp.get(getContext()).getService();
        mPresenter = new PostsPresenter(mService, new ResourceManager(getContext()));
        mPresenter.bind(this);
        mPresenter.getPosts();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_posts, container, false);
        mListView = view.findViewById(R.id.list_view_posts);
        mListView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void getAllPosts(ArrayList<PostModels> arrayList) {

        Random random = new Random();
        for (int i = 0; i < 19; i++) {
            mListRandom.add(i, arrayList.get(random.nextInt(arrayList.size())));
        }
        showAllPosts();
    }

    private void showAllPosts() {
            PostAdapter adapter = new PostAdapter(getContext(), mListRandom);
            mListView.setAdapter(adapter);
            //mCheckList = true;
    }


    @Override
    public void showGetPostsError(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        Log.e("Error ", "showGetPostsError: " + msg);
    }

    @Override
    public void showLoadingIndicator() {
        showProgressBar();
    }

    @Override
    public void hideLoadingIndicator() {
        dismissProgressBar();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unbind();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), CommentsActivity.class);
        intent.putExtra("userId", ((PostModels)parent.getItemAtPosition(position)).getUserId());
        startActivity(intent);
    }
}
