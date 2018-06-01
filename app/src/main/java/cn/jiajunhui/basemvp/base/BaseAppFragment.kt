package cn.jiajunhui.basemvp.base

import android.view.LayoutInflater
import android.view.View
import cn.jiajunhui.basemvp.R
import cn.jiajunhui.mvp.BaseMVPFragment
import cn.jiajunhui.mvp.layoutcontroller.DefaultLayoutController
import cn.jiajunhui.mvp.layoutcontroller.LayoutController
import cn.jiajunhui.mvp.mvp.IPresenter
import cn.jiajunhui.mvp.mvp.IView

abstract class BaseAppFragment<P:IPresenter<V>, V:IView> : BaseMVPFragment<P,V>() {

    override fun onBindLayoutController(): LayoutController = DefaultLayoutController(context!!)

    override fun onCreateLoadingView(): View? {
        return LayoutInflater.from(context).inflate(R.layout.layout_loading, null)
    }

    override fun onCreateErrorView(): View? {
        return LayoutInflater.from(context).inflate(R.layout.layout_error, null)
    }

}