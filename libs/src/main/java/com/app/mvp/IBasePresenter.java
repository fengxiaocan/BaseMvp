package com.app.mvp;

import androidx.lifecycle.LifecycleEventObserver;

/**
 * Presenter 基类
 */
public interface IBasePresenter<V> extends LifecycleEventObserver {
    void attachView(V view);

    void detachView();
}
