package com.app.mvp;


import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

public class MvpProvider implements LifecycleEventObserver {

    private final Factory mvpFactory;
    private final MvpStore mvpStore;
    private Lifecycle mLifecycle;

    public MvpProvider(@NonNull MvpStore store, Factory factory, @NonNull Lifecycle lifecycle) {
        mvpFactory = factory;
        mvpStore = store;
        this.mLifecycle = lifecycle;
        lifecycle.addObserver(this);
    }


    public static MvpProvider of(BaseMvpOwner owner) {
        return of(owner, null);
    }

    public static MvpProvider of(BaseMvpOwner owner, Factory factory) {
        return of(owner, factory, owner.getLifecycle());
    }

    public static MvpProvider of(MvpStoreOwner owner, Factory factory, Lifecycle lifecycle) {
        return new MvpProvider(owner.getMvpStore(), factory, lifecycle);
    }


    public static <P extends IBasePresenter<V>, V> P get(@NonNull Lifecycle lifecycle, @NonNull Class<P> modelClass) {
        P p = create(modelClass);
        lifecycle.addObserver(p);
        return p;
    }

    private static <P extends IBasePresenter<V>, V> P create(@NonNull Class<P> modelClass) {
        try {
            return modelClass.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException("Cannot create an instance of " + modelClass, e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Cannot create an instance of " + modelClass, e);
        }
    }

    @NonNull
    public <P extends IBasePresenter<V>, V> P get(@NonNull Class<P> modelClass) {
        String canonicalName = modelClass.getCanonicalName();
        if (canonicalName == null) {
            throw new IllegalArgumentException("Local and anonymous classes can not be IBasePresenter");
        }
        return get(canonicalName, modelClass);
    }

    @NonNull
    public <P extends IBasePresenter<V>, V> P get(@NonNull String key, @NonNull Class<P> modelClass) {
        IBasePresenter presenter = mvpStore.get(key);
        if (modelClass.isInstance(presenter)) {
            return (P) presenter;
        } else {
            if (presenter != null) {
            }
        }
        if (mvpFactory == null) {
            presenter = create(modelClass);
        } else {
            presenter = mvpFactory.create(modelClass);
        }
        mvpStore.put(key, presenter);
        return (P) presenter;
    }

    public void put(@NonNull IBasePresenter presenter) {
        put(presenter.getClass().getCanonicalName(), presenter);
    }

    public void put(@NonNull String key, @NonNull IBasePresenter presenter) {
        mvpStore.put(key, presenter);
    }

    @Override
    public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
        for (String key : mvpStore.keys()) {
            mvpStore.get(key).onStateChanged(source, event);
        }
        if (event == Lifecycle.Event.ON_DESTROY) {
            mvpStore.clear();
            if (mLifecycle != null) {
                mLifecycle.removeObserver(this);
                mLifecycle = null;
            }
        }
    }

    public interface BaseMvpOwner extends LifecycleOwner, MvpStoreOwner {
    }

    public interface MvpStoreOwner {
        MvpStore getMvpStore();
    }

    public interface Factory {
        @NonNull
        <P extends IBasePresenter<V>, V> P create(@NonNull Class<P> modelClass);
    }

    public static class NewInstanceFactory implements Factory {
        @NonNull
        @Override
        public <P extends IBasePresenter<V>, V> P create(@NonNull Class<P> modelClass) {
            return create(modelClass);
        }
    }
}
