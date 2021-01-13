package com.app.mvp;


import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

public class LifeDataProvider implements LifecycleEventObserver {

    private final Factory mvpFactory;
    private final LifeDataStore mvpStore;
    private Lifecycle mLifecycle;

    public LifeDataProvider(@NonNull LifeDataStore store, Factory factory, @NonNull Lifecycle lifecycle) {
        mvpFactory = factory;
        mvpStore = store;
        this.mLifecycle = lifecycle;
        lifecycle.addObserver(this);
    }
    
    public static LifeDataProvider of(BaseLifeDataOwner owner) {
        return of(owner, null);
    }

    public static LifeDataProvider of(BaseLifeDataOwner owner, Factory factory) {
        return of(owner, factory, owner.getLifecycle());
    }

    public static LifeDataProvider of(LifeDataStoreOwner owner, Factory factory, Lifecycle lifecycle) {
        return new LifeDataProvider(owner.getLifeDataStore(), factory, lifecycle);
    }

    public static <P extends LifecycleData> P get(@NonNull Lifecycle lifecycle, @NonNull Class<P> modelClass) {
        P p = createInstance(modelClass);
        lifecycle.addObserver(p);
        return p;
    }

    private static <P extends LifecycleData> P createInstance(@NonNull Class<P> modelClass) {
        try {
            return modelClass.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException("Cannot create an instance of " + modelClass, e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Cannot create an instance of " + modelClass, e);
        }
    }

    @NonNull
    public <P extends LifecycleData> P get(@NonNull Class<P> modelClass) {
        String canonicalName = modelClass.getCanonicalName();
        if (canonicalName == null) {
            throw new IllegalArgumentException("Local and anonymous classes can not be LifecycleData");
        }
        return get(canonicalName, modelClass);
    }

    @NonNull
    public <P extends LifecycleData> P get(@NonNull String key, @NonNull Class<P> modelClass) {
        LifecycleData lifecycleData = mvpStore.get(key);
        if (modelClass.isInstance(lifecycleData)) {
            return (P) lifecycleData;
        } else {
            if (lifecycleData != null) {
                //Log.d(TAG,"is different");
            }
        }
        if (mvpFactory == null) {
            lifecycleData = createInstance(modelClass);
        } else {
            lifecycleData = mvpFactory.create(modelClass);
        }
        mvpStore.put(key, lifecycleData);
        return (P) lifecycleData;
    }

    public void put(@NonNull LifecycleData presenter) {
        put(presenter.getClass().getCanonicalName(), presenter);
    }

    public void put(@NonNull String key, @NonNull LifecycleData presenter) {
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

    public interface BaseLifeDataOwner extends LifecycleOwner, LifeDataStoreOwner {
    }

    public interface LifeDataStoreOwner {
        LifeDataStore getLifeDataStore();
    }

    public interface Factory {
        @NonNull
        <P extends LifecycleData> P create(@NonNull Class<P> modelClass);
    }

    public static class InstanceFactory implements Factory {
        @NonNull
        @Override
        public <P extends LifecycleData> P create(@NonNull Class<P> modelClass) {
            return createInstance(modelClass);
        }
    }
}
