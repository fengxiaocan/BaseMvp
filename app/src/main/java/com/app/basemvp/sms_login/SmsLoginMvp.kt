package com.app.basemvp.sms_login;

import com.app.mvp.IBaseModel
import com.app.mvp.IBasePresenter
import com.app.mvp.Observer

interface SmsLoginMvp {

    interface IPresenter:IBasePresenter<IView?> {
        fun login(phone:String, sms:String)
    }

    interface IModel:IBaseModel {
        fun login(phone:String, sms:String, observer:Observer<String>)
    }

    interface IView {
        fun onWaiting()

        fun onSuccess(message:String)

        fun onFailure(message:String)
    }

}