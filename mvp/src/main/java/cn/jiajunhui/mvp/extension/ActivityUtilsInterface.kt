package cn.jiajunhui.mvp.extension

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager

interface ActivityUtilsInterface {

    fun keepScreenOnState(activity: Activity, state: Boolean){
        if (state)
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        else
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    fun switchScreenFillState(activity: Activity, fullScreen: Boolean, includeToolBar: Boolean){
        if(fullScreen){
            activity.window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN)
            if(includeToolBar)
                (activity as AppCompatActivity).supportActionBar?.hide()
        }else{
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            if(includeToolBar)
                (activity as AppCompatActivity).supportActionBar?.show()
        }
    }

}