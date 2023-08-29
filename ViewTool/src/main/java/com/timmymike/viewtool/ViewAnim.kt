package com.timmymike.viewtool

import android.animation.*
import android.view.View
import android.view.animation.*
import android.widget.ImageView
import androidx.core.view.isVisible

fun animateOpen(v: View, startHeight: Int, mHiddenViewMeasuredHeight: Int) {
    v.visibility = View.VISIBLE
    val animator = createDropAnimator(
        v, startHeight,
        mHiddenViewMeasuredHeight
    )
    animator.start()
}

fun animateOpen(v: View, mHiddenViewMeasuredHeight: Int) {
    v.visibility = View.VISIBLE
    val animator = createDropAnimator(
        v, 0,
        mHiddenViewMeasuredHeight
    )
    animator.start()
}

interface IAnimateEnd {
    fun onEnd()
}

fun animateOpen(v: View, mHiddenViewMeasuredHeight: Int, iAnimateEnd: IAnimateEnd) {
    val animator = createDropAnimator(
        v, 0,
        mHiddenViewMeasuredHeight
    )
    animator.start()
    animator.addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator) {
            super.onAnimationEnd(animation)
            iAnimateEnd.onEnd()
        }
    })
    v.visibility = View.VISIBLE
}

fun animateClose(view: View) {
    val origHeight = view.height
    val animator = createDropAnimator(view, origHeight, 0)
    animator.addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator) {
            view.visibility = View.GONE
        }
    })
    animator.start()
}

private fun createDropAnimator(v: View, start: Int, end: Int): ValueAnimator {
    val animator = ValueAnimator.ofInt(start, end)
    animator.addUpdateListener { arg0: ValueAnimator ->
        val value = arg0.animatedValue as Int
        val layoutParams = v.layoutParams
        layoutParams.height = value
        v.layoutParams = layoutParams
    }
    return animator
}

/**
 * 旋轉動畫
 */
fun View.animRotate(fromDegree: Float, toDegree: Float, duration: Long = 300L, needExecute: Boolean = true): Animation {
    //旋转动画效果   参数值 旋转的开始角度  旋转的结束角度  pivotX x轴伸缩值
    return RotateAnimation(
        fromDegree, toDegree,
        this.width / 2f, this.height / 2f
    ).apply {
        //该方法用于设置动画的持续时间，以毫秒为单位
        this.duration = duration
        //设置重复次数
        //动画终止时停留在最后一帧
        fillAfter = true
        //启动动画
        if (needExecute)
            startAnimation(this)
    }
}

/**
 * 縮放動畫
 * */
fun View.animScale(fromScale: Float, toScale: Float, duration: Long = 300L, needExecute: Boolean = true): Animation {
    return ScaleAnimation(
        fromScale, toScale,
        fromScale, toScale,
        Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
        Animation.RELATIVE_TO_SELF, 0.5f
    ).apply {
        fillAfter = true
        this.duration = duration

        if(needExecute)
            this@animScale.startAnimation(this)

    }
}

//向右出現
fun View.anim2RightShow(duration: Long = 300L, needExecute: Boolean = true, afterAction: ((View) -> Unit)? = null): Animation {
    return TranslateAnimation(
        Animation.RELATIVE_TO_SELF, -1f,
        Animation.RELATIVE_TO_SELF, 0f,
        Animation.RELATIVE_TO_SELF, 0f,
        Animation.RELATIVE_TO_SELF, 0f
    ).apply {
        fillAfter = true

        this.duration = duration
        setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                this@anim2RightShow.isVisible = true
                afterAction?.invoke(this@anim2RightShow)
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })

        if (needExecute)
            this@anim2RightShow.startAnimation(this)
    }
}

// 向左隱藏
fun View.anim2LeftHide(duration: Long = 300L, needExecute: Boolean = true, afterAction: ((View) -> Unit)? = null): Animation {
    return TranslateAnimation(
        Animation.RELATIVE_TO_SELF, 0f,
        Animation.RELATIVE_TO_SELF, -1f,
        Animation.RELATIVE_TO_SELF, 0f,
        Animation.RELATIVE_TO_SELF, 0f
    ).apply {
        fillAfter = true

        this.duration = duration
        setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                this@anim2LeftHide.isVisible = false
                afterAction?.invoke(this@anim2LeftHide)
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })

        if (needExecute)
            this@anim2LeftHide.startAnimation(this)

    }
}


//向右隱藏
fun View.anim2RightHide(duration: Long = 300L, needExecute: Boolean = true, afterAction: ((View) -> Unit)? = null): Animation {
    return TranslateAnimation(
        Animation.RELATIVE_TO_SELF, 0f,
        Animation.RELATIVE_TO_SELF, 1f,
        Animation.RELATIVE_TO_SELF, 0f,
        Animation.RELATIVE_TO_SELF, 0f
    ).apply {
        fillAfter = true

        this.duration = duration
        setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                this@anim2RightHide.isVisible = true
                afterAction?.invoke(this@anim2RightHide)
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })

        if (needExecute)
            this@anim2RightHide.startAnimation(this)
    }
}

// 向左出現
fun View.anim2LeftShow(duration: Long = 300L, needExecute: Boolean = true, afterAction: ((View) -> Unit)? = null): Animation {
    return TranslateAnimation(
        Animation.RELATIVE_TO_SELF, 1f,
        Animation.RELATIVE_TO_SELF, 0f,
        Animation.RELATIVE_TO_SELF, 0f,
        Animation.RELATIVE_TO_SELF, 0f
    ).apply {
        fillAfter = true

        this.duration = duration
        setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                this@anim2LeftShow.isVisible = false
                afterAction?.invoke(this@anim2LeftShow)
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })

        if (needExecute)
            this@anim2LeftShow.startAnimation(this)

    }
}

// 向上隱藏
fun View.anim2TopHide(duration: Long = 300L, needExecute: Boolean = true, afterAction: ((View) -> Unit)? = null): Animation {
    return TranslateAnimation(
        Animation.RELATIVE_TO_SELF, 0f,
        Animation.RELATIVE_TO_SELF, 0f,
        Animation.RELATIVE_TO_SELF, 0f,
        Animation.RELATIVE_TO_SELF, -1f
    ).apply {
        fillAfter = true

        this.duration = duration
        setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                this@anim2TopHide.isVisible = false
                afterAction?.invoke(this@anim2TopHide)
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })

        if (needExecute)
            this@anim2TopHide.startAnimation(this)
    }
}

// 向下出現
fun View.anim2BottomShow(duration: Long = 300L, needExecute: Boolean = true, afterAction: ((View) -> Unit)? = null): Animation {
    return TranslateAnimation(
        Animation.RELATIVE_TO_SELF, 0f,
        Animation.RELATIVE_TO_SELF, 0f,
        Animation.RELATIVE_TO_SELF, -1f,
        Animation.RELATIVE_TO_SELF, 0f
    ).apply {
        fillAfter = true

        this.duration = duration
        setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                this@anim2BottomShow.isVisible = true
                afterAction?.invoke(this@anim2BottomShow)
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
        if (needExecute)
            this@anim2BottomShow.startAnimation(this)
    }
}


// 向上出現
fun View.anim2TopShow(duration: Long = 300L, needExecute: Boolean = true, afterAction: ((View) -> Unit)? = null): Animation {
    return TranslateAnimation(
        Animation.RELATIVE_TO_SELF, 0f,
        Animation.RELATIVE_TO_SELF, 0f,
        Animation.RELATIVE_TO_SELF, 1f,
        Animation.RELATIVE_TO_SELF, 0f
    ).apply {
        repeatMode = Animation.REVERSE
        this.duration = duration
        setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                this@anim2TopShow.visibility = View.VISIBLE
                afterAction?.invoke(this@anim2TopShow)
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
        if (needExecute)
            this@anim2TopShow.startAnimation(this)
    }
}

// 向下隱藏
fun View.anim2BottomHide(duration: Long = 300L, needExecute: Boolean = true, afterAction: ((View) -> Unit)? = null): Animation {
    val mHiddenAction = TranslateAnimation(
        Animation.RELATIVE_TO_SELF, 0f,
        Animation.RELATIVE_TO_SELF, 0f,
        Animation.RELATIVE_TO_SELF, 0f,
        Animation.RELATIVE_TO_SELF, 1f
    )
    mHiddenAction.duration = duration
    mHiddenAction.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation) {}
        override fun onAnimationEnd(animation: Animation) {
            this@anim2BottomHide.isVisible = false
            afterAction?.invoke(this@anim2BottomHide)
        }

        override fun onAnimationRepeat(animation: Animation) {}
    })

    if (needExecute)
        this.startAnimation(mHiddenAction)

    return mHiddenAction
}

fun View.animAlphaChange(startAlpha: Float, endAlpha: Float, needExecute: Boolean = true, duration: Long = 300L, afterAction: ((View) -> Unit)? = null): Animation {
    val animation: Animation = AlphaAnimation(startAlpha, endAlpha).apply {
        setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                afterAction?.invoke(this@animAlphaChange)
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
        this.duration = duration
    }
    if (needExecute)
        this.startAnimation(animation)

    return animation
}

fun View.fadeIn(duration: Long = 300L, needExecute: Boolean = true) =
    this.animAlphaChange(0f, 1f, needExecute, duration) {
        it.isEnabled = true
        it.isVisible = true
    }

fun View.fadeOut(duration: Long = 300L, needExecute: Boolean = true) =
    this.animAlphaChange(1f, 0f, needExecute, duration) {
        it.isEnabled = false
        it.isVisible = false
    }

