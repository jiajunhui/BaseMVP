package cn.jiajunhui.mvp.layoutcontroller

import android.view.View

interface LayoutPart {

    fun getUserContentView():View

    fun getLoadingView():View?=null

    fun getErrorView():View?=null

    fun getExtraView(type: Int):View?=null

}