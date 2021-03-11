package com.app.basemvp.main;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.app.mvp.BasePresenter;

public class LoginPresenter extends BasePresenter<LoginModel, LoginMvp.LoginView> implements LoginMvp.LoginPresenter {

    @Override
    public void login(String userName, String password) {
        if (isActive()) {
            if (isViewAttached()) {
                getView().waiting();
            }
            getModel().login(userName, password, success -> {
                if (isViewAttached()) {
                    getView().loginSuccess(success);
                }
            }, error -> {
                if (isViewAttached()) {
                    getView().loginFailure(error.getMessage());
                }
            });
        }
    }

    @Override
    public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
        super.onStateChanged(source, event);
    }

    @Override
    public void onAttach(LoginMvp.LoginView view) {
        super.onAttach(view);
        Log.e("noah","attachView");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("noah", "login detachView");
    }

    @Override
    protected LoginModel createModel() {
        return super.createModel();
    }
}
