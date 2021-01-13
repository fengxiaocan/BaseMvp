package com.app.mvp;

import androidx.appcompat.app.AppCompatActivity;

public class BaseMvpActivity extends AppCompatActivity implements MvpProvider.BaseMvpOwner {
    private MvpStore mvpStore;
    private MvpProvider provider;

    @Override
    public MvpStore getMvpStore() {
        return mvpStore == null ? mvpStore = new MvpStore() : mvpStore;
    }

    public MvpProvider provider() {
        return provider == null ? provider = MvpProvider.of(this) : provider;
    }

}
