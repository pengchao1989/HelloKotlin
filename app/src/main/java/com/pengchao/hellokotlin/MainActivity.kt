package com.pengchao.hellokotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.pengchao.hellokotlin.activity.*
import com.pengchao.hellokotlin.api.UserReposService
import com.pengchao.hellokotlin.bean.Repo
import com.pengchao.hellokotlin.http.RetrofitManager
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), AnkoLogger {

    private val log = AnkoLogger("MainActivity")

    class MyActivityUI : AnkoComponent<MainActivity> {

        override fun createView(ui: AnkoContext<MainActivity>) = ui.apply {
            verticalLayout {

                padding = dip(20)

                editText {
                    hint = "Name"
                }

                val name = editText()

                //include xml layout
/*                include<LinearLayout>(R.layout.simple_input) {
                    text = "Hello, world!"
                }*/

                button("Insert Text") {
                    setOnClickListener({ name.append("Hello Anko \n") })
                }.lparams(width = wrapContent) {
                    horizontalMargin = dip(5)
                    topMargin = dip(10)
                }

                button("Show Toast") {

                    textSize = 20f
                    onClick {
                        toast("Hello, ${name.text}!")
                    }
                }

                //指定theme
                themedButton("Show Dialog", theme = R.style.redButton) {
                    onClick { ui.owner.showAlerts() }

                }

                button(R.string.start_activity) {
                    //Anko风格的startActivity
                    onClick {
                        ui.owner.startActivity()
                    }
                }

                button("Start ViewIdActivity") {
                    onClick { ui.owner.startActivity<TestViewIdActivity>() }
                }

                button("Start TestCoroutinesActivity") {
                    onClick { ui.owner.startActivity<TestCoroutinesActivity>() }
                }

                button("Start UserDetailActivity") {
                    //lambda示例(view-> ...) 只有一个抽象方法的都可以无缝的使用labada
                    setOnClickListener { view ->
                        if (view is Button) {
                            view.text = "Clicked"
                        }
                        startActivity<UserDetailActivity>()
                    }
                }

                val startActivityButton = button("Start TestListViewActivity")
                startActivityButton.setOnClickListener { ui.owner.startActivity<TestListViewActivity>() }

            }.applyRecursively { view ->  //会递归每一个view
                when (view) {
                    is EditText -> view.textSize = 20f  //20sp
                    is Button -> {

                    }

                }
            }
        }.view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = MyActivityUI().setContentView(this)


        var result = add(1, 2)

        printLog()

    }

    //基本函数示例
    fun add(a: Int, b: Int): Int {
        return a + b;
    }

    fun printLog(){
        info("London is the capital of Great Britain")
        debug("124") // .toString() method will be executed
        warn(null) // "null" will be printed
    }


    fun showToast() {
        longToast("Hi there!")
    }

    fun showAlerts() {
        alert("Hi, I'm Roy", "Have you tried turning it off and on again?") {
            yesButton { toast("Oh…") }
            noButton {}
        }.show()
    }

    fun startActivity() {
        //val intent = Intent(this, HomeActivity::class.java)
        //intent.putExtra("id", 5)
        //intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        //startActivity(intent)

        //startActivity<HomeActivity>("id" to 5, "name" to "123")

        intentFor<HomeActivity>("id" to 5, "name" to "123").singleTop()
    }

}
