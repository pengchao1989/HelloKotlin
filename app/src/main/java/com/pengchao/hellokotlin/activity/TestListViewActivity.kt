package com.pengchao.hellokotlin.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.pengchao.hellokotlin.adapter.CommitListAdapter
import com.pengchao.hellokotlin.api.CommitService
import com.pengchao.hellokotlin.bean.CommitInfo
import com.pengchao.hellokotlin.http.RetrofitManager
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onItemClick
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by pengchao on 2017/9/5.
 */
class TestListViewActivity : AppCompatActivity() {

    private val TAG = "TestListViewActivity"

    class TestListViewActivityUI(private val commitAdapter: CommitListAdapter) : AnkoComponent<TestListViewActivity> {

        override fun createView(ui: AnkoContext<TestListViewActivity>) = ui.apply {
            verticalLayout {

                padding = dip(16)

                val list = listView() {
                    adapter = commitAdapter
                }
            }
        }.view
    }

    val commitAdapter: CommitListAdapter = CommitListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TestListViewActivityUI(commitAdapter).setContentView(this)

        requestCommitList()
    }

    //请commit列表
    private fun requestCommitList() {

        val commitService = RetrofitManager.getInstance().create(CommitService::class.java)
        val call = commitService.listRepos("didispace", "SpringBoot-Learning")
        call?.enqueue(object : Callback<List<CommitInfo>> {
            override fun onResponse(call: Call<List<CommitInfo>>, response: Response<List<CommitInfo>>) {

                for (commitInfo in response.body()!!) {
                    Log.d(TAG, commitInfo.commit.message)
                }

                commitAdapter.setDatas(response.body()!!)
            }

            override fun onFailure(call: Call<List<CommitInfo>>, t: Throwable) {

            }
        })
    }
}