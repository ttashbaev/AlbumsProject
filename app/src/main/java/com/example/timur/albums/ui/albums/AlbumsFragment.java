package com.example.timur.albums.ui.albums;

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
import android.widget.Toolbar;

import com.example.timur.albums.R;
import com.example.timur.albums.StartApp;
import com.example.timur.albums.data.ResourceManager;
import com.example.timur.albums.data.entity.AlbumModel;
import com.example.timur.albums.data.entity.PhotoModel;
import com.example.timur.albums.data.entity.PostModels;
import com.example.timur.albums.data.network.RetrofitService;
import com.example.timur.albums.ui.BaseFragment;
import com.example.timur.albums.ui.albums.photos.PhotosActivity;

import java.util.ArrayList;
import java.util.Random;

public class AlbumsFragment extends BaseFragment implements OnItemClickListener, AlbumsContract.View{
    private ListView mListView;
    private AlbumsPresenter mPresenter;
    private ArrayList<AlbumModel> mListRandom;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListRandom = new ArrayList<>();
        mPresenter = new AlbumsPresenter(StartApp.get(getContext()).getService(), new ResourceManager(getContext()));
        mPresenter.bind(this);
        mPresenter.getAlbums();
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), PhotosActivity.class);
        intent.putExtra("albumId", ((AlbumModel)parent.getItemAtPosition(position)).getUserId());
        Log.d("albumId", "onItemClick: " + ((AlbumModel)parent.getItemAtPosition(position)).getUserId());
        startActivity(intent);
    }

    @Override
    public void getAllAlbums(ArrayList<AlbumModel> arrayList) {
        Random random = new Random();
        for (int i = 0; i < 14; i++) {
            mListRandom.add(i, arrayList.get(random.nextInt(arrayList.size())));
        }
        AlbumAdapter adapter = new AlbumAdapter(getContext(), mListRandom);
        mListView.setAdapter(adapter);
    }

    @Override
    public void showGetAlbumsError(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
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
}
