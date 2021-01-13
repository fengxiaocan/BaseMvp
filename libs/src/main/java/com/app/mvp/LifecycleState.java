package com.app.mvp;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

class LifecycleState implements LifecycleEventObserver {
    private Lifecycle.Event state = null;

    @Override
    public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
        state = event;
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

    void destroy() {
        state = Lifecycle.Event.ON_DESTROY;
    }
}
