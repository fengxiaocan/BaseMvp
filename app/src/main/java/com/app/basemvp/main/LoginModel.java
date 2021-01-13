package com.app.basemvp.main;

import android.os.SystemClock;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.app.mvp.BaseModel;
import com.app.mvp.Observer;

public class LoginModel extends BaseModel implements LoginMvp.LoginModel {

    @Override
    public void login(String userName, String password, Observer<String> observer) {
        new Thread(() -> {
            SystemClock.sleep(2000);
            observer.setValue("成功");
        }).start();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
        super.onStateChanged(source, event);
    }
}
