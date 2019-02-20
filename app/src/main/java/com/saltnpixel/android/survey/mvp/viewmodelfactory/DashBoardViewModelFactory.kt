package com.saltnpixel.android.survey.mvp.viewmodelfactory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.saltnpixel.android.survey.mvp.presenters.DashBoardPresenter

class DashBoardViewModelFactory(private val repository: DashBoardPresenter) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashBoardPresenter::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return repository as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}