package com.saltnpixel.android.survey.repositories

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.saltnpixel.android.shared.data.EmptyError
import com.saltnpixel.android.shared.data.FailError
import com.saltnpixel.android.shared.data.ResultError
import com.saltnpixel.android.shared.data.ServerError
import com.saltnpixel.android.survey.entities.GetLoginResponse
import com.saltnpixel.android.survey.entities.LoginData
import com.saltnpixel.android.survey.utils.scheduler
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class LoginRepository private constructor(context: Context):BaseRepository(){

    companion object {
        var INSTANCE: LoginRepository? = null
        fun getInstance(): LoginRepository {
            if (INSTANCE == null) {
                throw RuntimeException("LoginRepository is being invoked before initializing")
            }
            val i = INSTANCE
            return i!!
        }

        fun inintRepository(context: Context) {
            INSTANCE = LoginRepository(context)
        }
    }

    fun checkLoginResponse(
         email:String,
         password:String,
         mLoginResult:MutableLiveData<LoginData>,
         errorLD:MutableLiveData<ResultError>
    ){
        mTheApi.checkLoginSuccess(email,password)
            .subscribeOn(scheduler)
            .observeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<GetLoginResponse> {
                override fun onComplete() {}
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(getLoginResponse: GetLoginResponse) {
                    if(getLoginResponse.loginData!=null){
                        mLoginResult.value=getLoginResponse.getLoginData()
                    }else{
                        errorLD.value=FailError("error","message")
                    }
                }
                override fun onError(e: Throwable) {
                    errorLD.value= ServerError(e.message!!)
                }
            })
    }
}