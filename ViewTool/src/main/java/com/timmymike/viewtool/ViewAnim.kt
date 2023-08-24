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

fun showLeft2Right(view: View) {
    view.visibility = View.VISIBLE
    val mShowAction = TranslateAnimation(
        Animation.RELATIVE_TO_SELF, -1.0f,
        Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
        0.0f, Animation.RELATIVE_TO_SELF, 0.0f
    )
    mShowAction.repeatMode = Animation.REVERSE
    mShowAction.duration = 150
    view.startAnimation(mShowAction)
}

fun hideRight2Left(view: View) {
    view.visibility = View.GONE
    val mShowAction = TranslateAnimation(
        Animation.RELATIVE_TO_SELF, 0.0f,
        Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF,
        0.0f, Animation.RELATIVE_TO_SELF, 0.0f
    )
    mShowAction.repeatMode = Animation.REVERSE
    mShowAction.duration = 150
    view.startAnimation(mShowAction)
}

fun View.animBottom2TopShow() {
    val mShowAction = TranslateAnimation(
        Animation.RELATIVE_TO_SELF, 0.0f,
        Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
        1.0f, Animation.RELATIVE_TO_SELF, 0.0f
    )
    mShowAction.repeatMode = Animation.REVERSE
    mShowAction.duration = 300
    this.visibility = View.INVISIBLE
    mShowAction.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation) {}
        override fun onAnimationEnd(animation: Animation) {
            this@animBottom2TopShow.visibility = View.VISIBLE
        }

        override fun onAnimationRepeat(animation: Animation) {}
    })
    this.startAnimation(mShowAction)
}

fun View.animTop2BottomHidden() {
    if (this.visibility == View.GONE) return
    val mHiddenAction = TranslateAnimation(
        Animation.RELATIVE_TO_SELF,
        0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
        Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
        1.0f
    )
    mHiddenAction.duration = 300
    mHiddenAction.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation) {}
        override fun onAnimationEnd(animation: Animation) {
            this@animTop2BottomHidden.isVisible = false
        }
        override fun onAnimationRepeat(animation: Animation) {}
    })
    this.startAnimation(mHiddenAction)
}

fun View.animAlphaChange(startAlpha: Float, endAlpha: Float, duration: Long, afterAction: (() -> Unit)? = null) {
    val animation: Animation = AlphaAnimation(startAlpha, endAlpha).apply {
        setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                afterAction?.invoke()
            }
            override fun onAnimationRepeat(animation: Animation) {}
        })
        this.duration = duration
    }
    this.startAnimation(animation)
}

fun View.fadeIn(duration: Long = 1000L) {
    this.animAlphaChange(0f, 1f, duration) {
        this.isEnabled = true
        this.isVisible = true
    }
}

fun View.fadeOut(duration: Long = 1000L) {
    if (this.visibility != View.VISIBLE) return
    this.animAlphaChange(1f, 0f, duration) {
        this.isEnabled = false
        this.isVisible = false
    }
}

