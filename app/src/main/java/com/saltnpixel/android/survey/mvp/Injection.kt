package com.saltnpixel.android.survey.mvp

import android.arch.lifecycle.ViewModelProvider
import com.saltnpixel.android.survey.mvp.presenters.DashBoardPresenter
import com.saltnpixel.android.survey.mvp.presenters.LoginPresenter
import com.saltnpixel.android.survey.mvp.viewmodelfactory.DashBoardViewModelFactory
import com.saltnpixel.android.survey.mvp.viewmodelfactory.LoginViewModelFactory

object Injection {
    fun provideViewModelFactoryLogin(loginPresenter: LoginPresenter): ViewModelProvider.Factory {
        return LoginViewModelFactory(loginPresenter)
    }
    fun provideViewModelFactoryDashBoard(dashBoardPresenter: DashBoardPresenter): ViewModelProvider.Factory {
        return DashBoardViewModelFactory(dashBoardPresenter)
    }
}