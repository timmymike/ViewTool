package com.timmymike.viewtool

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Shader
import android.graphics.drawable.*
import android.os.Build
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.graphics.drawable.toBitmap


/**由timmymike整理的畫面美觀工具*/

/**設定水波文*/
fun View.setRippleBackground(color: Int, states: Drawable? = background) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

        val rippleColor = this.getResourceColor(color)
        val colorStateList = ColorStateList(
            arrayOf(intArrayOf()),
            intArrayOf(rippleColor)
        )

        this.background =  RippleDrawable(colorStateList, states, states?.current)
    }
}

/**
 *
 * 建立形狀的圖片並回傳
 * @author Robert Chou didi31139@gmail.com
 * @param context
 * @param colorID ex:填滿顏色
 * @param radii The corners are ordered top-left, top-right, bottom-right, bottom-left.
 * @param strokeWidth 外框畫筆寬度
 * @param strokeColorID 外框顏色
 * @param gradientDrawableShape 圖片形狀 ex:GradientDrawable.RECTANGLE
 * @date 2021/03/21
 * @version
 */
private fun createShapeDrawable(context: Context, colorID: Int, radii: FloatArray?, strokeWidth: Int, strokeColorID: Int, gradientDrawableShape: Int): GradientDrawable {

    val gradientDrawable = GradientDrawable()
    if (colorID != 0)
        gradientDrawable.setColor(context.getResourceColor(colorID))
    if (radii != null) {
        gradientDrawable.cornerRadii = radii
    }
    if (strokeWidth != 0 && strokeColorID != 0) {
        gradientDrawable.setStroke(strokeWidth, context.getResourceColor(strokeColorID))
    }

    if (gradientDrawableShape != 0) {
        gradientDrawable.shape = gradientDrawableShape
    }
    return gradientDrawable
}

/**
 * @param context
 * @param corner 圓角弧度(Dp)
 * @param bgColorID 背景填滿色
 * @param strokeColorID 邊框顏色
 * @param strokeWidth 邊框粗細 (Dp)
 * @date 2023/03/21
 * @version
 */
fun getRoundBg(
    context: Context,
    corner: Int,
    bgColorID: Int, strokeColorID: Int = 0, strokeWidth: Int = 0
): GradientDrawable {
    val tl = corner.dpToPx(context)
    val tr = corner.dpToPx(context)
    val bl = corner.dpToPx(context)
    val br = corner.dpToPx(context)
    return createShapeDrawable(context, bgColorID, floatArrayOf(tl.toFloat(), tl.toFloat(), tr.toFloat(), tr.toFloat(), br.toFloat(), br.toFloat(), bl.toFloat(), bl.toFloat()), strokeWidth.dpToPx(context), strokeColorID, GradientDrawable.RECTANGLE)
}

/**
 * @param context
 * @param tldp 左上弧度 (PixelByDevice)
 * @param trdp 右上弧度 (PixelByDevice)
 * @param bldp 左下弧度 (PixelByDevice)
 * @param brdp 右下弧度 (PixelByDevice)
 * @param left 是否顯示邊框 true為顯示
 * @param top 是否顯示邊框 true為顯示
 * @param right 是否顯示邊框 true為顯示
 * @param bottom 是否顯示邊框 true為顯示
 * @param bgColorID 背景填滿色
 * @param strokeColorID 邊框顏色
 * @param strokeWidth 邊框粗細 (PixelByDevice)
 * @date 2023/03/21
 * @version
 */

fun Context.getRectangleBg(
    tldp: Int = 0, trdp: Int = 0, bldp: Int = 0, brdp: Int = 0,
    left: Boolean = true, top: Boolean = true, right: Boolean = true, bottom: Boolean = true,
    bgColorID: Int = 0, strokeColorID: Int = 0, strokeWidth: Int = 0
): LayerDrawable {

    val tl = tldp.dpToPx(this)
    val tr = trdp.dpToPx(this)
    val bl = bldp.dpToPx(this)
    val br = brdp.dpToPx(this)

    val drawable = createShapeDrawable(this, bgColorID, floatArrayOf(tl.toFloat(), tl.toFloat(), tr.toFloat(), tr.toFloat(), br.toFloat(), br.toFloat(), bl.toFloat(), bl.toFloat()), strokeWidth.dpToPx(this), strokeColorID, GradientDrawable.RECTANGLE)

    val layerDrawable = LayerDrawable(arrayOf<Drawable>(drawable))
    val w = strokeWidth.dpToPx(this).toInt()
    layerDrawable.setLayerInset(0, if (left) 0 else -w, if (top) 0 else -w, if (right) 0 else -w, if (bottom) 0 else -w)

    return layerDrawable
}

/**
 * 回傳色碼的橢圓形背景
 * @author Robert Chou didi31139@gmail.com
 * @date 2023/03/21
 * @version
 */
fun Context.getOVALPointDrawable(colorID: Int): GradientDrawable {
    return createShapeDrawable(this, colorID, floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f), 0, 0, GradientDrawable.OVAL)
}

/**
 * 取得 填滿Drawable的背景
 * @author Robert Chou didi31139@gmail.com
 * @date 2023/03/21
 * @version
 */

fun Context.getRepeatDrawable(drawableID: Int): BitmapDrawable? {
    return kotlin.runCatching {
        this.getRepeatDrawable(this.getResourceDrawable(drawableID).toBitmap())
    }.getOrNull()
}

fun Context.getRepeatDrawable(bitmap: Bitmap?): BitmapDrawable? {
    if (bitmap == null) return null
    return BitmapDrawable(this.resources, bitmap).apply {
        setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)
    }
}

/**
 * 取得 渲染過顏色後的 svg 背景
 * */
fun Context.getTintedDrawable(drawableId: Int, colorId: Int): Drawable {
    return this.getResourceDrawable(drawableId).let {
        DrawableCompat.wrap(it)
        DrawableCompat.setTintList(it.mutate(), AppCompatResources.getColorStateList(this, colorId))
        it
    }
}


