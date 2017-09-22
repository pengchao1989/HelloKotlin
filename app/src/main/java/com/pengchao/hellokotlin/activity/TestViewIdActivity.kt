package com.pengchao.hellokotlin.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import com.pengchao.hellokotlin.R
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by pengchao on 2017/9/5.
 * 演示直接使用view的id
 */
class TestViewIdActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_1.onClick { textview_1.text = "Button1 Clicked!" }

        button_2.setOnClickListener(View.OnClickListener { textview_1.setText(R.string.app_name) })

        findViewById<Button>(R.id.button_3).setOnClickListener(View.OnClickListener {
            textview_1.setText("Button3 Clicked!")
        })
    }
}