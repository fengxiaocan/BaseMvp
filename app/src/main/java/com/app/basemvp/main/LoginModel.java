package com.app.basemvp.main;

import android.os.SystemClock;

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
}
