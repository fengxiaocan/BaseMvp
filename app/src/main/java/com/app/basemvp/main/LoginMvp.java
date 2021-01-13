package com.app.basemvp.main;

import com.app.mvp.IBaseModel;
import com.app.mvp.IBasePresenter;
import com.app.mvp.Observer;

public interface LoginMvp {
    interface LoginPresenter extends IBasePresenter<LoginView> {
        void login(String userName, String password);
    }

    interface LoginModel extends IBaseModel {
        void login(String userName, String password, Observer<String> observer);
    }

    interface LoginView {
        void waiting();

        void loginSuccess(String message);

        void loginFailure(String message);
    }
}
