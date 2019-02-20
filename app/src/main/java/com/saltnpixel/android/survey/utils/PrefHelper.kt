package com.saltnpixel.android.survey.utils

import android.content.Context
import android.preference.PreferenceManager
import com.saltnpixel.android.survey.R

class PrefHelper(context: Context){
    companion object {
        private const val accessToken = "data.source.prefs.AccessToken"
    }
    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    var userToken = preferences.getString(accessToken, context.getString(R.string.saved_access_token))!!
        set(value) = preferences.edit().putString(accessToken,value).apply()
}