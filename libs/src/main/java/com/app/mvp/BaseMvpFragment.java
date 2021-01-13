package com.app.mvp;

import androidx.fragment.app.Fragment;

public class BaseMvpFragment extends Fragment implements LifeDataProvider.BaseLifeDataOwner {
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
