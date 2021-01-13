package com.app.basemvp.main;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.app.mvp.BasePresenter;
import com.app.mvp.Observer;

public class LoginPresenter extends BasePresenter<LoginModel, LoginMvp.LoginView> implements LoginMvp.LoginPresenter {

    @Override
    public void login(String userName, String password) {
        if (isActive()) {
            if (isViewAttached()) {
                getView().waiting();
            }
            getModel().login(userName, password, new Observer<String>() {
                @Override
                public void setValue(String data) {
                    if (isViewAttached()) {
                        getView().loginSuccess(data);
                    }
                }

                @Override
                public void onError(Throwable error) {
                    if (isViewAttached()) {
                        getView().loginFailure(error.getMessage());
                    }
                }
            });
        }
    }

    @Override
    public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
        super.onStateChanged(source, event);
    }

    @Override
    public void attachView(LoginMvp.LoginView view) {
        super.attachView(view);
        Log.e("noah","attachView");
    }

    @Override
    public void detachView() {
        super.detachView();
        Log.e("noah","detachView");
    }

    @Override
    protected LoginModel createModel() {
        return super.createModel();
    }
}
