package com.timmymike.viewtool

import android.animation.*
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.view.animation.TranslateAnimation
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

fun rotateImage(imageView: ImageView, fromDegrees: Float, toDegrees: Float, imageResource: Int) {
    val pivotX = imageView.width / 2f
    val pivotY = imageView.height / 2f
    imageView.setImageResource(imageResource)
    //旋转动画效果   参数值 旋转的开始角度  旋转的结束角度  pivotX x轴伸缩值
    val animation = RotateAnimation(
        fromDegrees, toDegrees,
        pivotX, pivotY
    )
    //该方法用于设置动画的持续时间，以毫秒为单位
    animation.duration = 300
    //设置重复次数
    //animation.setRepeatCount(int repeatCount);
    //动画终止时停留在最后一帧
    animation.fillAfter = true
    //启动动画
    imageView.startAnimation(animation)
}

/**
 * @param arrow
 * @param flag  1：朝上
 */
fun rotateArrow(arrow: ImageView, flag: Boolean) {
    val pivotX = arrow.width / 2f
    val pivotY = arrow.height / 2f
    val fromDegrees: Float
    val toDegrees: Float
    // flag为true则向上
    if (flag) {
        fromDegrees = 180f
        toDegrees = 360f
    } else {
        fromDegrees = 0f
        toDegrees = 180f
    }
    //旋转动画效果   参数值 旋转的开始角度  旋转的结束角度  pivotX x轴伸缩值
    val animation = RotateAnimation(
        fromDegrees, toDegrees,
        pivotX, pivotY
    )
    //该方法用于设置动画的持续时间，以毫秒为单位
    animation.duration = 300
    //设置重复次数
    //animation.setRepeatCount(int repeatCount);
    //动画终止时停留在最后一帧
    animation.fillAfter = true
    //启动动画
    arrow.startAnimation(animation)
}

fun animImageView(mImageView: ImageView?) {
    //图片动画
    val toScale = 0.2f
    val propertyValuesHolderX = PropertyValuesHolder.ofFloat("scaleX", 1.0f, toScale, 1.0f)
    val propertyValuesHolderY = PropertyValuesHolder.ofFloat("scaleY", 1.0f, toScale, 1.0f)
    val objectAnimator = ObjectAnimator.ofPropertyValuesHolder(
        mImageView,
        propertyValuesHolderX, propertyValuesHolderY
    )
    objectAnimator.start()
}

fun View.animLeft2RightShow(duration: Long = 300L, needExecute: Boolean = true, afterAction: ((View) -> Unit)? = null):Animation {
    val mShowAction = TranslateAnimation(
        Animation.RELATIVE_TO_SELF, -1f,
        Animation.RELATIVE_TO_SELF, 0.0f,
        Animation.RELATIVE_TO_SELF, 0.0f,
        Animation.RELATIVE_TO_SELF, 0.0f
    ).apply{
        fillAfter = true
    }
//    mShowAction.repeatMode = Animation.REVERSE
    mShowAction.duration = duration
    mShowAction.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation) {}
        override fun onAnimationEnd(animation: Animation) {
            this@animLeft2RightShow.isVisible = true
//            this@animLeft2RightShow.pivotX = 0f
            afterAction?.invoke(this@animLeft2RightShow)
        }

        override fun onAnimationRepeat(animation: Animation) {}
    })

    if (needExecute)
        this.startAnimation(mShowAction)

    return mShowAction
}

fun View.animRight2LeftHide(duration: Long = 300L, needExecute: Boolean = true, afterAction: ((View) -> Unit)? = null):Animation {
    val mHideAction = TranslateAnimation(
        Animation.RELATIVE_TO_SELF, 0f,
        Animation.RELATIVE_TO_SELF, -1f,
        Animation.RELATIVE_TO_SELF, 0f,
        Animation.RELATIVE_TO_SELF, 0f
    ).apply{
        fillAfter = true
    }
    mHideAction.duration = duration
    mHideAction.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation) {}
        override fun onAnimationEnd(animation: Animation) {
            this@animRight2LeftHide.isVisible = false
//            this@animRight2LeftHide.pivotX = -1f
            afterAction?.invoke(this@animRight2LeftHide)
        }

        override fun onAnimationRepeat(animation: Animation) {}
    })

    if (needExecute)
        this.startAnimation(mHideAction)

    return mHideAction
}

fun View.animBottom2TopShow(duration: Long = 300L, needExecute: Boolean = true, afterAction: ((View) -> Unit)? = null) :Animation{
    val mShowAction = TranslateAnimation(
        Animation.RELATIVE_TO_SELF, 0.0f,
        Animation.RELATIVE_TO_SELF, 0.0f,
        Animation.RELATIVE_TO_SELF, 1.0f,
        Animation.RELATIVE_TO_SELF, 0.0f
    )
    mShowAction.repeatMode = Animation.REVERSE
    mShowAction.duration = duration
    this.visibility = View.INVISIBLE
    mShowAction.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation) {}
        override fun onAnimationEnd(animation: Animation) {
            this@animBottom2TopShow.visibility = View.VISIBLE
            afterAction?.invoke(this@animBottom2TopShow)
        }

        override fun onAnimationRepeat(animation: Animation) {}
    })

    if (needExecute)
        this.startAnimation(mShowAction)

    return mShowAction
}

fun View.animTop2BottomHide(duration: Long = 300L, needExecute: Boolean = true, afterAction: ((View) -> Unit)? = null): Animation {
    val mHiddenAction = TranslateAnimation(
        Animation.RELATIVE_TO_SELF, 0.0f,
        Animation.RELATIVE_TO_SELF, 0.0f,
        Animation.RELATIVE_TO_SELF, 0.0f,
        Animation.RELATIVE_TO_SELF, 1.0f
    )
    mHiddenAction.duration = duration
    mHiddenAction.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation) {}
        override fun onAnimationEnd(animation: Animation) {
            this@animTop2BottomHide.isVisible = false
            afterAction?.invoke(this@animTop2BottomHide)
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

