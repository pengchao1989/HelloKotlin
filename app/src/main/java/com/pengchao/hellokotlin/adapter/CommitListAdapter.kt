package com.pengchao.hellokotlin.adapter

import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.pengchao.hellokotlin.bean.CommitInfo
import org.jetbrains.anko.*
import android.widget.LinearLayout.HORIZONTAL
import java.util.ArrayList

/**
 * Created by pengchao on 2017/9/5.
 */
class CommitListAdapter : BaseAdapter() {

    private var commitInfoList : MutableList<CommitInfo> ? = ArrayList()

    override fun getView(position: Int, p1: View?, parent : ViewGroup?): View {

        return with(parent!!.context) {
            //清单表元素布局
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(10)
                orientation = HORIZONTAL
                textView {
                    text=""+ commitInfoList!![position].commit.message
                    textSize = 16f
                    typeface = Typeface.MONOSPACE
                    padding =dip(5)
                }
            }
        }
    }

    internal fun setDatas(commitInfos: List<CommitInfo>) {
        commitInfoList!!.clear()
        commitInfoList!!.addAll(commitInfos)
        this.notifyDataSetChanged()
    }


    override fun getItem(position: Int): Any {
        return commitInfoList!![position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return commitInfoList!!.size
    }
}
