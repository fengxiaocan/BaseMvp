package com.app.mvp;

public interface Observer<T> {
    void setValue(T data);

    void onError(Throwable error);
}
