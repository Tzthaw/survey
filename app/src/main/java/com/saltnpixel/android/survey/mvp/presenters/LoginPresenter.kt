package com.saltnpixel.android.survey.mvp.presenters

import android.arch.lifecycle.MutableLiveData
import com.saltnpixel.android.shared.model.BasePresenter
import com.saltnpixel.android.survey.entities.LoginData
import com.saltnpixel.android.survey.mvp.views.LoginView
import com.saltnpixel.android.survey.repositories.LoginRepository

class LoginPresenter:BasePresenter<LoginView>() {
    private lateinit var mLoginResultLD:MutableLiveData<LoginData>
    override fun initPresenter(mView: LoginView) {
        super.initPresenter(mView)
        mLoginResultLD=MutableLiveData()
    }

    fun onNotifyCheckLogin(
        email:String,
        password:String
    ){
        LoginRepository.getInstance().checkLoginResponse(email,password,mLoginResultLD,errorLD)
    }

    val loginResultLD:MutableLiveData<LoginData>?
        get() = mLoginResultLD
}