package com.timmymike.viewtool

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.TextView
import androidx.core.view.children
import kotlin.math.roundToInt

/**由timmymike整理的畫面美觀工具*/

/**
 * SP單位數值 根據裝置動態 回傳依照畫面密度修改的寬度。
 * @editor Timmy.Hsieh
 * @date formatted 2023/03/21
 * @return dp根據裝置動態計算 回傳計算完畢的SP。
 */
val Number.spToAutoWidth
    get() =
        resourcesDisplayMetrics.let { metric ->
            ((this.toFloat() * metric.widthPixels) / metric.scaledDensity / 360f)
        }

/**
 * SP單位數值 根據裝置動態 回傳像素:
 * @editor Timmy.Hsieh
 * @date formatted 2023/03/21
 * @return dp根據裝置動態計算 回傳pixel
 * 使用範例：
 * 100.spToPx
 */
val Number.spToPx
    get() =
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            this.toFloat(),
            resourcesDisplayMetrics
        ).roundToInt()

val Number.pxToSp
    get() =
        this.toFloat() / resourcesDisplayMetrics.scaledDensity

/**
 * DP單位數值 根據裝置動態 回傳像素:
 * @editor Timmy.Hsieh
 * @date formatted 2023/03/21
 * @return dp根據裝置動態計算 回傳pixel
 * 使用範例：
 * 100.dpToPx
 */
val Number.dpToPx
    get() =
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            resourcesDisplayMetrics
        ).roundToInt()

val Number.pxToDp
    get() =
        this.toFloat() / resourcesDisplayMetrics.density

/**
 * 取得螢幕寬度 單位為整數(pixel)
 * @author Robert Chou didi31139@gmail.com
 * @editor Timmy.Hsieh
 * @date formatted 2023/03/21
 */
fun getScreenWidthPixels(): Int {
    return resourcesDisplayMetrics.widthPixels
}

/**
 * 取得螢幕高度 單位為整數(pixel)
 * @author Robert Chou didi31139@gmail.com
 * @editor Timmy.Hsieh
 * @date formatted 2023/03/21
 */
fun getScreenHeightPixels(): Int {
    return resourcesDisplayMetrics.heightPixels
}

/**
 * 設定 View 的高度。(單位: px)
 * @param value
 */
fun View.setHeight(value: Int) {
    layoutParams?.let {
        it.height = value
        this@setHeight.layoutParams = it
    }
}

/**
 * 設定 View 的寬度。(單位: px)
 * @param value
 */
fun View.setWidth(value: Int) {
    layoutParams?.let {
        it.width = value
        this@setWidth.layoutParams = it
    }
}

/**
 * 設定 view的長寬 單位為畫素(pixel)
 * @param w
 * @param h
 * @author Wang / Robert
 * @editor Timmy.Hsieh
 * @date formatted 2023/03/21
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
 * @editor Timmy.Hsieh
 * @date formatted 2023/03/21
 */
fun View.setViewSizeByDpUnit(w: Int, h: Int) {
    setViewSize(w.dpToPx, h.dpToPx)
}

/**
 * 設定 view的長寬 單位為畫素(pixel) 自動高度
 * @param view
 * @param w
 * @param rid
 * @author Wang / Robert
 * @editor Timmy.Hsieh
 * @date formatted 2023/03/21
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
 * @editor Timmy.Hsieh
 * @date formatted 2023/03/21
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
 * 設定物件間距  單位為畫素(dp)
 * @author Wang / Robert Chou didi31139@gmail.com
 * @editor Timmy.Hsieh
 * @date formatted 2023/03/21
 */
fun View.setMarginByDpUnit(leftMargin: Int, topMargin: Int, rightMargin: Int, bottomMargin: Int) {
    val params = this.layoutParams
    if (params is ViewGroup.MarginLayoutParams) {
        params.setMargins(
            leftMargin.dpToPx,
            topMargin.dpToPx,
            rightMargin.dpToPx,
            bottomMargin.dpToPx
        )
    }
    this.layoutParams = params
    this.requestLayout()
}

/**
 * 設定物件內間距  單位為畫素(dp)
 * @author Wang / Robert Chou didi31139@gmail.com
 * @editor Timmy.Hsieh
 * @date formatted 2023/03/21
 */
fun View.setPaddingByDpUnit(
    leftPadding: Int,
    topPadding: Int,
    rightPadding: Int,
    bottomPadding: Int
) {
    this.setPadding(
        leftPadding.dpToPx,
        topPadding.dpToPx,
        rightPadding.dpToPx,
        bottomPadding.dpToPx
    )

    this.requestLayout()
}

/**
 * 取得測量後的實際寬度(pixel)
 * @author Timmy.Hsieh
 * @date formatted 2023/03/21
 */
fun View.getRealityWidth(): Int {
    this.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    return this.measuredWidth
}

/**
 * 取得測量後的實際高度(pixel)
 * @author Timmy.Hsieh
 * @date formatted 2023/03/21
 */
fun View.getRealityHeight(): Int {
    this.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    return this.measuredHeight
}

/**
 * 取得測量後的實際寬度(pixel)
 * @author Timmy.Hsieh
 * @date formatted 2023/03/21
 */
fun TextView.setTextSize(sp: Int) {
    (this as? TextView)?.setTextSize(TypedValue.COMPLEX_UNIT_SP, sp.spToAutoWidth)
}

/**
 * 把xml中的文字，重新設定以xml中的文字縮放為適應畫面。
 * @author Timmy.Hsieh
 * @date formatted 2023/03/21
 * */
fun ViewGroup.resetLayoutTextSize() {
    val scale = resourcesDisplayMetrics.scaledDensity
    resetLayout {
        (it as? TextView)?.setTextSize((it.textSize / scale + 0.5f).toInt())
    }
}

/**
 * 把xml中的Layout，執行指定的動作
 * @author Timmy.Hsieh
 * @date formatted 2023/03/21
 * */
fun ViewGroup.resetLayout(block: ((View) -> Unit) = {}) {
    this.children.forEach {
        block.invoke(it)
        (it as? ViewGroup)?.resetLayout(block)
    }
}

/**
 * 取得傳入資源的圖片高度
 * @param id 圖片資源的路徑
 * @author Wang / Robert Chou didi31139@gmail.com
 * @editor Timmy.Hsieh
 * @date formatted 2023/03/21
 */
fun Context.getDrawableHeight(id: Int, maxWidth: Int): Int {
    val drawable = getResourceDrawable(id)
    val w = drawable.intrinsicWidth.toFloat()
    val h = drawable.intrinsicHeight.toFloat()
    val scale = h / w
    val ansHeight = maxWidth * scale

    return ansHeight.toInt()
}

/**
 * 取得uri資源的圖片高度
 * @param String 圖片資源的Uri位置(content://...)
 * @author Wang / Robert Chou didi31139@gmail.com
 * @editor Timmy.Hsieh
 * @date formatted 2023/03/21
 */
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

/**
 * 取得傳入資源的圖片寬度
 * @param id 圖片資源的路徑
 * @author Wang / Robert Chou didi31139@gmail.com
 * @editor Timmy.Hsieh
 * @date formatted 2023/03/21
 */
fun Context.getDrawableWidth(id: Int, maxHeight: Int): Int {
    val drawable = this.getResourceDrawable(id)

    val w = drawable.intrinsicWidth.toFloat()
    val h = drawable.intrinsicHeight.toFloat()
    val scale = w / h
    val ansWidth = maxHeight * scale

    return ansWidth.toInt()
}

/**
 * 取得uri資源的圖片寬度
 * @param String 圖片資源的Uri位置(content://...)
 * @author Wang / Robert Chou didi31139@gmail.com
 * @editor Timmy.Hsieh
 * @date formatted 2023/03/21
 */
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

/**
 * 繪製後Log出顯示內容的實際寬高(主要用於debug)
 * @param tagName
 * @author Timmy.Hsieh
 * @date 2023/08/24
 */
fun View.showLayout(tagName: String = "Layout內容寬高"): View = this.also { layout ->
    this.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            layout.viewTreeObserver.removeOnGlobalLayoutListener(this)
            val width: Int = layout.measuredWidth
            val height: Int = layout.measuredHeight
            Log.e(tagName, "內容實際寬高=>{$width,$height} layout=>${layout} ")
        }
    })
}

