package com.saltnpixel.android.survey.entities

import com.google.gson.annotations.SerializedName

data class DashBoardData(
	@SerializedName("user_name")
	val userName: String? = null,
	val profile: String? = null,
	@SerializedName("survey_limit")
	val surveyLimit: String? = null,
	@SerializedName("user_check")
	val userCheck: String? = null,
	val points: Int? = null
)
