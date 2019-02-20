package com.saltnpixel.android.survey.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.saltnpixel.android.survey.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPref = this.getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE
        )

        val defaultValue = resources.getString(R.string.saved_access_token_default_key)
        val accessToken = sharedPref.getString(getString(R.string.saved_access_token), defaultValue)
        println("Testing Default: $defaultValue Token: $accessToken ")

        if (accessToken != null && accessToken != "") {
//            val intent = Intent(this, DashBoardActivity::class.java)
//            startActivity(intent)
        } else {
            startActivity(MainActivity.newIntent(this))
        }
        finish()
    }
}
