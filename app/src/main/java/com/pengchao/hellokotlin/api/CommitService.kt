package com.pengchao.hellokotlin.api

import com.pengchao.hellokotlin.bean.CommitInfo
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by pengchao on 2017/9/4.
 */
interface CommitService {
    @GET("v5/repos/{owner}/{repo}/commits")
    fun listRepos(@Path("owner") owner: String,@Path("repo") repo: String): retrofit2.Call<List<CommitInfo>>
}
