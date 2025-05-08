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
            // 螢幕寬度與基準寬度(360dp)的比例，乘以原始SP值
            ((this.toFloat() * metric.widthPixels) / metric.density / 360f)
        }

/**
 * SP單位數值 根據裝置動態 回傳像素:
 * @editor Timmy.Hsieh
 * @date formatted 2023/03/21
 * @return dp根據裝置動態計算 回傳pixel
 * 使用範例：
 * 100.spToPx，會回傳這個裝置轉換為px的數值
 */
val Number.spToPx: Int
    get() = run {
        val metrics = resourcesDisplayMetrics
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            this.toFloat(),
            metrics
        ).roundToInt()
    }

/**
 * DP單位數值 根據裝置動態 回傳像素:
 * @editor Timmy.Hsieh
 * @date formatted 2023/03/21
 * @return dp根據裝置動態計算 回傳pixel
 * 使用範例：
 * 100.dpToPx，會回傳這個裝置轉換為px的數值
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
fun getScreenWidthPixels(): Int = resourcesDisplayMetrics.widthPixels

/**
 * 取得螢幕高度 單位為整數(pixel)
 * @author Robert Chou didi31139@gmail.com
 * @editor Timmy.Hsieh
 * @date formatted 2023/03/21
 */
fun getScreenHeightPixels(): Int = resourcesDisplayMetrics.heightPixels


/**
 * 設定 View 的高度。(單位: px)
 * @param value
 */
fun View.setHeight(value: Int) =
    layoutParams?.let {
        it.height = value
        this@setHeight.layoutParams = it
    }

/**
 * 設定 View 的寬度。(單位: px)
 * @param value
 */
fun View.setWidth(value: Int) =
    layoutParams?.let {
        it.width = value
        this@setWidth.layoutParams = it
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
 * @param w
 * @param h
 * @author Wang / Robert
 * @editor Timmy.Hsieh
 * @date formatted 2023/03/21
 */
fun View.setViewSizeByDpUnit(w: Int, h: Int) = setViewSize(w.dpToPx, h.dpToPx)


/**
 * 設定 方形(Square) view的長寬 單位為畫素(pixel)
 * @param dim  預設定的方形邊長
 * @editor Timmy.Hsieh
 * @date formatted 2023/10/31
 */
fun View.setSquSize(dim: Int) = this.setViewSize(dim, dim)


/**
 * 設定 方形(Square) view的長寬 單位為畫素(pixel)
 * @param dim  預設定的方形邊長
 * @editor Timmy.Hsieh
 * @date formatted 2023/10/31
 */
fun View.setSquSizeByDpUnit(dim: Int) = this.setViewSize(dim.dpToPx, dim.dpToPx)


/**
 * 設定 view的長寬 單位為畫素(pixel) 自動高度
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
 * 設定文字大小(sp)
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
    // 使用 TypedValue.applyDimension 來替代 scaledDensity
    val context = context
    resetLayout {
        (it as? TextView)?.let { textView ->
            // 取得原始 SP 值
            val originalSpValue = textView.textSize / context.resources.displayMetrics.density
            // 設置文字大小，使用 SP 單位
            textView.setTextSize(originalSpValue.roundToInt())
        }
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
 * @class String 圖片資源的Uri位置(content://...)
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
 * @class String  圖片資源的Uri位置(content://...)
 * @author Wang / Robert Chou didi31139@gmail.com
 * @editor Timmy.Hsieh
 * @date formatted 2023/03/21
 */
fun String.getUriImageWidth(maxHeight: Int): Int {
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
fun View.showLayout(tagName: String = "Layout內容寬高${this}"): View = this.also { layout ->
    this.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            layout.viewTreeObserver.removeOnGlobalLayoutListener(this)
            val width: Int = layout.measuredWidth
            val height: Int = layout.measuredHeight
            Log.e(tagName, "內容實際寬高=>{$width,$height} layout=>${layout} ")
        }
    })
}

/**
 * 繪製完Layout，取得實際寬高以後要執行的動作
 * @param action,,,第一個參數：width,,,第二個參數：height
 * @author Timmy.Hsieh
 * @date 2023/08/24
 */
fun View.onLayoutComplete(action: (Int, Int) -> Unit): View = this.also { layout ->
    this.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            layout.viewTreeObserver.removeOnGlobalLayoutListener(this)
            val width: Int = layout.measuredWidth
            val height: Int = layout.measuredHeight
            action.invoke(width, height)
        }
    })
}
