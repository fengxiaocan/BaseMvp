package com.app.mvp;

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

}
