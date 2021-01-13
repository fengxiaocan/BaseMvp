package com.app.basemvp.sms_login;

import com.app.mvp.BaseModel;
import com.app.basemvp.sms_login.SmsLoginMvp;
import com.app.mvp.Observer

class SmsLoginModel:BaseModel(),SmsLoginMvp.IModel {
    override fun login(phone:String, sms:String, observer:Observer<String>) {

    }
}
