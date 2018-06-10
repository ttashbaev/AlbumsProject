package com.example.timur.albums.ui.posts.comments;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.timur.albums.R;
import com.example.timur.albums.StartApp;
import com.example.timur.albums.data.ResourceManager;
import com.example.timur.albums.data.entity.CommentsModel;
import com.example.timur.albums.data.entity.PostModels;
import com.example.timur.albums.data.network.RetrofitService;
import com.example.timur.albums.ui.BaseActivity;

import java.util.ArrayList;

public class CommentsActivity extends BaseActivity implements CommentsContract.View {
    private int mUserId;
    private CommentsPresenter mPresenter;
    private RetrofitService mService;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        initToolbar();
        mService = StartApp.get(this).getService();
        Intent intent = getIntent();
        mUserId = intent.getIntExtra("userId", 0);
        mPresenter = new CommentsPresenter(mService, new ResourceManager(getApplicationContext()));
        mPresenter.bind(this);
        mPresenter.getComments(mUserId);
        mListView = findViewById(R.id.list_view_comments);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getAllComments(ArrayList<CommentsModel> arrayList) {
        mListView.setAdapter(new CommentsAdapter(getApplicationContext(), arrayList));
    }

    @Override
    public void showGetCommentsError(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadingIndicator() {
        showProgressBar();
    }

    @Override
    public void hideLoadingIndicator() {
        dismissProgressBar();
    }
}
