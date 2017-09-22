package com.pengchao.hellokotlin.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pengchao.hellokotlin.api.UserInfoService
import com.pengchao.hellokotlin.bean.UserInfo
import com.pengchao.hellokotlin.http.RetrofitManager
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by pengchao on 2017/9/5.
 */

class UserDetailActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UserDetailActivityUI().setContentView(this)
    }
}

class UserDetailActivityUI : AnkoComponent<UserDetailActivity>{
    override fun createView(ui: AnkoContext<UserDetailActivity>) = with(ui) {

        verticalLayout {
            val name = textView()
            val blog = textView()
            val bio = textView()
            button("get userInfo") {
                onClick {
                    val userInfoService = RetrofitManager.getInstance().create(UserInfoService::class.java)
                    val call = userInfoService.getUserInfo("didispace")
                    call.enqueue(object : Callback<UserInfo>{

                        override fun onResponse(call: Call<UserInfo>?, response: Response<UserInfo>?) {

                            val userInfo = response!!.body()
                            if(userInfo != null){
                                name.text = userInfo.name
                                blog.text = userInfo.blog
                                bio.text = userInfo.bio
                            }
                        }

                        override fun onFailure(call: Call<UserInfo>?, t: Throwable?) {

                        }
                    })
                }
            }
        }
    }
}


