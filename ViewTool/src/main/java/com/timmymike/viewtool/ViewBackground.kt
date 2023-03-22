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

/**
 * 設定水波紋
 * @param colorID 水波紋顏色
 * @param maskArea 要填滿與顯示的背景
 * @author Timmy.Hsieh
 * @date formatted 2023/03/21
 */
fun View.setRippleBackground(colorId: Int, maskArea: Drawable? = background, showOrigBgAfterAddRipple: Boolean = true) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

        this.background = RippleDrawable(
            ColorStateList(
                arrayOf(intArrayOf()),
                intArrayOf(this.getResourceColor(colorId))
            ),
            if (showOrigBgAfterAddRipple) maskArea else null,
            maskArea?.current
        )
    }
}

/**
 * @param context
 * @param corner 圓角弧度(Dp)
 * @param bgColorID 背景填滿色
 * @param strokeColorID 邊框顏色
 * @param strokeWidth 邊框粗細 (Dp)
 * @editor Timmy.Hsieh
 * @date formatted 2023/03/21
 */
fun getRoundBg(
    context: Context,
    corner: Int,
    bgColorID: Int, strokeColorID: Int = 0, strokeWidth: Int = 0
): GradientDrawable {
    val tl = corner.dpToPx
    val tr = corner.dpToPx
    val bl = corner.dpToPx
    val br = corner.dpToPx
    return createShapeDrawable(context, bgColorID, floatArrayOf(tl.toFloat(), tl.toFloat(), tr.toFloat(), tr.toFloat(), br.toFloat(), br.toFloat(), bl.toFloat(), bl.toFloat()), strokeWidth.dpToPx, strokeColorID, GradientDrawable.RECTANGLE)
}

/**
 * @param context
 * @param tldp 左上弧度 (Dp)
 * @param trdp 右上弧度 (Dp)
 * @param bldp 左下弧度 (Dp)
 * @param brdp 右下弧度 (Dp)
 * @param left 是否顯示邊框 true為顯示
 * @param top 是否顯示邊框 true為顯示
 * @param right 是否顯示邊框 true為顯示
 * @param bottom 是否顯示邊框 true為顯示
 * @param bgColorID 背景填滿色
 * @param strokeColorID 邊框顏色
 * @param strokeWidth 邊框粗細 (Dp)
 * @editor Timmy.Hsieh
 * @date formatted 2023/03/21
 */

fun Context.getRectangleBg(
    tldp: Int = 0, trdp: Int = 0, bldp: Int = 0, brdp: Int = 0,
    left: Boolean = true, top: Boolean = true, right: Boolean = true, bottom: Boolean = true,
    bgColorID: Int = 0, strokeColorID: Int = 0, strokeWidth: Int = 0
): LayerDrawable {

    val tl = tldp.dpToPx
    val tr = trdp.dpToPx
    val bl = bldp.dpToPx
    val br = brdp.dpToPx

    val drawable = createShapeDrawable(this, bgColorID, floatArrayOf(tl.toFloat(), tl.toFloat(), tr.toFloat(), tr.toFloat(), br.toFloat(), br.toFloat(), bl.toFloat(), bl.toFloat()), strokeWidth.dpToPx, strokeColorID, GradientDrawable.RECTANGLE)

    val layerDrawable = LayerDrawable(arrayOf<Drawable>(drawable))
    val w = strokeWidth.dpToPx
    layerDrawable.setLayerInset(0, if (left) 0 else -w, if (top) 0 else -w, if (right) 0 else -w, if (bottom) 0 else -w)

    return layerDrawable
}

/**
 * 回傳色碼的橢圓形背景
 * @author Robert Chou didi31139@gmail.com
 * @editor Timmy.Hsieh
 * @date formatted 2023/03/21
 */
fun Context.getOVALPointDrawable(colorID: Int): GradientDrawable {
    return createShapeDrawable(this, colorID, floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f), 0, 0, GradientDrawable.OVAL)
}

/**
 * 取得 填滿Drawable的背景
 * @author Robert Chou didi31139@gmail.com
 * @editor Timmy.Hsieh
 * @date formatted 2023/03/21
 */

fun Context.getRepeatDrawable(drawableID: Int): BitmapDrawable? {
    return kotlin.runCatching {
        this.getRepeatDrawable(this.getResourceDrawable(drawableID).toBitmap())
    }.getOrNull()
}

/**
 * 取得 填滿 Drawable 的背景
 * @author Robert Chou didi31139@gmail.com
 * @editor Timmy.Hsieh
 * @date formatted 2023/03/21
 */


fun Context.getRepeatDrawable(bitmap: Bitmap?): BitmapDrawable? {
    if (bitmap == null) return null
    return BitmapDrawable(this.resources, bitmap).apply {
        setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)
    }
}

/**
 * 取得 渲染過顏色後的 svg 背景
 * @author Robert Chou didi31139@gmail.com
 * @editor Timmy.Hsieh
 * @date formatted 2023/03/21
 */
fun Context.getTintedDrawable(drawableId: Int, colorId: Int): Drawable {
    return this.getResourceDrawable(drawableId).apply {
        DrawableCompat.wrap(this)
        DrawableCompat.setTintList(this.mutate(), AppCompatResources.getColorStateList(this@getTintedDrawable, colorId))
    }
}

/**
 * 建立形狀的圖片並回傳
 * @param context
 * @param colorID ex:填滿顏色
 * @param radii The corners are ordered top-left, top-right, bottom-right, bottom-left.
 * @param strokeWidth 外框畫筆寬度
 * @param strokeColorID 外框顏色
 * @param gradientDrawableShape 圖片形狀 ex:GradientDrawable.RECTANGLE
 * @author Robert Chou didi31139@gmail.com
 * @editor Timmy.Hsieh
 * @date formatted 2023/03/21
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
