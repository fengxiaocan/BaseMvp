package com.app.basemvp.user_login

import com.app.mvp.IBaseModel
import com.app.mvp.IBasePresenter

interface UserLoginMvp {

    interface IPresenter:IBasePresenter<IView?> {

    }

    interface IModel:IBaseModel {

    }

    interface IView{

    }

}