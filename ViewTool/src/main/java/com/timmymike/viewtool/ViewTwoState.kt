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
 * @param unPressedDrawable 未按下的圖片 R.drawable.unPressedImage
 * @param pressedDrawable   按下的圖片 R.drawable.pressedImage
 * @param beSelected 為true，選擇後狀態改變，為false(預設)，則只有「滑到到按鈕上方」才有改變的效果
 * @editor Timmy.Hsieh
 * @date formatted 2023/03/21
 */
fun View.setPressedImage(unPressedDrawable: Drawable, pressedDrawable: Drawable?, beSelected: Boolean = false): StateListDrawable? {
    if (pressedDrawable == null) {
        this.background = unPressedDrawable
        return null
    }
    return StateListDrawable().apply {

        if (beSelected)
            addState(intArrayOf(android.R.attr.state_selected), pressedDrawable)
        else
            addState(intArrayOf(android.R.attr.state_pressed), pressedDrawable)
        addState(intArrayOf(), unPressedDrawable)
        (this@setPressedImage as? ImageView)?.setImageDrawable(this)
    }
}

/**
 * 設定 壓下的圖片切換效果
 * @param unPressedDrawableID 未按下的圖片 R.drawable.unPressedImage
 * @param pressedDrawableID   按下的圖片 R.drawable.pressedImage
 * @param beSelected 為true，選擇後狀態改變，為false(預設)，則只有「滑到到按鈕上方」才有改變的效果
 * @editor Timmy.Hsieh
 * @date formatted 2023/03/21
 */
fun View.setPressedImage(unPressedDrawableID: Int, pressedDrawableID: Int, beSelected: Boolean = false) =
    setPressedImage(
        context.getResourceDrawable(unPressedDrawableID),
        context.getResourceDrawable(pressedDrawableID),
        beSelected
    )


/**
 * 設定 壓下的背景切換效果
 * @param unPressedDrawable 未按下的圖片 R.drawable.unPressedImage
 * @param pressedDrawable   按下的圖片 R.drawable.pressedImage
 * @param beSelected 為true，選擇後狀態改變，為false(預設)，則只有「滑到到按鈕上方」才有改變的效果
 * @editor Timmy.Hsieh
 * @date formatted 2023/03/21
 */
fun View.setPressedBackground(unPressedDrawable: Drawable, pressedDrawable: Drawable?, beSelected: Boolean = false): StateListDrawable? {
    if (pressedDrawable == null) {
        this.background = unPressedDrawable
        return null
    }
    return StateListDrawable().apply {

        if (beSelected)
            addState(intArrayOf(android.R.attr.state_selected), pressedDrawable)
        else
            addState(intArrayOf(android.R.attr.state_pressed), pressedDrawable)

        addState(intArrayOf(), unPressedDrawable)
        this@setPressedBackground.background = this
    }
}

/**
 * 設定 壓下的背景切換效果
 * @param unPressedDrawableID 未按下的圖片 R.drawable.unPressedImage
 * @param pressedDrawableID   按下的圖片 R.drawable.pressedImage
 * @param beSelected 為true，選擇後狀態改變，為false(預設)，則只有「滑到到按鈕上方」才有改變的效果
 * @editor Timmy.Hsieh
 * @date formatted 2023/03/21
 */
fun View.setPressedBackground(unPressedDrawableID: Int, pressedDrawableID: Int, beSelected: Boolean = false) =
    setPressedBackground(
        context.getResourceDrawable(unPressedDrawableID),
        context.getResourceDrawable(pressedDrawableID),
        beSelected
    )


/**
 * 設定按鈕 被按住的顏色背景
 * @param unPressedColor 未按下的顏色背景 R.color.color1
 * @param pressedColor 按下的顏色 R.color.color
 * @param beSelected 為true，選擇後狀態改變，為false(預設)，則只有「滑到到按鈕上方」才有改變的效果
 * @editor Timmy.Hsieh
 * @date formatted 2023/03/21
 */
fun View.setPressedTextColor(unPressedColor: Int, pressedColor: Int, beSelected: Boolean = false) {
    val context = this.context
    if (pressedColor == 0) {
        (this as TextView).setTextColor(context.getResourceColor(unPressedColor))
        return
    }
    val colorStateList = ColorStateList(
        arrayOf(
            if (beSelected)
                intArrayOf(android.R.attr.state_selected)
            else
                intArrayOf(android.R.attr.state_pressed),
            intArrayOf()
        ),
        intArrayOf(
            context.getResourceColor(pressedColor),
            context.getResourceColor(unPressedColor)
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
