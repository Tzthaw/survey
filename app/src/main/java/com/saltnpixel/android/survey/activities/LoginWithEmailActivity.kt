package com.saltnpixel.android.survey.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.mmgoogleexpert.ptut.shared.ui.BaseActivity
import com.saltnpixel.android.shared.data.EmptyError
import com.saltnpixel.android.shared.data.FailError
import com.saltnpixel.android.shared.data.ResultError
import com.saltnpixel.android.shared.data.ServerError
import com.saltnpixel.android.survey.R
import com.saltnpixel.android.survey.mvp.Injection
import com.saltnpixel.android.survey.mvp.presenters.LoginPresenter
import com.saltnpixel.android.survey.mvp.views.LoginView
import com.saltnpixel.android.survey.utils.*
import kotlinx.android.synthetic.main.activity_login_with_email.*

class LoginWithEmailActivity : BaseActivity(), LoginView {

    private val loginViewModel by lazyAndroid {
        ViewModelProviders.of(this, Injection.provideViewModelFactoryLogin(LoginPresenter()))
            .get(LoginPresenter::class.java)
    }
    private val prefHelper by lazyAndroid { PrefHelper(this) }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LoginWithEmailActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_with_email)
        loginViewModel.initPresenter(this)
        btn_email_login_page_login.setOnClickListener {
            setUpUserLogin()
        }
        loginViewModel.loginResultLD?.observe(this, Observer {
            if (it?.accessToken.isNullOrEmpty()) {
                AlertDialogHelper.displayAlert(this, "Cannot access Token")
            } else {
                prefHelper.userToken = it?.accessToken!!
                startActivity(DashBoardActivity.newIntent(this))
                //Toast.makeText(this,"Cannot access Token",Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setUpUserLogin() {
        val email = emailEdit.text.toString()
        val password = passwordEdit.text.toString()
        loginViewModel.onNotifyCheckLogin(email, password)
        loginViewModel.errorLD.observe(this, this)

    }

    override fun onChanged(error: ResultError?) {
        error.let {
            when (it) {
                is EmptyError -> {
                    AlertDialogHelper.displayAlert(this, EMPTY_ERROR)
                }
                is FailError -> {
                    AlertDialogHelper.displayAlert(this, FAILURE_ERROR)
                }
                is ServerError -> {
                    AlertDialogHelper.displayAlert(this, SERVER_ERROR)
                }
            }
        }
    }
}