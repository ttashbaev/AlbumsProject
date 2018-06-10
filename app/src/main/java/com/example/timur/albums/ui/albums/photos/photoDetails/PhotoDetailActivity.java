package com.example.timur.albums.ui.albums.photos.photoDetails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.timur.albums.R;
import com.example.timur.albums.ui.BaseActivity;

public class PhotoDetailActivity extends BaseActivity {
    private ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);
        mImage = findViewById(R.id.image_full);
        initToolbar();
        Glide.with(this)
                .load(getIntent().getStringExtra("photo"))
                .into(mImage);
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

}
