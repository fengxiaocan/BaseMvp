package com.app.mvp;

import com.app.lifedata.LifecycleData;

/**
 * Presenter 基类
 */
public interface IBasePresenter<V> extends LifecycleData {
    void onAttach(V view);
}
