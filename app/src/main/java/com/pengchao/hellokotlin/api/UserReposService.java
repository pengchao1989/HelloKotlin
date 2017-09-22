package com.pengchao.hellokotlin.api;

import com.pengchao.hellokotlin.bean.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by pengchao on 2017/8/28.
 */

public interface UserReposService {
    @GET("v5/users/{username}/repos")
    Call<List<Repo>> listRepos(@Path("username") String user);
}
