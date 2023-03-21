package com.timmymike.viewtool

import android.content.Context
import android.graphics.BitmapFactory
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.children
import kotlin.math.roundToInt

/**由timmymike整理的畫面美觀工具*/

fun Float.spToPx(context: Context): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this, context.resources.displayMetrics).toInt()
}

fun Int.pxToSp(context: Context): Float {
    return this / context.resources.displayMetrics.scaledDensity
}

fun Float.dpToPx(context: Context): Int {
    return context.resources.displayMetrics.let { metric ->
        ((this * metric.widthPixels) / metric.density / 360f).toInt().let { realDpSize ->
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, realDpSize.toFloat(), context.resources.displayMetrics).toInt()
        }
    }
}

/**
 * 輸入DP單位數值 根據裝置動態 回傳像素:
 * @author Robert Chou didi31139@gmail.com
 * @param dpSize 整數 單位為dp
 * @date 2023/03/21
 * @return dp根據裝置動態計算 回傳pixel
 * @version
 */
fun Int.dpToPx(context: Context): Int {
    return context.resources.displayMetrics.let { metric ->
        ((this * metric.widthPixels).toFloat() / metric.density / 360f).toInt().let { realDpSize ->
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, realDpSize.toFloat(), context.resources.displayMetrics).roundToInt()
        }
    }
}


fun Int.pxToDp(context: Context): Float {
    return this / context.resources.displayMetrics.density
}

/**
 *
 * 取得螢幕寬度 單位為整數(pixel)
 * @author Robert Chou didi31139@gmail.com
 * @date 2023/03/21
 * @version
 */
fun Context.getScreenWidthPixels(): Int {
    return this.resources.displayMetrics.widthPixels
}

/**
 *
 * 取得螢幕高度 單位為整數(pixel)
 * @author Robert Chou didi31139@gmail.com
 * @date 2023/03/21
 * @version
 */
fun Context.getScreenHeightPixels(): Int {
    return this.resources.displayMetrics.heightPixels
}


/**
 * 設定 view的長寬 單位為畫素(pixel)
 * @param view
 * @param w
 * @param h
 * @author Wang / Robert
 * @date 2023/03/21
 * @version
 */
fun View.setViewSize(w: Int, h: Int) {
    try {
        this.layoutParams.width = w
        this.layoutParams.height = h
    } catch (e: Exception) {
        //如果prams不存在 則重新建立
        val params = ViewGroup.LayoutParams(w, h)
        params.width = w
        params.height = h
        this.layoutParams = params
    }
    this.requestLayout()
}

/**
 * 設定 view的長寬 單位為dp
 * @param view
 * @param w
 * @param h
 * @author Wang / Robert
 * @date 2023/03/21
 * @version
 */
fun View.setViewSizeByDpUnit(w: Int, h: Int) {
    setViewSize(w.dpToPx(this.context), h.dpToPx(this.context))
}

/**
 * 設定 view的長寬 單位為畫素(pixel) 自動高度
 * @param view
 * @param w
 * @param rid
 * @author Wang / Robert
 * @date 2023/03/21
 * @version
 */
fun View.setViewSizeByResWidth(w: Int, rid: Int) {
    val h = context.getDrawableWidth(rid, w)
    try {
        this.layoutParams.width = w
        this.layoutParams.height = h
    } catch (e: Exception) {
        //如果prams不存在 則重新建立
        val params = ViewGroup.LayoutParams(w, h)
        params.width = w
        params.height = h
        this.layoutParams = params
    }
}

/**
 * 設定 view的長寬 單位為畫素(pixel) 自動寬度
 * @param view
 * @param h
 * @param rid
 * @author Wang / Robert
 * @date 2023/03/21
 * @version
 */
fun View.setViewSizeByResHeight(h: Int, rid: Int) {
    val w = context.getDrawableWidth(rid, h)
    try {
        this.layoutParams.width = w
        this.layoutParams.height = h
    } catch (e: Exception) {
        //如果prams不存在 則重新建立
        val params = ViewGroup.LayoutParams(w, h)
        params.width = w
        params.height = h
        this.layoutParams = params
    }
}

/**
 *
 * 設定物件間距  單位為畫素(pixel)
 * 上層類別須為 RelativeLayout or LinearLayout
 * @author Wang / Robert Chou didi31139@gmail.com
 * @date 2021/03/21
 * @version
 */
fun View.setMarginByDpUnit(leftMargin: Int, topMargin: Int, rightMargin: Int, bottomMargin: Int) {
    val params = this.layoutParams
    if (params is ViewGroup.MarginLayoutParams) {
        params.setMargins(
            leftMargin.dpToPx(this.context),
            topMargin.dpToPx(this.context),
            rightMargin.dpToPx(this.context),
            bottomMargin.dpToPx(this.context)
        )
    }
    this.layoutParams = params
    this.requestLayout()
}

fun View.setPaddingByDpUnit(
    leftPadding: Int,
    topPadding: Int,
    rightPadding: Int,
    bottomPadding: Int
) {
    this.setPadding(
        leftPadding.dpToPx(this.context),
        topPadding.dpToPx(this.context),
        rightPadding.dpToPx(this.context),
        bottomPadding.dpToPx(this.context)
    )

    this.requestLayout()
}

fun View.getRealityWidth(): Int {
    this.measure(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
    return this.measuredWidth
}

fun View.getRealityHeight(): Int {
    this.measure(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
    return this.measuredHeight
}


fun View.setTextSize(sp: Int) {
    this.context.resources.displayMetrics.let { metric ->
        ((sp * metric.widthPixels).toFloat() / metric.density / 360f).toInt().let {
            (this as? TextView)?.setTextSize(TypedValue.COMPLEX_UNIT_SP, it.toFloat())
        }
    }
}

/**把xml中的文字，重新設定以xml中的文字縮放為適應畫面。*/
fun ViewGroup.resetLayoutTextSize() {
    val scale = this.resources.displayMetrics.scaledDensity
    this.children.forEach {
        (it as? TextView)?.setTextSize((it.textSize / scale + 0.5f).toInt())?.apply {
            return@forEach
        }
        (it as? ViewGroup)?.resetLayoutTextSize()
    }
}

fun Context.getDrawableHeight(id: Int, maxWidth: Int): Int {
    val drawable = this.getResourceDrawable(id)
    val w = drawable.intrinsicWidth.toFloat()
    val h = drawable.intrinsicHeight.toFloat()
    val scale = h / w
    val ansHeight = maxWidth * scale

    return ansHeight.toInt()
}

fun String.getUriImageHeight(maxWidth: Int): Int {
    val options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    BitmapFactory.decodeFile(this, options)

    val w = options.outWidth.toFloat()
    val h = options.outHeight.toFloat()
    val scale = h / w
    val ansHeight = maxWidth * scale

    return ansHeight.toInt()
}

fun Context.getDrawableWidth(id: Int, maxHeight: Int): Int {
    val drawable = this.getResourceDrawable(id)

    val w = drawable.intrinsicWidth.toFloat()
    val h = drawable.intrinsicHeight.toFloat()
    val scale = w / h
    val ansWidth = maxHeight * scale

    return ansWidth.toInt()
}

fun String.getUriImageWidth(context: Context, maxHeight: Int): Int {
    val options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    BitmapFactory.decodeFile(this, options)

    val w = options.outWidth.toFloat()
    val h = options.outHeight.toFloat()
    val scale = w / h
    val ansWidth = maxHeight * scale

    return ansWidth.toInt()
}
