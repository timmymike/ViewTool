package com.timmymike.viewtool

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.DisplayMetrics
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment


/**
 * 此檔案為取得資源黨內的資料，不用擔心有版本問題，且保證有回傳值
 * @author Timmy.Hsieh
 * @date formatted 2023/03/21
 *
 * */

fun Context.getResourceColor(colorId: Int) = kotlin.runCatching { ResourcesCompat.getColor(this.resources, colorId, this.theme) }.getOrNull() ?: Color.TRANSPARENT

fun Context.getResourceDrawable(drawableID: Int) = kotlin.runCatching { ResourcesCompat.getDrawable(this.resources, drawableID, this.theme) }.getOrNull() ?: ColorDrawable(Color.TRANSPARENT)

fun Context.getResourceString(stringID: Int) = kotlin.runCatching { this.resources.getString(stringID, this.theme) }.getOrNull() ?: ""

fun View.getResourceColor(colorId: Int) = this.context.getResourceColor(colorId)

fun View.getResourceDrawable(drawableID: Int) = this.context.getResourceDrawable(drawableID)

fun View.getResourceString(stringID: Int) = this.context.getResourceString(stringID)

fun Fragment.getResourceColor(colorId: Int) = this.requireContext().getResourceColor(colorId)

fun Fragment.getResourceDrawable(drawableID: Int) = this.requireContext().getResourceDrawable(drawableID)

fun Fragment.getResourceString(stringID: Int) = this.requireContext().getResourceString(stringID)

val resourcesDisplayMetrics: DisplayMetrics get() = Resources.getSystem().displayMetrics