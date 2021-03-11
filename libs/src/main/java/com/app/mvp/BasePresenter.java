package com.app.mvp;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import java.lang.ref.WeakReference;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * presenter 的实现类,封装基本方法
 *
 * @param <M>
 * @param <V>
 */
public abstract class BasePresenter<M extends IBaseModel, V> extends LifecycleState
        implements IBasePresenter<V> {
    private WeakReference<V> baseView;
    private M baseModel;

    protected BasePresenter() {
        this.baseModel = createModel();
    }

    protected M createModel() {
        return defaultCreateModel();
    }

    @Override
    public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
        super.onStateChanged(source, event);
        getModel().onStateChanged(source, event);
    }

    @Override
    public void onAttach(V view) {
        baseView = new WeakReference<V>(view);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (baseView != null) {
            baseView.clear();
            baseView = null;
        }
        getModel().onDetach();
    }

    protected final boolean isViewAttached() {
        return baseView != null && baseView.get() != null;
    }

    protected final V getView() {
        return baseView.get();
    }

    protected final M getModel() {
        return baseModel;
    }

    protected final M defaultCreateModel() {
        try {
            Type genericSuperclass = getClass().getGenericSuperclass();
            Class<M> type = (Class<M>) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
            return type.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
