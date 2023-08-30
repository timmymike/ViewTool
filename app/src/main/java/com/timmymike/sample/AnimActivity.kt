package com.timmymike.sample

import android.graphics.Color
import android.os.Bundle
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

        singleExecute()

        compoundExecute()

        continuousExecute()

    }

    // 單一執行
    private fun singleExecute() {
        sampleTopHide()

        sampleBottomHide()

        sampleLeftHide()

        sampleRightHide()

        sampleFade()

        sampleRotate45()

        sampleRotate90()

        sampleScale()

        sampleColor()

        sampleBackgroundColor()
    }


    // 向上隱藏 Sample
    private fun sampleTopHide() = binding.run {
        btnTop.run {
            clickWithTrigger {

                loge("btnTop 觸發點擊！")
                isSelected = !isSelected

                text = if (!isSelected) {
                    ivTop.anim2BottomShow()
                    "向上隱藏"
                } else {
                    ivTop.anim2TopHide()
                    "向下出現"
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

                text = if (!isSelected) {
                    ivBottom.anim2TopShow()
                    "向下隱藏"
                } else {
                    ivBottom.anim2BottomHide()
                    "向上出現"
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
                text = if (!isSelected) {
                    ivLeft.anim2RightShow()
                    "向左隱藏"
                } else {
                    ivLeft.anim2LeftHide()
                    "向右出現"
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
                text = if (!isSelected) {
                    ivRight.anim2LeftShow()
                    "向右隱藏"
                } else {
                    ivRight.anim2RightHide()
                    "向左出現"
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
                    ivFade.fadeIn(1000L)
                } else {
                    text = "淡入"
                    ivFade.fadeOut(1000L)
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
            tvScale.text = "當前倍率：${nowScale.format("0.00")}"
            loge("縮放完畢的倍率=>${nowScale}")
        }

        // 縮小
        btnScaleDown.clickWithTrigger {
            ivScale.animScale(nowScale, nowScale * 0.5f)
            nowScale *= 0.5f
            tvScale.text = "當前倍率：${nowScale.format("0.00")}"
            loge("縮放完畢的倍率=>${nowScale}")
        }

    }

    // 顏色漸變 Sample
    private fun sampleColor() = binding.run {
        var nowColor = Color.BLACK
        btnColorBlue.clickWithTrigger {
            ivColor.animColor(nowColor, Color.BLUE, 1000L)
            nowColor = Color.BLUE
        }

        btnColorRed.clickWithTrigger {
            ivColor.animColor(nowColor, Color.RED, 1000L)
            nowColor = Color.RED
        }

        btnColorGreen.clickWithTrigger {
            ivColor.animColor(nowColor, Color.GREEN, 1000L)
            nowColor = Color.GREEN
        }

        btnColorBlack.clickWithTrigger {
            ivColor.animColor(nowColor, Color.BLACK, 1000L)
            nowColor = Color.BLACK
        }
    }

    // 背景顏色漸變 Sample
    private fun sampleBackgroundColor() = binding.run {
        var nowColor = Color.WHITE
        btnBgBlue.clickWithTrigger {
            ivBg.animBgColor(nowColor, Color.BLUE, 1000L)
            nowColor = Color.BLUE
        }

        btnBgRed.clickWithTrigger {
            ivBg.animBgColor(nowColor, Color.RED, 1000L)
            nowColor = Color.RED
        }

        btnBgGreen.clickWithTrigger {
            ivBg.animBgColor(nowColor, Color.GREEN, 1000L)
            nowColor = Color.GREEN
        }

        btnBgWhite.clickWithTrigger {
            ivBg.animBgColor(nowColor, Color.WHITE, 1000L)
            nowColor = Color.WHITE
        }
    }

    // 複合執行
    private fun compoundExecute() {
        compoundExecute1()

        compoundExecute2()

        compoundExecute3()
    }

    // 範例1：旋轉+變色
    private fun compoundExecute1() = binding.run {
        btnCompound1.run {
            clickWithTrigger {
                isSelected = isSelected.not()
                text = if (!isSelected) {
                    animateGroup(
                        ivCompound1.animRotate(90f, 0f, needExecute = false),
                        ivCompound1.animColor(Color.BLUE, Color.BLACK, needExecute = false)
                    )
                    "旋轉90+變藍"
                } else {
                    animateGroup(
                        ivCompound1.animRotate(0f, 90f, needExecute = false),
                        ivCompound1.animColor(Color.BLACK, Color.BLUE, needExecute = false)
                    )
                    "變回去"
                }
            }
        }
    }

    // 範例2：縮小+淡出
    private fun compoundExecute2() = binding.run {
        btnCompound2.run {
            clickWithTrigger {
                isSelected = isSelected.not()
                text = if (!isSelected) {
                    animateGroup(
                        ivCompound2.animScale(0.5f, 1f, 2000L, needExecute = false), // Duration可以不同
                        ivCompound2.animAlpha(0.5f, 1f, needExecute = false)
                    )
                    "縮小0.5+透明度0.5"
                } else {
                    animateGroup(
                        ivCompound2.animScale(1f, 0.5f, 2000L, needExecute = false),
                        ivCompound2.animAlpha(1f, 0.5f, needExecute = false)
                    )
                    "變回去"
                }
            }
        }
    }

    // 範例3：向右隱藏+旋轉
    private fun compoundExecute3() = binding.run {
        btnCompound3.run {
            clickWithTrigger {
                isSelected = isSelected.not()
                text = if (!isSelected) {
                    animateGroup(
                        ivCompound3.anim2LeftShow(needExecute = false),
                        ivCompound3.animRotate(270f, 90f,2000L, needExecute = false)
                    )
                    "向右隱藏+旋轉"
                } else {
                    animateGroup(
                        ivCompound3.anim2RightHide(2000L, needExecute = false),
                        ivCompound3.animRotate(90f, 270f, needExecute = false)
                    )
                    "變回去"
                }
            }
        }
    }


    private fun continuousExecute() {
    }



}

