package com.saltnpixel.android.survey

import android.app.Application
import com.saltnpixel.android.survey.repositories.DashBoardRepository
import com.saltnpixel.android.survey.repositories.LoginRepository

class SurveyApp :Application(){
    override fun onCreate() {
        super.onCreate()
        LoginRepository.inintRepository(this)
        DashBoardRepository.inintRepository(this)
    }
}