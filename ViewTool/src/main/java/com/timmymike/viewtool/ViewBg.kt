package com.timmymike.viewtool

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Shader
import android.graphics.drawable.*
import android.os.Build
import android.view.View
import androidx.annotation.ColorRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.graphics.drawable.toBitmap
import com.caverock.androidsvg.SVG


/**由timmymike整理的畫面美觀工具*/


/**
 * 設定水波紋
 * ※ Android 21版以上才有水波紋的效果，可以設定為背景是因為文字通常設定為背景即可。
 * @param colorId 水波紋顏色(Resource編號)
 * @param maskArea 要填滿與顯示的背景
 * @author Timmy.Hsieh
 * @date formatted 2023/03/21
 */
fun View.setRippleBackgroundById(@ColorRes colorId: Int, maskArea: Drawable? = background, showOrigBgAfterAddRipple: Boolean = true) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        RippleDrawable(
            ColorStateList(
                arrayOf(intArrayOf()),
                intArrayOf(this.getResourceColor(colorId))
            ),
            if (showOrigBgAfterAddRipple) maskArea else null,
            maskArea?.current
        ).let {
            this@setRippleBackgroundById.background = it
        }
    }
}

/**
 * 設定水波紋
 * ※ Android 21版以上才有水波紋的效果，可以設定為背景是因為文字通常設定為背景即可。
 * @param color 水波紋顏色
 * @param maskArea 要填滿與顯示的背景
 * @author Timmy.Hsieh
 * @date formatted 2023/03/21
 */
fun View.setRippleBackground(color: Int, maskArea: Drawable? = background, showOrigBgAfterAddRipple: Boolean = true) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        RippleDrawable(
            ColorStateList(
                arrayOf(intArrayOf()),
                intArrayOf(color)
            ),
            if (showOrigBgAfterAddRipple) maskArea else null,
            maskArea?.current
        ).let {
            this@setRippleBackground.background = it
        }
    }
}

/**
 * 設定水波紋
 * ※ Android 21版以上才有水波紋的效果，23版才可以設定為前景效果。
 * @param colorId 水波紋顏色(Resource編號)
 * @param maskArea 要填滿與顯示的背景
 * @author Timmy.Hsieh
 * @date formatted 2023/03/21
 */
fun View.setRippleForegroundById(@ColorRes colorId: Int, maskArea: Drawable? = background, showOrigBgAfterAddRipple: Boolean = true) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        RippleDrawable(
            ColorStateList(
                arrayOf(intArrayOf()),
                intArrayOf(this.getResourceColor(colorId))
            ),
            if (showOrigBgAfterAddRipple) maskArea else null,
            maskArea?.current
        ).let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                this@setRippleForegroundById.foreground = it
            } else {
                this@setRippleForegroundById.background = it
            }
        }
    }
}

/**
 * 設定水波紋
 * ※ Android 21版以上才有水波紋的效果，23版才可以設定為前景效果。
 * @param color 水波紋顏色
 * @param maskArea 要填滿與顯示的背景
 * @author Timmy.Hsieh
 * @date formatted 2023/03/21
 */
fun View.setRippleForeground(color: Int, maskArea: Drawable? = background, showOrigBgAfterAddRipple: Boolean = true) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        RippleDrawable(
            ColorStateList(
                arrayOf(intArrayOf()),
                intArrayOf(color)
            ),
            if (showOrigBgAfterAddRipple) maskArea else null,
            maskArea?.current
        ).let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                this@setRippleForeground.foreground = it
            } else {
                this@setRippleForeground.background = it
            }
        }
    }
}
/**
 * @param svgString 要轉換為SVG的字串
 * @editor Timmy.Hsieh
 * @date formatted 2024/11/13
 */
fun getDrawableBySVGString(svgString:String): PictureDrawable {
    // 創建 SVG 解析器
    return PictureDrawable(SVG.getFromString(svgString).renderToPicture())
}

/**
 * @param corner 圓角弧度(Dp)
 * @param bgColorID 背景填滿色
 * @param strokeColorID 邊框顏色
 * @param strokeWidth 邊框粗細 (Dp)
 * @editor Timmy.Hsieh
 * @date formatted 2023/03/21
 */
fun Context.getRoundBgById(
    corner: Int,
    bgColorID: Int, strokeColorID: Int = 0, strokeWidth: Int = 0
): GradientDrawable {
    val tl = corner.dpToPx
    val tr = corner.dpToPx
    val bl = corner.dpToPx
    val br = corner.dpToPx
    return createShapeDrawableById(this, bgColorID, floatArrayOf(tl.toFloat(), tl.toFloat(), tr.toFloat(), tr.toFloat(), br.toFloat(), br.toFloat(), bl.toFloat(), bl.toFloat()), strokeWidth.dpToPx, strokeColorID, GradientDrawable.RECTANGLE)
}


/**
 * @param corner 圓角弧度(Dp)
 * @param bgColorID 背景填滿色
 * @param strokeColorID 邊框顏色
 * @param strokeWidth 邊框粗細 (Dp)
 * @editor Timmy.Hsieh
 * @date formatted 2024/11/06
 * 直接傳入Px單位的版本，以畫出更細的邊線
 */
fun Context.getRoundBgPxById(
    corner: Int,
    bgColorID: Int, strokeColorID: Int = 0, strokeWidth: Int = 0
): GradientDrawable {
    return createShapeDrawableById(this, bgColorID, floatArrayOf(corner.toFloat(), corner.toFloat(), corner.toFloat(), corner.toFloat(), corner.toFloat(), corner.toFloat(), corner.toFloat(), corner.toFloat()), strokeWidth, strokeColorID, GradientDrawable.RECTANGLE)
}
/**
 * @param corner 圓角弧度(Dp)
 * @param bgColor 背景填滿色
 * @param strokeColor 邊框顏色
 * @param strokeWidth 邊框粗細 (Dp)
 * @editor Timmy.Hsieh
 * @date formatted 2024/11/18
 * 直接傳入Px單位的版本，以畫出更細的邊線
 * 新增不使用資源Color，直接傳入顏色的Int值的方法。
 */
fun getRoundBgPx(
    corner: Int,
    bgColor: Int, strokeColor: Int = 0, strokeWidth: Int = 0
): GradientDrawable {
    return createShapeDrawable( bgColor, floatArrayOf(corner.toFloat(), corner.toFloat(), corner.toFloat(), corner.toFloat(), corner.toFloat(), corner.toFloat(), corner.toFloat(), corner.toFloat()), strokeWidth, strokeColor, GradientDrawable.RECTANGLE)
}

/**
 * @param corner 圓角弧度(Dp)
 * @param bgColor 背景填滿色
 * @param strokeColor 邊框顏色
 * @param strokeWidth 邊框粗細 (Dp)
 * @editor Timmy.Hsieh
 * @date formatted 2024/11/18
 * 新增不使用資源Color，直接傳入顏色的Int值的方法。
 */
fun getRoundBg(
    corner: Int,
    bgColor: Int, strokeColor: Int = 0, strokeWidth: Int = 0
): GradientDrawable {
    val tl = corner.dpToPx
    val tr = corner.dpToPx
    val bl = corner.dpToPx
    val br = corner.dpToPx
    return createShapeDrawable( bgColor, floatArrayOf(tl.toFloat(), tl.toFloat(), tr.toFloat(), tr.toFloat(), br.toFloat(), br.toFloat(), bl.toFloat(), bl.toFloat()), strokeWidth.dpToPx, strokeColor, GradientDrawable.RECTANGLE)
}


/**
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

fun Context.getRectangleBgById(
    tldp: Int = 0, trdp: Int = 0, bldp: Int = 0, brdp: Int = 0,
    left: Boolean = true, top: Boolean = true, right: Boolean = true, bottom: Boolean = true,
    bgColorID: Int = 0, strokeColorID: Int = 0, strokeWidth: Int = 0
): LayerDrawable {

    val tl = tldp.dpToPx
    val tr = trdp.dpToPx
    val bl = bldp.dpToPx
    val br = brdp.dpToPx

    val drawable = createShapeDrawableById(this, bgColorID, floatArrayOf(tl.toFloat(), tl.toFloat(), tr.toFloat(), tr.toFloat(), br.toFloat(), br.toFloat(), bl.toFloat(), bl.toFloat()), strokeWidth.dpToPx, strokeColorID, GradientDrawable.RECTANGLE)

    val layerDrawable = LayerDrawable(arrayOf<Drawable>(drawable))
    val w = strokeWidth.dpToPx
    layerDrawable.setLayerInset(0, if (left) 0 else -w, if (top) 0 else -w, if (right) 0 else -w, if (bottom) 0 else -w)

    return layerDrawable
}

/**
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
 * @date formatted 2024/11/06
 * 直接傳入Px單位的版本，以畫出更細的邊線
 */

fun Context.getRectangleBgPxById(
    tldp: Int = 0, trdp: Int = 0, bldp: Int = 0, brdp: Int = 0,
    left: Boolean = true, top: Boolean = true, right: Boolean = true, bottom: Boolean = true,
    bgColorID: Int = 0, strokeColorID: Int = 0, strokeWidth: Int = 0
): LayerDrawable {

    val drawable = createShapeDrawableById(this, bgColorID, floatArrayOf(tldp.toFloat(), tldp.toFloat(), trdp.toFloat(), trdp.toFloat(), brdp.toFloat(), brdp.toFloat(), bldp.toFloat(), bldp.toFloat()), strokeWidth, strokeColorID, GradientDrawable.RECTANGLE)

    val layerDrawable = LayerDrawable(arrayOf<Drawable>(drawable))
    layerDrawable.setLayerInset(0, if (left) 0 else -strokeWidth, if (top) 0 else -strokeWidth, if (right) 0 else -strokeWidth, if (bottom) 0 else -strokeWidth)

    return layerDrawable
}



/**
 * @param tldp 左上弧度 (Dp)
 * @param trdp 右上弧度 (Dp)
 * @param bldp 左下弧度 (Dp)
 * @param brdp 右下弧度 (Dp)
 * @param left 是否顯示邊框 true為顯示
 * @param top 是否顯示邊框 true為顯示
 * @param right 是否顯示邊框 true為顯示
 * @param bottom 是否顯示邊框 true為顯示
 * @param bgColor 背景填滿色
 * @param strokeColor 邊框顏色
 * @param strokeWidth 邊框粗細 (Dp)
 * @editor Timmy.Hsieh
 * @date formatted 2024/11/18
 * 新增不使用資源Color，直接傳入顏色的Int值的方法。
 */

fun getRectangleBg(
    tldp: Int = 0, trdp: Int = 0, bldp: Int = 0, brdp: Int = 0,
    left: Boolean = true, top: Boolean = true, right: Boolean = true, bottom: Boolean = true,
    bgColor: Int = 0, strokeColor: Int = 0, strokeWidth: Int = 0
): LayerDrawable {

    val tl = tldp.dpToPx
    val tr = trdp.dpToPx
    val bl = bldp.dpToPx
    val br = brdp.dpToPx

    val drawable = createShapeDrawable( bgColor, floatArrayOf(tl.toFloat(), tl.toFloat(), tr.toFloat(), tr.toFloat(), br.toFloat(), br.toFloat(), bl.toFloat(), bl.toFloat()), strokeWidth.dpToPx, strokeColor, GradientDrawable.RECTANGLE)

    val layerDrawable = LayerDrawable(arrayOf<Drawable>(drawable))
    val w = strokeWidth.dpToPx
    layerDrawable.setLayerInset(0, if (left) 0 else -w, if (top) 0 else -w, if (right) 0 else -w, if (bottom) 0 else -w)

    return layerDrawable
}

/**
 * @param tldp 左上弧度 (Dp)
 * @param trdp 右上弧度 (Dp)
 * @param bldp 左下弧度 (Dp)
 * @param brdp 右下弧度 (Dp)
 * @param left 是否顯示邊框 true為顯示
 * @param top 是否顯示邊框 true為顯示
 * @param right 是否顯示邊框 true為顯示
 * @param bottom 是否顯示邊框 true為顯示
 * @param bgColor 背景填滿色
 * @param strokeColor 邊框顏色
 * @param strokeWidth 邊框粗細 (Dp)
 * @editor Timmy.Hsieh
 * @date formatted 2024/11/18
 * 直接傳入Px單位的版本，以畫出更細的邊線
 * 新增不使用資源Color，直接傳入顏色的Int值的方法。
 */

fun getRectangleBgPx(
    tldp: Int = 0, trdp: Int = 0, bldp: Int = 0, brdp: Int = 0,
    left: Boolean = true, top: Boolean = true, right: Boolean = true, bottom: Boolean = true,
    bgColor: Int = 0, strokeColor: Int = 0, strokeWidth: Int = 0
): LayerDrawable {

    val drawable = createShapeDrawable( bgColor, floatArrayOf(tldp.toFloat(), tldp.toFloat(), trdp.toFloat(), trdp.toFloat(), brdp.toFloat(), brdp.toFloat(), bldp.toFloat(), bldp.toFloat()), strokeWidth, strokeColor, GradientDrawable.RECTANGLE)

    val layerDrawable = LayerDrawable(arrayOf<Drawable>(drawable))
    layerDrawable.setLayerInset(0, if (left) 0 else -strokeWidth, if (top) 0 else -strokeWidth, if (right) 0 else -strokeWidth, if (bottom) 0 else -strokeWidth)

    return layerDrawable
}

/**
 * 回傳色碼的橢圓形背景
 * @author Robert Chou didi31139@gmail.com
 * @editor Timmy.Hsieh
 * @date formatted 2023/03/21
 */
fun Context.getOVALPointDrawableById(colorID: Int): GradientDrawable {
    return createShapeDrawableById(this, colorID, floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f), 0, 0, GradientDrawable.OVAL)
}
/**
 * 回傳色碼的橢圓形背景
 * @author Robert Chou didi31139@gmail.com
 * @editor Timmy.Hsieh
 * @date formatted 2024/11/18
 * 新增不使用資源Color，直接傳入顏色的Int值的方法。
 */
fun getOVALPointDrawable(color: Int): GradientDrawable {
    return createShapeDrawable( color, floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f), 0, 0, GradientDrawable.OVAL)
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
 * @param color ex:填滿顏色
 * @param radii The corners are ordered top-left, top-right, bottom-right, bottom-left.
 * @param strokeWidth 外框畫筆寬度
 * @param strokeColor 外框顏色
 * @param gradientDrawableShape 圖片形狀 ex:GradientDrawable.RECTANGLE
 * @author Robert Chou didi31139@gmail.com
 * @editor Timmy.Hsieh
 * @date formatted 2023/03/21
 */
private fun createShapeDrawable( color: Int, radii: FloatArray?, strokeWidth: Int, strokeColor: Int, gradientDrawableShape: Int): GradientDrawable {

    val gradientDrawable = GradientDrawable()
    if (color != 0)
        gradientDrawable.setColor(color)
    if (radii != null) {
        gradientDrawable.cornerRadii = radii
    }
    if (strokeWidth != 0 && strokeColor != 0) {
        gradientDrawable.setStroke(strokeWidth, strokeColor)
    }

    if (gradientDrawableShape != 0) {
        gradientDrawable.shape = gradientDrawableShape
    }
    return gradientDrawable
}

/**
 * 建立形狀的圖片並回傳
 * @param context
 * @param colorID ex:填滿顏色 // Resource資源檔
 * @param radii The corners are ordered top-left, top-right, bottom-right, bottom-left.
 * @param strokeWidth 外框畫筆寬度
 * @param strokeColorID 外框顏色 // Resource資源檔
 * @param gradientDrawableShape 圖片形狀 ex:GradientDrawable.RECTANGLE
 * @author Robert Chou didi31139@gmail.com
 * @editor Timmy.Hsieh
 * @date formatted 2023/03/21
 */
private fun createShapeDrawableById(context: Context, colorID: Int, radii: FloatArray?, strokeWidth: Int, strokeColorID: Int, gradientDrawableShape: Int): GradientDrawable {

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