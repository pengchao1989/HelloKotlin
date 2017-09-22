package com.pengchao.hellokotlin.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.pengchao.hellokotlin.bean.CommitInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by p_cha on 2017/9/5.
 */

public class CommitListAdapter2 extends BaseAdapter {

    List<CommitInfo> mCommitInfoList = new ArrayList<>();

    void setDatas(List<CommitInfo> commitInfos){
        mCommitInfoList.clear();
        mCommitInfoList.addAll(commitInfos);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mCommitInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return mCommitInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
