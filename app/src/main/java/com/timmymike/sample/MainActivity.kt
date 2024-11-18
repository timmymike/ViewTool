package com.timmymike.sample

import android.graphics.Color
import android.os.Bundle
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.timmymike.logtool.loge
import com.timmymike.viewtool.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.tv_test).run {
            isClickable = true
            clickWithTrigger {
                loge("1觸發點擊！")
                isSelected = !isSelected
            }

//            setTextSize(14)
//            background = getRectangleBg(tldp = 40, trdp = 40, left = false, bgColorID = R.color.purple_200, strokeWidth = 10, strokeColorID = R.color.teal_200)
            30.dpToPx.let {
                setPadding(it, it, it, it)
            }
            loge("tv1佔比總寬度=>${this.getRealityWidth().toFloat() / getScreenWidthPixels()}")
            setClickTextColorStateById(android.R.color.holo_green_dark, android.R.color.holo_blue_dark)

            background = setClickBgState(
                getRoundBgById(500,android.R.color.holo_purple,R.color.black,20),
                getRectangleBgById(tldp = 40, trdp = 0, brdp = 40, left = false, bgColorID = R.color.black, strokeWidth = 10, strokeColorID = R.color.teal_200),
                getRectangleBgById(tldp = 40, trdp = 0, brdp = 40, left = false, bottom = false, bgColorID = R.color.white, strokeWidth = 10, strokeColorID = R.color.purple_700),
            )
//            background = getRepeatDrawable(R.drawable.ic_launcher_foreground)
//            background = getTintedDrawable(R.drawable.ic_launcher_foreground, android.R.color.holo_green_dark)
//            setBackgroundResource(R.drawable.ic_class_checkbox_checked)
//            background = setPressedImage(R.drawable.ic_class_checkbox_checked,-1)
//            setRippleBackgroundById(android.R.color.holo_red_dark)
            setRippleForeground(Color.BLUE)

        }
        var count = 0
        findViewById<TextView>(R.id.tv_test2).run {
            isClickable = true
            clickWithTrigger {
                isSelected = !isSelected
                Toast.makeText(context, "觸發點擊。${++count}", Toast.LENGTH_SHORT).show()

//                setRippleBackgroundById(android.R.color.holo_orange_dark)
            }

            setTextSize(50)
            30.let {
                setPaddingByDpUnit(it, it, it, it)
            }

            background = setClickBgState(
                getRectangleBgById(tldp = 40, trdp = 0, brdp = 40, left = false, bgColorID = R.color.black, strokeWidth = 10, strokeColorID = R.color.teal_200),
                selectedDrawable = getRectangleBgById(tldp = 40, trdp = 0, brdp = 40, left = false, bottom = false, bgColorID = R.color.white, strokeWidth = 10, strokeColorID = R.color.purple_700),
            )

            setClickTextColorState(Color.RED,Color.BLUE,Color.GRAY)
            loge("tv2佔比總寬度=>${this.getRealityWidth().toFloat() / getScreenWidthPixels()}")
//            background = getRepeatDrawable(R.drawable.ic_class_checkbox_checked)
//            setBackgroundResource(R.drawable.ic_class_checkbox_checked)
//            background = setPressedImage(R.drawable.ic_class_checkbox_checked,-1)
//            setRippleBackgroundById(android.R.color.holo_orange_dark)
        }

        findViewById<ImageView>(R.id.iv_test).run {
            isClickable = true
            setOnClickListener { isSelected = !isSelected }
            setClickBgStateById(R.color.purple_700, R.color.teal_700, R.color.white)
            setClickImgStateById(R.drawable.ic_class_checkbox_uncheck, R.drawable.ic_launcher_foreground, R.drawable.ic_class_checkbox_checked)
            setRippleForegroundById(android.R.color.holo_green_dark)
        }
        findViewById<CheckBox>(R.id.cb_test).run {
            setOnClickListener { isSelected = !isSelected }
            text = getResourceString(R.string.app_name)
            setCheckDrawable(R.drawable.ic_class_checkbox_uncheck, R.drawable.ic_class_checkbox_checked)
            setClickBgStateById(R.color.purple_200, R.color.teal_700)
            setRippleForegroundById(android.R.color.holo_red_dark)
        }
    }


}

