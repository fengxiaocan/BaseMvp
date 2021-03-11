package com.app.mvp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class BaseMvpActivity extends AppCompatActivity implements LifeDataProvider.BaseLifeDataOwner {
    private LifeDataStore mvpStore;
    private LifeDataProvider provider;

    @Override
    public LifeDataStore getLifeDataStore() {
        return mvpStore == null ? mvpStore = new LifeDataStore() : mvpStore;
    }

    public LifeDataProvider provider() {
        return provider == null ? provider = LifeDataProvider.of(this) : provider;
    }

    public <T extends LifecycleData> T getProvider(@NonNull Class<T> modelClass) {
        return provider().get(modelClass);
    }

    public <T extends LifecycleData> T getProvider(@NonNull String key, @NonNull Class<T> modelClass) {
        return provider().get(key, modelClass);
    }
}
