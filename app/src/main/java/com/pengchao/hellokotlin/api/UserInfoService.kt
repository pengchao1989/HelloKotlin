package com.pengchao.hellokotlin.api

import com.pengchao.hellokotlin.bean.UserInfo
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by pengchao on 2017/9/5.
 */
interface UserInfoService{
    @GET("v5/users/{username}")
    fun getUserInfo(@Path("username") username: String) : retrofit2.Call<UserInfo>
}