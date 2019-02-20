package com.saltnpixel.android.survey.entities

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken

data class GetLoginResponse(
	@SerializedName("login_data")
	@Expose
	val loginData: JsonElement?=null,
	@SerializedName("user_check")
	@Expose
	val userCheck: String? = null
//	@SerializedName("error")
//	val error:String?=null,
//	@SerializedName("message")
//	val message:String?=null
)
{
    fun getLoginData():LoginData{
		var resultList: MutableList<LoginData> = ArrayList() // Initializing here just to cover the null pointer exceptionuuu
		val gson = Gson()
		if (loginData is JsonObject) {
			resultList.add(gson.fromJson<LoginData>(loginData, LoginData::class.java))
		} else if (loginData is JsonArray) {
			val founderListType = object : TypeToken<ArrayList<LoginData>>() {
			}.type
			resultList = gson.fromJson<MutableList<LoginData>>(loginData, founderListType)
		}
		return resultList[0]
    }
}

