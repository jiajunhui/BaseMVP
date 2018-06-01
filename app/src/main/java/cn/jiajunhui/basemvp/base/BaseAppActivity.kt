package cn.jiajunhui.basemvp.base

import android.view.LayoutInflater
import android.view.View
import cn.jiajunhui.basemvp.R
import cn.jiajunhui.mvp.BaseMVPActivity
import cn.jiajunhui.mvp.layoutcontroller.DefaultLayoutController
import cn.jiajunhui.mvp.layoutcontroller.LayoutController
import cn.jiajunhui.mvp.mvp.IPresenter
import cn.jiajunhui.mvp.mvp.IView

abstract class BaseAppActivity<P:IPresenter<V>, V:IView> : BaseMVPActivity<P,V>() {

    override fun onBindLayoutController(): LayoutController = DefaultLayoutController(this)

    override fun onCreateLoadingView(): View? {
        return LayoutInflater.from(this).inflate(R.layout.layout_loading, null)
    }

    override fun onCreateErrorView(): View? {
        return LayoutInflater.from(this).inflate(R.layout.layout_error, null)
    }

}