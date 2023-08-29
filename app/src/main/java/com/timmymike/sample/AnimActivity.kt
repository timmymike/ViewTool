package com.timmymike.sample

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.timmymike.logtool.loge
import com.timmymike.viewtool.*

class AnimActivity : AppCompatActivity() {

    private val btnTopBtom: Button by lazy { findViewById<Button>(R.id.btn_top_bottom) }
    private val ivTopBtom: ImageView by lazy { findViewById<ImageView>(R.id.iv_top_bottom) }

    val btnRightLeft by lazy { findViewById<Button>(R.id.btn_right_left) }
    val ivRightLeft by lazy { findViewById<ImageView>(R.id.iv_right_left) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anim)

        sampleBottomHide()

        sampleLeftHide()


    }


    private fun sampleBottomHide() {
        btnTopBtom.run {
            clickWithTrigger {

                loge("btnTopBtom 觸發點擊！")
                isSelected = !isSelected

                if (!isSelected) {
                    text = "向下隱藏"
                    ivTopBtom.animBottom2TopShow()
                } else {
                    text = "向上出現"
                    ivTopBtom.animTop2BottomHide()
                }
            }
        }
    }

    private fun sampleLeftHide() {
        btnRightLeft.run {
            clickWithTrigger {
                isSelected = !isSelected
                loge("btnRightLeft 觸發點擊！")
                if (!isSelected) {
                    text = "向左隱藏"
                    ivRightLeft.animLeft2RightShow()
                } else {
                    text = "向右出現"
                    ivRightLeft.animRight2LeftHide()
                }
            }

        }
    }

}

