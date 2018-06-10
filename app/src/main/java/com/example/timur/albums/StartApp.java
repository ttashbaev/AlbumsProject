package com.example.timur.albums;

import android.app.Application;
import android.content.Context;

import com.example.timur.albums.data.network.NetworkBuilder;
import com.example.timur.albums.data.network.RetrofitService;

public class StartApp extends Application {

    private RetrofitService mService;

    @Override
    public void onCreate() {
        super.onCreate();
        mService = NetworkBuilder.initService();
    }

    public static StartApp get (Context context){
        return (StartApp) context.getApplicationContext();
    }

    public RetrofitService getService() {
        return mService;
    }
}
