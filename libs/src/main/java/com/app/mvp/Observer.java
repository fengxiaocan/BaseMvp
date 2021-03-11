package com.app.mvp;

public interface Observer<T> {
    void onResult(T data);
}
