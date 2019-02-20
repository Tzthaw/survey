package com.saltnpixel.android.survey.mvp.presenters

import android.arch.lifecycle.MutableLiveData
import com.saltnpixel.android.shared.model.BasePresenter
import com.saltnpixel.android.survey.entities.DashBoardData
import com.saltnpixel.android.survey.mvp.views.DashBoardView
import com.saltnpixel.android.survey.repositories.DashBoardRepository

class DashBoardPresenter:BasePresenter<DashBoardView>() {
    private lateinit var mDashBoardDataLD: MutableLiveData<DashBoardData>
    override fun initPresenter(mView: DashBoardView) {
        super.initPresenter(mView)
        mDashBoardDataLD= MutableLiveData()
    }

    fun onNotifyDashBoardData(){
        DashBoardRepository.getInstance().getAllDashBoardData(mDashBoardDataLD,errorLD)
    }
    val dashResultLD:MutableLiveData<DashBoardData>?
        get() = mDashBoardDataLD
}