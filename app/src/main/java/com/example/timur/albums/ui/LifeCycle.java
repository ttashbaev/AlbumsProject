package com.example.timur.albums.ui;

public interface LifeCycle<V> {

    void bind(V view);

    void unbind();
}
