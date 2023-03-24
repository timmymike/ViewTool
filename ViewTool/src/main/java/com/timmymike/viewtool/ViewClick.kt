package com.timmymike.viewtool

import android.os.Handler
import android.os.Looper
import android.view.View

/**由timmymike整理的畫面美觀工具*/

object ClickOption {
    // 每點擊兩下，第二下要延遲多久以後才有效。
    var CLICK_DELAY = 700L
}

    private var clickEvent: ((View?) -> Unit)? = null

    /**
     * 有跳頁或提示對話框動作才要使用，避免跳頁跳兩次或對話框出現兩個。
     * @param block 要執行的內容
     * @author Timmy.Hsieh
     * @date formatted 2023/03/21
     */
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

    /**
     * 同一頁面的事件(和原本的setOnClickListener一模一樣)
     * @param block 要執行的內容
     * @author Timmy.Hsieh
     * @date formatted 2023/03/21
     */
    fun <T : View> T.click(block: (T?) -> Unit) = setOnClickListener {
        block.invoke(this)
    }