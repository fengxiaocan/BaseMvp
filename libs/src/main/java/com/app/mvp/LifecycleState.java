package com.app.mvp;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

import com.app.lifedata.LifecycleData;

public class LifecycleState implements LifecycleEventObserver, LifecycleData {
    private Lifecycle.Event state = null;

    @Override
    public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
        if (state != event) {
            //防止二次回调
            state = event;
            if (isDestroy()) {
                onDetach();
            }
        }
    }

    /**
     * 活跃可用的
     *
     * @return
     */
    protected final boolean isActive() {
        return !isAtLeast(Lifecycle.Event.ON_DESTROY);
    }

    /**
     * 在前台
     *
     * @return
     */
    protected final boolean isForegoing() {
        return state == Lifecycle.Event.ON_RESUME;
    }

    /**
     * 状态
     *
     * @return
     */
    protected final Lifecycle.Event state() {
        return state;
    }

    /**
     * 注销了
     *
     * @return
     */
    protected final boolean isDestroy() {
        return isAtLeast(Lifecycle.Event.ON_DESTROY);
    }

    protected final boolean isAtLeast(@NonNull Lifecycle.Event event) {
        return state != null && state.compareTo(event) >= 0;
    }

    protected final void destroy(){
        state = Lifecycle.Event.ON_DESTROY;
    }

    @Override
    public void onDetach() {
        destroy();
    }
}
