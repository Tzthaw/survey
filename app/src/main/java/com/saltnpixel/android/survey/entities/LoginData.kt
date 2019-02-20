package com.saltnpixel.android.survey.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginData(
	@SerializedName("access_token")
	@Expose
	val accessToken: String? = null,
	@SerializedName("refresh_token")
	@Expose
	val refreshToken: String? = null,
	@SerializedName("token_type")
	@Expose
	val tokenType: String? = null,
	@SerializedName("expires_in")
	@Expose
	val expiresIn: Int? = null
)
