package com.saltnpixel.android.survey.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.facebook.login.widget.LoginButton
import com.mmgoogleexpert.ptut.shared.ui.BaseActivity
import com.saltnpixel.android.survey.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginIntroActivity :BaseActivity(),View.OnClickListener{

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LoginIntroActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btn_loginpage_login.setOnClickListener(this)
        btn_login_fblogin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_loginpage_login->{
                startActivity(LoginWithEmailActivity.newIntent(this))
            }
            R.id.btn_login_fblogin->{
               fb_login_button_login_intro.performClick()
            }
        }
    }
}