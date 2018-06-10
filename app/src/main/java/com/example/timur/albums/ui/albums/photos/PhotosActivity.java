package com.example.timur.albums.ui.albums.photos;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.timur.albums.R;
import com.example.timur.albums.StartApp;
import com.example.timur.albums.data.ResourceManager;
import com.example.timur.albums.data.entity.PhotoModel;
import com.example.timur.albums.data.network.RetrofitService;
import com.example.timur.albums.ui.BaseActivity;
import com.example.timur.albums.ui.albums.photos.photoDetails.PhotoDetailActivity;

import java.util.ArrayList;

public class PhotosActivity extends BaseActivity implements OnItemClickListener, PhotosContract.View{

    private GridView mGridView;
    private PhotosPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        initUI();
        init();
    }

    private void initUI() {
        mGridView = findViewById(R.id.grid_view_photos);
        mGridView.setOnItemClickListener(this);
        initToolbar();
    }

    private void init() {
        RetrofitService service = StartApp.get(this).getService();
        mPresenter = new PhotosPresenter(service, new ResourceManager(this));
        mPresenter.bind(this);
        mPresenter.getPhotos(getIntent().getIntExtra("albumId", 0));
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getApplicationContext(), PhotoDetailActivity.class);
        intent.putExtra("photo", ((PhotoModel)parent.getItemAtPosition(position)).getUrl());
        startActivity(intent);
    }

    @Override
    public void getAllPhotos(ArrayList<PhotoModel> arrayList) {
        mGridView.setAdapter(new PhotosAdapter(this, arrayList));
    }

    @Override
    public void showGetPhotosError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
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
