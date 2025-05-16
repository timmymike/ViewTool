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


/**
 * 設定 View 的連續點擊動作並執行，
 * 當指定次數(times)的點擊間隔低於 actionTime 時則觸發 action。
 *
 * @param times  點擊次數，預設為 3
 * @param interval 指定的點擊時間間隔（毫秒），預設為 1000L
 * @param action 點擊符合條件時要執行的動作，傳入點擊的 View 參數
 *
 * 使用範例：
 * 1.myView.clickWithTimes {
 *     // 當三次點擊間隔在1秒內，就會執行這裡的動作
 *     doAction()
 * }
 *
 * 2.myView.clickWithTimes(times = 4, actionTime = 1500L) {
 *     // 需要在 1500 毫秒內完成 4 次點擊才會觸發
 *     doAction()
 * }
 *
 */
fun View.clickWithTimes(times: Int = 3, interval: Long = 1000L, action: (View) -> Unit) {
    // 用於紀錄點擊的時間戳
    val clickTimeList: MutableList<Long> = mutableListOf()

    // 設定點擊事件監聽器
    this.setOnClickListener { view ->
        // 取得目前的時間戳
        val nowTime = System.currentTimeMillis()
        clickTimeList.add(nowTime)

        if (clickTimeList.size >= times) {
            // 判斷在指定次數內，最後一次與第一次的間隔是否小於 interval
            if (clickTimeList.last() - clickTimeList.first() < interval) {
                action(view)
            }
            // 點擊次數達到上限後清除紀錄以便下次重新計算
            clickTimeList.clear()
        }
    }
}