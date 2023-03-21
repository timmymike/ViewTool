package com.timmymike.sample

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.timmymike.logtool.loge
import com.timmymike.viewtool.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.tv_test).run {
            isClickable = true
            setOnClickListener { isSelected = !isSelected }

            setTextSize(14)
//            background = getRectangleBg(tldp = 40, trdp = 40, left = false, bgColorID = R.color.purple_200, strokeWidth = 10, strokeColorID = R.color.teal_200)
            30.dpToPx(this@MainActivity).let {
                setPadding(it, it, it, it)
            }
            loge("tv1佔比總寬度=>${this.getRealityWidth().toFloat() / getScreenWidthPixels()}")
            setPressedTextColor(android.R.color.holo_green_dark, android.R.color.holo_blue_dark,couldSelected = false)
            background = getRepeatDrawable(R.drawable.ic_launcher_foreground)
//            setBackgroundResource(R.drawable.ic_class_checkbox_checked)
//            background = setPressedImage(R.drawable.ic_class_checkbox_checked,-1)
            setRippleBackground(android.R.color.holo_red_dark)

        }

        findViewById<TextView>(R.id.tv_test2).run {
            isClickable = true
            setOnClickListener { isSelected = !isSelected }

            setTextSize(14)
            30.let {
                setPaddingByDpUnit(it, it, it, it)
            }
            background = setPressedBackground(
                getRectangleBg(tldp = 40, trdp = 0, brdp = 40, left = false, bgColorID = R.color.black, strokeWidth = 10, strokeColorID = R.color.teal_200),
                getRectangleBg(tldp = 40, trdp = 0, brdp = 40, left = false, bottom = false, bgColorID = R.color.white, strokeWidth = 10, strokeColorID = R.color.purple_700),
                true
            )
            setPressedTextColor(android.R.color.holo_green_dark, android.R.color.holo_blue_dark,couldSelected = true)
//            loge("tv2佔比總寬度=>${this.getRealityWidth().toFloat() / getScreenWidthPixels()}")
//            background = getRepeatDrawable(R.drawable.ic_class_checkbox_checked)
//            setBackgroundResource(R.drawable.ic_class_checkbox_checked)
//            background = setPressedImage(R.drawable.ic_class_checkbox_checked,-1)
            setRippleBackground(android.R.color.holo_orange_dark)
        }

        findViewById<ImageView>(R.id.iv_test).run {
            isClickable = true
            setOnClickListener { isSelected = !isSelected }
            setPressedBackground(R.color.purple_700, R.color.teal_700, false)
            setPressedImage(R.drawable.ic_class_checkbox_uncheck, R.drawable.ic_class_checkbox_checked, false)
            setRippleBackground(android.R.color.holo_green_dark)
        }
        findViewById<CheckBox>(R.id.cb_test).run {
            setOnClickListener { isSelected = !isSelected }

            setCheckDrawable(R.drawable.ic_class_checkbox_uncheck, R.drawable.ic_class_checkbox_checked)
            setPressedBackground(R.color.purple_200, R.color.teal_700, true)
            setRippleBackground(android.R.color.holo_blue_dark)
        }
    }


}

