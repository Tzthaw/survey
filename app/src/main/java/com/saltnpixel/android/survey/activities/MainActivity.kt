package com.saltnpixel.android.survey.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.mmgoogleexpert.ptut.shared.ui.BaseActivity
import com.saltnpixel.android.survey.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() , View.OnClickListener {
    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnLogin.setOnClickListener(this)
        btnRegister.setOnClickListener (this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnLogin->{
                startActivity(LoginIntroActivity.newIntent(this))
            }
            R.id.btnRegister->{

            }
        }
    }
}
