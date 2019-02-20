package com.mmgoogleexpert.ptut.shared.ui

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import com.saltnpixel.android.shared.data.ResultError
import android.support.v7.app.AppCompatActivity

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity(), Observer<ResultError> {
    override fun onChanged(error: ResultError?) {

    }
}