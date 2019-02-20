package com.saltnpixel.android.survey.mvp.viewmodelfactory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.saltnpixel.android.survey.mvp.presenters.LoginPresenter

class LoginViewModelFactory(private val repository: LoginPresenter) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginPresenter::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return repository as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}