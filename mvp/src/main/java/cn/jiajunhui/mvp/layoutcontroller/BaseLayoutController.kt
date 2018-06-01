package cn.jiajunhui.mvp.layoutcontroller

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout

abstract class BaseLayoutController(context:Context) : FrameLayout(context), LayoutController {

    private var onLayoutEventListener:OnLayoutEventListener?=null

    private var layoutPart:LayoutPart?=null

    init {
        layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
        setOnClickListener({})
    }

    final override fun setOnLayoutEventListener(onLayoutEventListener: OnLayoutEventListener) {
        this.onLayoutEventListener = onLayoutEventListener
    }

    final override fun attachLayoutPart(layoutPart: LayoutPart) {
        this.layoutPart = layoutPart
        onLayoutPartAttached()
    }

    abstract fun onLayoutPartAttached()

    fun sendEvent(code:Int, bundle: Bundle?){
        onLayoutEventListener?.onEvent(code, bundle)
    }

    fun getLayoutPart():LayoutPart? = layoutPart

    override fun getView(): View = this

}