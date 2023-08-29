package com.timmymike.sample

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.timmymike.logtool.loge
import com.timmymike.viewtool.*

class AnimActivity : AppCompatActivity() {

    private val btnTop by lazy { findViewById<Button>(R.id.btn_top) }
    private val ivTop by lazy { findViewById<ImageView>(R.id.iv_top) }

    private val btnBtom by lazy { findViewById<Button>(R.id.btn_bottom) }
    private val ivBtom by lazy { findViewById<ImageView>(R.id.iv_bottom) }

    private val btnLeft by lazy { findViewById<Button>(R.id.btn_left) }
    private val ivLeft by lazy { findViewById<ImageView>(R.id.iv_left) }

    private val btnRight by lazy { findViewById<Button>(R.id.btn_right) }
    private val ivRight by lazy { findViewById<ImageView>(R.id.iv_right) }


    private val btnFade by lazy { findViewById<Button>(R.id.btn_fade) }
    private val ivFade by lazy { findViewById<ImageView>(R.id.iv_fade) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anim)

        sampleTopHide()

        sampleBottomHide()

        sampleLeftHide()

        sampleRightHide()

        sampleFade()


    }


    // 向上隱藏 Sample
    private fun sampleTopHide() {
        btnTop.run {
            clickWithTrigger {

                loge("btnTop 觸發點擊！")
                isSelected = !isSelected

                if (!isSelected) {
                    text = "向上隱藏"
                    ivTop.anim2BottomShow()
                } else {
                    text = "向下出現"
                    ivTop.anim2TopHide()
                }
            }
        }


    }

    // 向下隱藏 Sample
    private fun sampleBottomHide() {
        btnBtom.run {
            clickWithTrigger {

                loge("btnBtom 觸發點擊！")
                isSelected = !isSelected

                if (!isSelected) {
                    text = "向下隱藏"
                    ivBtom.anim2TopShow()
                } else {
                    text = "向上出現"
                    ivBtom.anim2BottomHide()
                }
            }
        }
    }

    // 向左隱藏 Sample
    private fun sampleLeftHide() {
        btnLeft.run {
            clickWithTrigger {
                isSelected = !isSelected
                loge("btnLeft 觸發點擊！")
                if (!isSelected) {
                    text = "向左隱藏"
                    ivLeft.anim2RightShow()
                } else {
                    text = "向右出現"
                    ivLeft.anim2LeftHide()
                }
            }

        }
    }

    // 向右隱藏 Sample
    private fun sampleRightHide() {
        btnRight.run {
            clickWithTrigger {
                isSelected = !isSelected
                loge("btnRight 觸發點擊！")
                if (!isSelected) {
                    text = "向右隱藏"
                    ivRight.anim2LeftShow()
                } else {
                    text = "向左出現"
                    ivRight.anim2RightHide()
                }
            }
        }
    }

    // 淡出淡入 Sample
    private fun sampleFade() {
        btnFade.run {
            clickWithTrigger {
                isSelected = !isSelected
                loge("btnFade 觸發點擊！")
                if (!isSelected) {
                    text = "淡出"
                    ivFade.fadeIn()
                } else {
                    text = "淡入"
                    ivFade.fadeOut()
                }
            }
        }
    }
}

