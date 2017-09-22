package com.pengchao.hellokotlin.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pengchao.hellokotlin.R
import com.pengchao.hellokotlin.api.UserInfoService
import com.pengchao.hellokotlin.bean.UserInfo
import com.pengchao.hellokotlin.http.RetrofitManager
import kotlinx.android.synthetic.main.activity_coroutines.*
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.Ref
import org.jetbrains.anko.coroutines.experimental.asReference
import org.jetbrains.anko.coroutines.experimental.bg

/**
 * Created by p_cha on 2017/9/22.
 * 演示线程协作特性
 */
class TestCoroutinesActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)

        btnGetUserInfo.setOnClickListener {  loadAndShowData() }
    }

    fun loadAndShowData() {
        // Ref<T> uses the WeakReference under the hood
        val ref: Ref<TestCoroutinesActivity> = this.asReference()

        async(UI) {

            //You can easily execute your code on the background thread using bg():
            val data : Deferred<UserInfo?> = bg {getUserInfo()}

            // Use ref() instead of this@TestCoroutinesActivity
            ref().showUserInfo(data.await())
        }
    }

    fun getUserInfo(): UserInfo? {
        val userInfoService = RetrofitManager.getInstance().create(UserInfoService::class.java)
        val responseUserInfo = userInfoService.getUserInfo("didispace").execute();
        return responseUserInfo.body()
    }

    fun showUserInfo(userInfo: UserInfo?) {
        if (userInfo != null) {
            tvUserInfo.setText(userInfo.name)
        }
    }
}