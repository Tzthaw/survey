package com.saltnpixel.android.survey.repositories

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.saltnpixel.android.shared.data.ResultError
import com.saltnpixel.android.shared.data.ServerError
import com.saltnpixel.android.survey.entities.DashBoardData
import com.saltnpixel.android.survey.utils.access_token
import com.saltnpixel.android.survey.utils.scheduler
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class DashBoardRepository private constructor(context: Context):BaseRepository() {

    companion object {
        var INSTANCE: DashBoardRepository? = null
        fun getInstance(): DashBoardRepository {
            if (INSTANCE == null) {
                throw RuntimeException("DashBoardRepository is being invoked before initializing")
            }
            val i = INSTANCE
            return i!!
        }

        fun inintRepository(context: Context) {
            INSTANCE = DashBoardRepository(context)
        }
    }

    fun getAllDashBoardData(
        mDashBoardDataLD:MutableLiveData<DashBoardData>,
        mErrorLD:MutableLiveData<ResultError>
    ){
        mTheApi.getDashBoardData(access_token)
            .subscribeOn(scheduler)
            .observeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<DashBoardData> {
                override fun onComplete() {}
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(data: DashBoardData) {
                    mDashBoardDataLD.value=data
                }
                override fun onError(e: Throwable) {
                    mErrorLD.value= ServerError(e.message!!)
                }
            })
    }
}