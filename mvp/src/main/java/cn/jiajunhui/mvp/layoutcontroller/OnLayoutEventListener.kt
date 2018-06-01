package cn.jiajunhui.mvp.layoutcontroller

import android.os.Bundle

interface OnLayoutEventListener {

    fun onEvent(code: Int, bundle: Bundle?)

}

const val LAYOUT_EVENT_CODE_ERROR_RETRY:Int = -100