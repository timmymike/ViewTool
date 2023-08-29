package com.timmymike.sample

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.timmymike.logtool.format
import com.timmymike.logtool.loge
import com.timmymike.sample.databinding.ActivityAnimBinding
import com.timmymike.viewtool.*

class AnimActivity : AppCompatActivity() {

    lateinit var binding: ActivityAnimBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityAnimBinding.inflate(layoutInflater).apply {
            binding = this
        }.root)

        sampleTopHide()

        sampleBottomHide()

        sampleLeftHide()

        sampleRightHide()

        sampleFade()

        sampleRotate45()

        sampleRotate90()

        sampleScale()

    }


    // 向上隱藏 Sample
    private fun sampleTopHide() = binding.run {
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
    private fun sampleBottomHide() = binding.run {
        btnBottom.run {
            clickWithTrigger {

                loge("btnBtom 觸發點擊！")
                isSelected = !isSelected

                if (!isSelected) {
                    text = "向下隱藏"
                    ivBottom.anim2TopShow()
                } else {
                    text = "向上出現"
                    ivBottom.anim2BottomHide()
                }
            }
        }
    }

    // 向左隱藏 Sample
    private fun sampleLeftHide() = binding.run {
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
    private fun sampleRightHide() = binding.run {
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
    private fun sampleFade() = binding.run {
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

    // 旋轉45度 Sample
    private fun sampleRotate45() = binding.run {
        var nowDegree = 0f
        btnRotate45.run {
            clickWithTrigger {
                loge("btnRotate45 觸發點擊！")
                ivRotate45.animRotate(nowDegree, nowDegree + 45f)
                nowDegree += 45f
            }
        }
    }

    // 旋轉90度 Sample
    private fun sampleRotate90() = binding.run {
        var nowDegree = 0f
        btnRotate90.run {
            clickWithTrigger {
                loge("btnRotate90 觸發點擊！")
                ivRotate90.animRotate(nowDegree, nowDegree + 90f)
                nowDegree += 90f
            }
        }
    }


    // 中心比例縮放 Sample
    private fun sampleScale() = binding.run {
        var nowScale = 1f

        // 放大
        btnScaleUp.clickWithTrigger {
            ivScale.animScale(nowScale, nowScale * 1.5f)
            nowScale *= 1.5f
            tvScale.text = "當前倍率：${nowScale.format("#.#")}"
            loge("縮放完畢的倍率=>${nowScale}")
        }

        // 縮小
        btnScaleDown.clickWithTrigger {
            ivScale.animScale(nowScale, nowScale * 0.5f)
            nowScale *= 0.5f
            tvScale.text = "當前倍率：${nowScale.format("#.#")}"
            loge("縮放完畢的倍率=>${nowScale}")
        }

    }
}

