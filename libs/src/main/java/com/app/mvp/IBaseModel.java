package com.app.mvp;

import androidx.lifecycle.LifecycleEventObserver;

/**
 * Model层基类
 */
public interface IBaseModel extends LifecycleEventObserver {
    void onDetach();
}
