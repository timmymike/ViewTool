package com.timmymike.viewtool

import android.os.Handler
import android.os.Looper
import android.view.View
import kotlinx.coroutines.*

/**由timmymike整理的畫面美觀工具*/

object ClickOption {
    var CLICK_DELAY = 700L
}

//蝦米註：有跳頁或提示對話框動作才要使用，避免跳頁跳兩次或對話框出現兩個。
var clickEvent: ((View?) -> Unit)? = null

fun <T : View> T.clickWithTrigger(block: (View?) -> Unit) {
    setOnClickListener { view ->
        if (clickEvent == null) {
            clickEvent = block
            block.invoke(view)
            Handler(Looper.myLooper() ?: return@setOnClickListener).postDelayed({
                clickEvent = null
            }, ClickOption.CLICK_DELAY)
        }
    }
}

//蝦米註：同一頁面的事件(和原本的setOnClickListener一模一樣)
fun <T : View> T.click(block: (T?) -> Unit) = setOnClickListener {
    block.invoke(this)
}
