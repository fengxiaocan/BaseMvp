package com.app.basemvp.main;

import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.app.mvp.BaseModel;
import com.app.mvp.Observer;

public class LoginModel extends BaseModel implements LoginMvp.LoginModel {


    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("noah","LoginModel onDetach");
    }

    @Override
    public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
        super.onStateChanged(source, event);
        Log.e("noah","LoginModel onStateChanged;"+event.name());
    }

    @Override
    public void login(String userName, String password, Observer<String> success, Observer<Throwable> error) {
        new Thread(() -> {
            SystemClock.sleep(2000);
            success.onResult("成功");
        }).start();
    }
}
