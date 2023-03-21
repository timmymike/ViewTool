package com.timmymike.viewtool

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.core.content.res.ResourcesCompat


/**由timmymike整理的畫面美觀工具*/

fun Context.getResourceColor(colorId: Int) = kotlin.runCatching { ResourcesCompat.getColor(this.resources, colorId, this.theme) }.getOrNull() ?: Color.TRANSPARENT

fun Context.getResourceDrawable(drawableID: Int) = kotlin.runCatching { ResourcesCompat.getDrawable(this.resources, drawableID, this.theme) }.getOrNull() ?: ColorDrawable(Color.TRANSPARENT)

fun Context.getResourceString(stringID: Int) = kotlin.runCatching { Resources.getSystem().getString(stringID, this.theme) }.getOrNull() ?: ""

fun View.getResourceColor(colorId: Int) = this.context.getResourceColor(colorId)

fun View.getResourceDrawable(drawableID: Int) = this.context.getResourceDrawable(drawableID)

fun View.getResourceString(stringID: Int) = this.context.getResourceString(stringID)