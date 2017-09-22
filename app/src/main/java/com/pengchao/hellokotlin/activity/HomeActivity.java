package com.pengchao.hellokotlin.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.pengchao.hellokotlin.R;
import com.pengchao.hellokotlin.api.UserReposService;
import com.pengchao.hellokotlin.bean.Repo;
import com.pengchao.hellokotlin.http.RetrofitManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by pengchao on 2017/8/17.
 */
public class HomeActivity extends AppCompatActivity{

    private static final String TAG = "HomeActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestRepoList();

            }
        });

        int id = getIntent().getIntExtra("id", 0);
        String name = getIntent().getStringExtra("name");

        TextView textView = findViewById(R.id.tv_test);
        textView.setText("id=" + id + " name=" + name);
    }

    private void requestRepoList() {
        UserReposService userReposService = RetrofitManager.getInstance().create(UserReposService.class);
        Call<List<Repo>> call = userReposService.listRepos("didispace");
        if (call != null){

            call.enqueue(new Callback<List<Repo>>() {
                @Override
                public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                    for (Repo repo : response.body()){
                        Log.d(TAG, repo.getFull_name());
                    }
                }

                @Override
                public void onFailure(Call<List<Repo>> call, Throwable t) {

                }
            });
        }
    }

}
