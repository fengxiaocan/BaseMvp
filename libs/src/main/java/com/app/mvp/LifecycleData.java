package com.app.mvp;

import androidx.lifecycle.LifecycleEventObserver;

public interface LifecycleData extends LifecycleEventObserver {
    void onDetach();
}