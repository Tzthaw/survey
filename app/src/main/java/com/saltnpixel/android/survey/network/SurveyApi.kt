package com.saltnpixel.android.survey.network

import com.saltnpixel.android.survey.entities.DashBoardData
import com.saltnpixel.android.survey.entities.GetLoginResponse
import com.saltnpixel.android.survey.entities.LoginData
import io.reactivex.Observable
import retrofit2.http.*

interface SurveyApi {
    @FormUrlEncoded
    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("login")
    fun checkLoginSuccess(
         @Field("email")email:String,
         @Field("password")password:String
    ):Observable<GetLoginResponse>

    @POST("dashboard")
    fun getDashBoardData(@Header("Authorization") auth: String): Observable<DashBoardData>
}