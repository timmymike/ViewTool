package com.timmymike.viewtool

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.util.DisplayMetrics
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment


/**
 * 此檔案為取得資源黨內的資料，不用擔心有版本問題，且保證有回傳值
 * @author Timmy.Hsieh
 * @date formatted 2023/03/21
 *
 * */
fun Context.getResourceColor(colorId: Int) = kotlin.runCatching { ContextCompat.getColor(this, colorId) }.getOrNull() ?: Color.TRANSPARENT

fun Context.getResourceDrawable(drawableID: Int) = kotlin.runCatching { ContextCompat.getDrawable(this, drawableID) }.getOrNull() ?: Color.TRANSPARENT.toDrawable()

fun Context.getResourceString(stringID: Int) = kotlin.runCatching { this.resources.getString(stringID) }.getOrNull() ?: ""

fun Context.getResourceStringArray(arrayId: Int): Array<String> = kotlin.runCatching { this.resources.getStringArray(arrayId) }.getOrNull() ?: arrayOf()

fun Context.getResourceColorOrNull(colorId: Int) = kotlin.runCatching { ContextCompat.getColor(this, colorId) }.getOrNull()

fun Context.getResourceDrawableOrNull(drawableID: Int) = kotlin.runCatching { ContextCompat.getDrawable(this, drawableID) }.getOrNull()

fun Context.getResourceStringOrNull(stringID: Int) = kotlin.runCatching { this.resources.getString(stringID) }.getOrNull()

fun Context.getResourceStringArrayOrNull(arrayId: Int): Array<String>? = kotlin.runCatching { this.resources.getStringArray(arrayId) }.getOrNull()


fun View.getResourceColor(colorId: Int) = this.context.getResourceColor(colorId)

fun View.getResourceDrawable(drawableID: Int) = this.context.getResourceDrawable(drawableID)

fun View.getResourceString(stringID: Int) = this.context.getResourceString(stringID)

fun View.getResourceStringArray(arrayId: Int): Array<String> = this.context.getResourceStringArray(arrayId)

fun View.getResourceColorOrNull(colorId: Int) = this.context.getResourceColorOrNull(colorId)

fun View.getResourceDrawableOrNull(drawableID: Int) = this.context.getResourceDrawableOrNull(drawableID)

fun View.getResourceStringOrNull(stringID: Int) = this.context.getResourceStringOrNull(stringID)


fun Fragment.getResourceColor(colorId: Int) = this.requireContext().getResourceColor(colorId)

fun Fragment.getResourceDrawable(drawableID: Int) = this.requireContext().getResourceDrawable(drawableID)

fun Fragment.getResourceString(stringID: Int) = this.requireContext().getResourceString(stringID)

fun Fragment.getResourceStringArray(arrayId: Int): Array<String> = this.requireContext().getResourceStringArray(arrayId)

val resourcesDisplayMetrics: DisplayMetrics get() = Resources.getSystem().displayMetrics