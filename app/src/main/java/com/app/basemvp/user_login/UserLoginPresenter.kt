package com.app.basemvp.user_login

import com.app.mvp.BasePresenter

class UserLoginPresenter:BasePresenter<UserLoginMvp.IModel?, UserLoginMvp.IView?>(),
        UserLoginMvp.IPresenter {

    override fun createModel() = UserLoginModel()
}