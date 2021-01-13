package com.app.basemvp.sms_login

import com.app.mvp.BasePresenter

class SmsLoginPresenter:BasePresenter<SmsLoginMvp.IModel?, SmsLoginMvp.IView?>(),
        SmsLoginMvp.IPresenter {

    override fun createModel():SmsLoginModel {
        return SmsLoginModel()
    }

    override fun login(phone:String, sms:String) {

    }

}