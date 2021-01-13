package com.app.mvp;

/**
 * Presenter 基类
 */
public interface IBasePresenter<V> extends LifecycleData {
    void onAttach(V view);
}
