package cn.jiajunhui.mvp.layoutcontroller

import android.os.Bundle
import android.view.View

interface LayoutController {

    fun setOnLayoutEventListener(onLayoutEventListener: OnLayoutEventListener)

    fun loading(bundle: Bundle?)

    fun success(bundle: Bundle?)

    fun error(bundle: Bundle?)

    fun extra(type: Int, bundle: Bundle?)

    fun attachLayoutPart(layoutPart: LayoutPart)

    fun getView():View

}