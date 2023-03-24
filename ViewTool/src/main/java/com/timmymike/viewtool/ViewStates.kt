package com.timmymike.viewtool

import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView


/**由timmymike整理的畫面美觀工具*/

/**
 * 設定 壓下的圖片切換效果
 * @param baseDrawable 未按下的圖片
 * @param pressedDrawable   按下的圖片
 * @param selectedDrawable 選擇後的圖片
 * @editor Timmy.Hsieh
 * @date formatted 2023/03/21
 */
fun View.setClickImgState(baseDrawable: Drawable, pressedDrawable: Drawable? = null, selectedDrawable: Drawable? = null): StateListDrawable {
    return StateListDrawable().apply {
        pressedDrawable?.let { addState(intArrayOf(android.R.attr.state_pressed), it) }
        selectedDrawable?.let { addState(intArrayOf(android.R.attr.state_selected), it) }
        addState(intArrayOf(), baseDrawable)
        (this@setClickImgState as? ImageView)?.setImageDrawable(this)
    }
}

/**
 * 設定 壓下的圖片切換效果
 * @param baseDrawableId 未按下的圖片ID
 * @param pressedDrawableId   按下的圖片ID
 * @param selectedDrawableId 選擇後的圖片ID
 * @editor Timmy.Hsieh
 * @date formatted 2023/03/21
 */
fun View.setClickImgStateById(baseDrawableId: Int, pressedDrawableId: Int = 0, selectedDrawableId: Int = 0) =
    setClickImgState(
        getResourceDrawable(baseDrawableId),
        getResourceDrawableOrNull(pressedDrawableId),
        getResourceDrawableOrNull(selectedDrawableId),
    )


/**
 * 設定 壓下的背景切換效果
 * @param baseDrawable 未按下的圖片
 * @param pressedDrawable   按下的圖片
 * @param selectedDrawable 選擇後的圖片
 * @editor Timmy.Hsieh
 * @date formatted 2023/03/21
 */
fun View.setClickBgState(baseDrawable: Drawable, pressedDrawable: Drawable? = null, selectedDrawable: Drawable? = null): StateListDrawable {
    return StateListDrawable().apply {
        pressedDrawable?.let { addState(intArrayOf(android.R.attr.state_pressed), it) }
        selectedDrawable?.let { addState(intArrayOf(android.R.attr.state_selected), it) }
        addState(intArrayOf(), baseDrawable)
        this@setClickBgState.background = this
    }
}

/**
 * 設定 壓下的背景切換效果
 * @param baseDrawableID 未按下的圖片Id
 * @param pressedDrawableID   按下的圖片Id
 * @param selectedDrawableId 選擇後的圖片Id
 * @editor Timmy.Hsieh
 * @date formatted 2023/03/21
 */
fun View.setClickBgStateById(baseDrawableID: Int, pressedDrawableID: Int = 0, selectedDrawableId: Int = 0) =
    setClickBgState(
        getResourceDrawable(baseDrawableID),
        getResourceDrawableOrNull(pressedDrawableID),
        getResourceDrawableOrNull(selectedDrawableId),
    )

/**
 * 設定按鈕 的文字顏色
 * @param baseColorId 未按下的顏色 R.color.color1
 * @param pressedColorId 按下的顏色 R.color.color2
 * @param selectedColorId 選擇後的顏色  R.color.color3
 * @editor Timmy.Hsieh
 * @date formatted 2023/03/21
 */
fun View.setClickTextColorStateById(baseColorId: Int, pressedColorId: Int = 0, selectedColorId: Int = 0) {
    val baseColor = getResourceColor(baseColorId)
    val pressedColor = getResourceColorOrNull(pressedColorId) ?: baseColor
    val selectedColor = getResourceColorOrNull(selectedColorId) ?: baseColor

    setClickTextColorState(baseColor, pressedColor, selectedColor)
}

/**
 * 設定按鈕 的文字顏色
 * @param baseColor 未按下的顏色 R.color.color1
 * @param pressedColor 按下的顏色 R.color.color2
 * @param selectedColor 選擇後的顏色  R.color.color3
 * @editor Timmy.Hsieh
 * @date formatted 2023/03/21
 */
fun View.setClickTextColorState(baseColor: Int, pressedColor: Int = -1, selectedColor: Int = -1) {

    val colorStateList = ColorStateList(
        arrayOf(
            intArrayOf(android.R.attr.state_pressed),
            intArrayOf(android.R.attr.state_selected),
            intArrayOf()
        ),
        intArrayOf(
            if (pressedColor != -1) pressedColor else baseColor,
            if (selectedColor != -1) selectedColor else baseColor,
            baseColor,
        )
    )
    (this as TextView).setTextColor(colorStateList)
}

/**
 * check box 選取框圖片設定
 * @param baseDrawableId 未按下的圖片 R.drawable.basedrawable
 * @param checkedDrawableId 未按下的圖片 R.drawable.checkeddrawable
 * @editor Timmy.Hsieh
 * @date formatted 2023/03/21
 */
fun View.setCheckDrawable(baseDrawableId: Int, checkedDrawableId: Int): StateListDrawable? {

    val baseDrawable = kotlin.runCatching {
        this.getResourceDrawable(baseDrawableId)
    }.getOrElse { ColorDrawable(this.getResourceColor(android.R.color.transparent)) }

    val checkedDrawable = kotlin.runCatching {
        this.getResourceDrawable(checkedDrawableId)
    }.getOrElse { return null }

    return StateListDrawable().apply {
        addState(intArrayOf(android.R.attr.state_checkable), baseDrawable)
        addState(intArrayOf(android.R.attr.state_checked), checkedDrawable)
        addState(intArrayOf(), baseDrawable)
        (this@setCheckDrawable as? CheckBox)?.let { buttonDrawable = this } ?: run {
            this@setCheckDrawable.background = this@apply
        }
    }
}