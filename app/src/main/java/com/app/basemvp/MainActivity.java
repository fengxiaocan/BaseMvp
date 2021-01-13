package com.app.basemvp;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.app.basemvp.main.LoginMvp;
import com.app.basemvp.main.LoginPresenter;
import com.app.mvp.BaseMvpActivity;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends BaseMvpActivity implements LoginMvp.LoginView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        provider().get(LoginPresenter.class).attachView(this);
    }

    private String getUserName() {
        return "";
    }

    private String getPassword() {
        return "";
    }

    public void viewClick(View view) {
        provider().get(LoginPresenter.class)
                .login(getUserName(), getPassword());
    }

    public void clickExit(View view) {
        finish();
    }

    @Override
    public void waiting() {
        Snackbar.make(findViewById(android.R.id.content), "正在登录", 4000).show();
    }

    @Override
    public void loginSuccess(String message) {
        runOnUiThread(() -> Snackbar.make(findViewById(android.R.id.content), message, 4000).show());
    }

    @Override
    public void loginFailure(String message) {
        runOnUiThread(() -> Snackbar.make(findViewById(android.R.id.content), message, 4000).show());
    }
}
