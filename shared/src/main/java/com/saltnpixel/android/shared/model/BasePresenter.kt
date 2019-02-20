package com.saltnpixel.android.shared.model

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.saltnpixel.android.shared.data.ResultError
import com.saltnpixel.android.shared.ui.BaseView

abstract class BasePresenter<T : BaseView> : ViewModel() {

    private lateinit var mView: T
    private lateinit var mErrorLD: MutableLiveData<ResultError>

    val errorLD: MutableLiveData<ResultError>
        get() = mErrorLD

    open fun initPresenter(mView: T) {
        this.mView = mView
        mErrorLD = MutableLiveData()
    }
}