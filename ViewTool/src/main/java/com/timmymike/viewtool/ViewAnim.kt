package com.timmymike.viewtool

import android.animation.*
import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible

/**
 * 複合型動畫呼叫方式(供動畫們一起執行)
 *
 * 使用範例：
 *  animateGroup(
 *      ivCompound2.animScale(1f, 0.5f, 2000L, needExecute = false),
 *      ivCompound2.animAlpha(1f, 0.5f, needExecute = false),
 *      ivCompound2.animRotate(90f, 0f, needExecute = false),
 *  )
 * */
fun animateGroup(vararg animator: Animator) {
    AnimatorSet().apply { playTogether(animator.toMutableList()) }.start()
}

/**
 * 連續型動畫呼叫方式(供動畫們接續執行)
 *
 * 使用範例：
 *  animateContinuous(
 *      ivContinuous3.animColor(Color.BLACK,Color.RED,1000L,needExecute = false),
 *      ivContinuous3.animRotate(0f,180f,1000L,needExecute = false),
 *      ivContinuous3.anim2BottomHide(needExecute = false)
 *  )
 * */
fun animateContinuous(vararg animator: Animator) {
    AnimatorSet().apply { playSequentially(animator.toMutableList()) }.start()
}

/**
 * 背景顏色漸變動畫
 * */
fun View.animBgColor(fromColor: Int, toColor: Int, duration: Long = 300L, needExecute: Boolean = true, afterAction: ((View) -> Unit)? = null): Animator {
    return ValueAnimator.ofObject(ArgbEvaluator(), fromColor, toColor).apply {
        this.duration = duration

        addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {
                afterAction?.invoke(this@animBgColor)
            }

            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })

        addUpdateListener { animator ->
            val animatedValue = animator.animatedValue as Int
            setBackgroundColor(animatedValue)
        }

        if (needExecute) {
            this.start()
        }
    }
}


/**
 * 顏色漸變動畫(限定ImageView)
 * */
fun ImageView.animColor(fromColor: Int, toColor: Int, duration: Long = 300L, needExecute: Boolean = true, afterAction: ((View) -> Unit)? = null): Animator {
    return ValueAnimator.ofObject(ArgbEvaluator(), fromColor, toColor).apply {
        this.duration = duration
        addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {
                afterAction?.invoke(this@animColor)
            }

            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })

        addUpdateListener { animator ->
            val animatedValue = animator.animatedValue as Int
            setColorFilter(animatedValue)
        }

        if (needExecute) {
            this.start()
        }
    }
}


/**
 * 中心縮放動畫
 */
fun View.animScale(fromScale: Float, toScale: Float, duration: Long = 300L, needExecute: Boolean = true, afterAction: ((View) -> Unit)? = null): Animator {
    val scaleXHolder = PropertyValuesHolder.ofFloat(View.SCALE_X, fromScale, toScale)
    val scaleYHolder = PropertyValuesHolder.ofFloat(View.SCALE_Y, fromScale, toScale)

    return ObjectAnimator.ofPropertyValuesHolder(this, scaleXHolder, scaleYHolder).apply {
        this.duration = duration
        addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {
                afterAction?.invoke(this@animScale)
            }

            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })

        if (needExecute) start()
    }
}

/**
 * 旋轉動畫
 */
fun View.animRotate(fromDegree: Float, toDegree: Float, duration: Long = 300L, needExecute: Boolean = true, afterAction: ((View) -> Unit)? = null): Animator {
    return ObjectAnimator.ofFloat(this, View.ROTATION, fromDegree, toDegree).apply {
        this.duration = duration
        addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {
                afterAction?.invoke(this@animRotate)
            }

            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })
        if (needExecute) start()

    }
}

/**
 * 向右出現
 * */
fun View.anim2RightShow(duration: Long = 300L, needExecute: Boolean = true, afterAction: ((View) -> Unit)? = null): Animator {

    return ObjectAnimator.ofFloat(this, View.TRANSLATION_X, -width.toFloat(), 0f).apply {
        isVisible = true // 要先設定為可見，才可以有「滑出的效果」

        this.duration = duration

        addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {
                afterAction?.invoke(this@anim2RightShow)
            }

            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })

        if (needExecute) start()
    }
}

/**
 * 向左隱藏
 * */
fun View.anim2LeftHide(duration: Long = 300L, needExecute: Boolean = true, afterAction: ((View) -> Unit)? = null): Animator {
    return ObjectAnimator.ofFloat(this, View.TRANSLATION_X, 0f, -width.toFloat()).apply {
        this.duration = duration
        addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {
                afterAction?.invoke(this@anim2LeftHide)
            }

            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })
        if (needExecute) start()
    }
}

/**
 * 向右隱藏
 * */
fun View.anim2RightHide(duration: Long = 300L, needExecute: Boolean = true, afterAction: ((View) -> Unit)? = null): Animator {
    return ObjectAnimator.ofFloat(this, View.TRANSLATION_X, 0f, width.toFloat()).apply {
        this.duration = duration
        addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {
                afterAction?.invoke(this@anim2RightHide)
            }

            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })
        if (needExecute) start()
    }
}

/**
 * 向左出现
 * */
fun View.anim2LeftShow(duration: Long = 300L, needExecute: Boolean = true, afterAction: ((View) -> Unit)? = null): Animator {
    return ObjectAnimator.ofFloat(this, View.TRANSLATION_X, width.toFloat(), 0f).apply {
        isVisible = true // 要先設定為可見，才可以有「滑出的效果」
        this.duration = duration
        addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {
                afterAction?.invoke(this@anim2LeftShow)
            }

            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })
        if (needExecute) start()
    }
}

/**
 * 向上隱藏
 * */
fun View.anim2TopHide(duration: Long = 300L, needExecute: Boolean = true, afterAction: ((View) -> Unit)? = null): Animator {
    return ObjectAnimator.ofFloat(this, View.TRANSLATION_Y, 0f, -height.toFloat()).apply {
        this.duration = duration
        addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {
                afterAction?.invoke(this@anim2TopHide)
            }

            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })
        if (needExecute) start()
    }
}

/**
 * 向下出現
 * */
fun View.anim2BottomShow(duration: Long = 300L, needExecute: Boolean = true, afterAction: ((View) -> Unit)? = null): Animator {
    return ObjectAnimator.ofFloat(this, View.TRANSLATION_Y, -height.toFloat(), 0f).apply {
        isVisible = true // 要先設定為可見，才可以有「滑出的效果」
        this.duration = duration
        addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {
                afterAction?.invoke(this@anim2BottomShow)
            }

            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })
        if (needExecute) start()
    }
}

/**
 * 向上出現
 * */
fun View.anim2TopShow(duration: Long = 300L, needExecute: Boolean = true, afterAction: ((View) -> Unit)? = null): Animator {
    return ObjectAnimator.ofFloat(this, View.TRANSLATION_Y, height.toFloat(), 0f).apply {
        isVisible = true  // 要先設定為可見，才可以有「滑出的效果」
        this.duration = duration
        addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {
                afterAction?.invoke(this@anim2TopShow)
            }

            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })
        if (needExecute) start()
    }
}

/**
 * 向下隱藏
 * */
fun View.anim2BottomHide(duration: Long = 300L, needExecute: Boolean = true, afterAction: ((View) -> Unit)? = null): Animator {
    return ObjectAnimator.ofFloat(this, View.TRANSLATION_Y, 0f, height.toFloat()).apply {
        this.duration = duration
        addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {
                afterAction?.invoke(this@anim2BottomHide)
            }

            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })
        if (needExecute) start()
    }
}


/**
 * 透明度效果動畫
 * */
fun View.animAlpha(startAlpha: Float, endAlpha: Float, needExecute: Boolean = true, duration: Long = 300L, afterAction: ((View) -> Unit)? = null): Animator {
    return ObjectAnimator.ofFloat(this, View.ALPHA, startAlpha, endAlpha).apply {
        this.duration = duration

        addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {
                afterAction?.invoke(this@animAlpha)
            }

            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })

        if (needExecute) start()
    }
}

/**
 * 封裝為全部出現的透明度效果
 * */
fun View.fadeIn(duration: Long = 300L, needExecute: Boolean = true, afterAction: ((View) -> Unit)? = null) =
    this.animAlpha(0f, 1f, needExecute, duration) {
        it.isEnabled = true
        afterAction?.invoke(this@fadeIn)
    }

/**
 * 封裝為全部消失的透明度效果
 * */
fun View.fadeOut(duration: Long = 300L, needExecute: Boolean = true, afterAction: ((View) -> Unit)? = null) =
    this.animAlpha(1f, 0f, needExecute, duration) {
        it.isEnabled = false
        afterAction?.invoke(this@fadeOut)
    }

